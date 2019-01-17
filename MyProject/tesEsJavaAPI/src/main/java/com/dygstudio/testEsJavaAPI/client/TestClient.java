package com.dygstudio.testEsJavaAPI.client;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Project: tesEsJavaAPI
 * @Author: diyaguang
 * @CreateDate: 2019-01-15-14:48
 * @Description:
 */
public class TestClient {
    public static String CLUSTER_NAME = "dyg_elasticsearch";
    public static String HOST_IP="10.211.55.34";
    public static int TCP_PORT = 9300;

    public static void main(String[] args) throws IOException{
        //testGet();
        testIndex();
    }

    public static void testGet() throws UnknownHostException{
        GetResponse getResponse = getSingleClient().prepareGet("books","it","1").get();
        System.out.println(getResponse.getSourceAsString());
    }

    public static void testIndex() throws IOException {
        IndicesAdminClient indicesAdminClient = getSingleClient().admin().indices();  //获取索引对象

        //判断索引是否存在
        IndicesExistsResponse exResponse = indicesAdminClient.prepareExists("IndexName").get();
        System.out.println(exResponse.isExists());

        //判断类型是否存在
        TypesExistsResponse exTypeResponse = indicesAdminClient.prepareTypesExists("indexName")
                .setTypes("type1","type2").get();
        System.out.println(exTypeResponse.isExists());

        //创建一个索引(e.g: 索引名必须小写)
        CreateIndexResponse cIndexResponse = indicesAdminClient.prepareCreate("indexName").get();
        System.out.println(cIndexResponse.isAcknowledged());

        //创建索引并设置 Settings
        CreateIndexResponse csIndexResponse = indicesAdminClient.prepareCreate("twitter")
                .setSettings(Settings.builder()
                        .put("index.number_of_shards",3)
                        .put("index.number_of_replicas",2)
                ).get();

        //更新副本
        /*UpdateSettingsResponse upResponse = indicesAdminClient
                .prepareUpdateSettings("twitter")
                .setSettings(Settings.builder()
                        .put("index.number_of_replicas",0))
                .get();
                */
        //ES在 6。0 后的版本有变化
        UpdateSettingsRequest usRequest = new UpdateSettingsRequest("indexName");
        usRequest.settings(Settings.builder().put("number_of_replicas",0).build());

        //获取 Settings
        GetSettingsResponse gsResponse = indicesAdminClient.prepareGetSettings("twitter","tweet").get();
        for(ObjectObjectCursor<String,Settings> cursor : gsResponse.getIndexToSettings()){
            String index = cursor.key;
            Settings settings = cursor.value;
            Integer shards = settings.getAsInt("index.number_of_shards",null);
            Integer replicas = settings.getAsInt("index.number_of_replicas",null);
            System.out.println("type:"+index+", shards:"+shards+" replicas:"+replicas);
        }

        //设置 Mapping
        getSingleClient().admin().indices().preparePutMapping("twitter").setType("tweet").setSource("{}").get();
        //或使用 XContentFactory来构造
        CreateIndexResponse cResponse = indicesAdminClient.prepareCreate("twitter").addMapping("tweet ",
                XContentFactory.jsonBuilder()
                        .startObject()
                        .startObject("properties")
                        .startObject("name")
                        .field("type"," keyword ")
                        .endObject()
                        .endObject()
                        .endObject())
                .get();

        //获取 Mapping
        GetMappingsResponse mResponse = indicesAdminClient.prepareGetMappings("indexName").get();
        ImmutableOpenMap<String, MappingMetaData> mapings = mResponse.getMappings().get("indexname");
        MappingMetaData metatda = mapings.get("typename");
        System.out.println(metatda.getSourceAsMap());

        //删除索引
        DeleteIndexRequest diRequest = new DeleteIndexRequest("indexName");
        //AcknowledgedResponse deleteIndexResponse = indicesAdminClient.delete(diRequest, RequestOptions.DEFAULT);

        //刷新
        indicesAdminClient.prepareRefresh().get();
        indicesAdminClient.prepareRefresh("indexName").get();
        indicesAdminClient.prepareRefresh("indexName","typeName").get();

        //关闭索引
        CloseIndexRequest ciRequest = new CloseIndexRequest("indexName");

        //打开索引
        OpenIndexResponse opResponse = indicesAdminClient.prepareOpen("indexName").get();

        //设置别名
        IndicesAliasesRequest iaRequest = new IndicesAliasesRequest();
        IndicesAliasesRequest.AliasActions aliasAction = new IndicesAliasesRequest
                .AliasActions(IndicesAliasesRequest.AliasActions.Type.ADD)
                .index("indexName")
                .alias("aliasName");
        iaRequest.addAliasAction(aliasAction);

        //获取别名
        GetAliasesResponse gaResponse = indicesAdminClient.prepareGetAliases("aliasesName").get();
    }

    private volatile  static TransportClient client;
    public static TransportClient getSingleClient() throws UnknownHostException{
        if(client == null){
            synchronized (TransportClient.class){
                Settings settings = Settings.builder().put("cluster.name",CLUSTER_NAME).build();
                client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName(HOST_IP),TCP_PORT));
            }
        }
        return client;
    }
}
