package com.dygstudio.testspringboot.controller;

import com.dygstudio.testspringboot.entity.Book;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: diyaguang
 * @date: 2019/02/27 4:57 PM
 * @description: com.dygstudio.testspringboot.controller
 */
@RestController
public class RestClientController {

    @RequestMapping("/restclient/book/{id}")
    public String getLogById(@PathVariable String id) throws Exception{
        Book book = null;
        RestTemplate template = new RestTemplate();
        Map<String,Object> paras = new HashMap<>();
        paras.put("id",id);
        String str = template.getForObject("http://10.211.55.34:9200/product/book/{id}",String.class,paras);

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(str);
        JsonNode root = mapper.readTree(parser);
        JsonNode sourceNode = root.get("_source");

        book = mapper.convertValue(sourceNode,Book.class);
        return book.getMessage();
        //return ""; //log.getMessage();
    }
}
