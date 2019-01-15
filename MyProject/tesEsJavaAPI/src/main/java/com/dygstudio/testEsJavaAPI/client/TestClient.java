package com.dygstudio.testEsJavaAPI.client;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Project: tesEsJavaAPI
 * @Author: diyaguang
 * @CreateDate: 2019-01-15-14:48
 * @Description:
 */
public class TestClient {
    public static String CLUSTER_NAME = "elasticsearch";
    public static String HOST_IP="192.168.120.131";
    public static int TCP_PORT = 9300;
    public static void main(String[] args) throws UnknownHostException{
        testGet();
        testIndex();
    }

    public static void testGet(){
        GetResponse getResponse = client.prepareGet("books","IT","1").get();
        System.out.println(getResponse.getSourceAsString());
    }

    public static void testIndex(){
        IndicesAdminClient indicesAdminClient = client.admin().indices();  //获取索引对象

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
        //获取 Settings
        //设置 Mapping
        //获取 Mapping
        //删除索引
        //刷新
        //关闭索引
        //打开索引
        //设置别名
        //获取别名
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
