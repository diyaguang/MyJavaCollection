package com.dygstudio.testEsJavaAPI.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: diyaguang
 * @date: 2019/01/16 4:26 PM
 * @description: com.dygstudio.testEsJavaAPI.client
 */
public class DocumentClient {

    //把JSON转化为 String
    public static void testJSON2String() throws IOException {
        String doc1 = "{"+
                "\"user\":\"kimchy\","+
                "\"postDate\":\"2013-01-30\","+
                "\"message\":\"trying out Elasticsearch\""+
                "}";
        IndexResponse response = TestClient.getSingleClient()
                .prepareIndex("twitter","tweet","1")
                .setSource(doc1)
                .get();
        System.out.println(response.status());
    }

    public static void testJSON2Map() throws IOException{
        Map<String,Object> doc2 = new HashMap<String,Object>();
        doc2.put("user","kimchy");
        doc2.put("postDate","2013-01-30");
        doc2.put("message","trying out Elasticsearch");
        IndexResponse response = TestClient.getSingleClient()
                .prepareIndex("twitter","tweet","2")
                .setSource(doc2)
                .get();
        System.out.println(response.status());
    }
    public static void testJSON2XContentBuilder() throws IOException{
        XContentBuilder doc3 = XContentFactory.jsonBuilder()
                .startObject()
                .field("user","kimchy")
                .field("postDate","2013-01-30")
                .field("message","trying out Elasticsearch")
                .endObject();
        System.out.println(doc3.toString());
        IndexResponse response = TestClient.getSingleClient()
                .prepareIndex("twitter","tweet","3")
                .setSource(doc3)
                .get();
        System.out.println(response.status());

        XContentBuilder doc3_2 = XContentFactory.jsonBuilder()
                .startObject()
                .field("name","Tom")
                .field("age","12")
                .startArray("scores")
                .startObject().field("Math","80").endObject()
                .startObject().field("English","85").endObject()
                .endArray()
                .field("address")
                .startObject().field("country","china").field("city","Beijing")
                .endObject()
                .endObject();
        System.out.println(doc3_2.toString());
    }

    public static void testJSON2Jackson() throws Exception{
        User user = new User("Zhang San",new Date(2013-1900,1-1,30),"trying out Elasticsearch");
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(format);
        byte[] doc4 = mapper.writeValueAsBytes(user);
        IndexResponse response = TestClient.getSingleClient()
                .prepareIndex("twitter","tweet","4")
                .setSource(doc4)
                .get();
        System.out.println(response.status());
    }
}
