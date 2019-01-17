package com.dygstudio.testEsJavaAPI.client;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;

import java.net.UnknownHostException;

/**
 * @author: diyaguang
 * @date: 2019/01/17 10:25 AM
 * @description: com.dygstudio.testEsJavaAPI.client
 */
public class AggregationTest {
    public static void testMaxAggregation() throws UnknownHostException {
        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("agg").field("price");
        SearchResponse sr = TestClient.getSingleClient().prepareSearch("books").addAggregation(maxAggregationBuilder).get();
        Max agg = sr.getAggregations().get("agg");
        double value = agg.getValue();
        System.out.println(value);
    }

    public static void testMinAggregation() throws UnknownHostException{
        MinAggregationBuilder minAgg = AggregationBuilders.min("agg").field("price");
        SearchResponse sr = TestClient.getSingleClient().prepareSearch("books").addAggregation(minAgg).execute().actionGet();
        Min min = sr.getAggregations().get("agg");
        double minValue = min.getValue();
        System.out.println(minValue);
    }
}
