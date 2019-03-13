package com.dygstudio.testspringboot2.api;

import com.dygstudio.testspringboot2.config.RedisHelp;
import com.dygstudio.testspringboot2.service.SWebSiteInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: diyaguang
 * @date: 2019/03/12 9:48 AM
 * @description: com.dygstudio.testspringboot2.api
 * 添加 WebSiteInfo 数据到数据库 POST /api/websiteinfo/
 * 添加 WebSiteInfo 数据到 Redis POST /api/websiteinfo/redis
 * 获取 WebSiteInfo 数据从 Redis GET   /api/websiteinfo/redis/:key
 * 官方文档：http://swagger.io/docs/specification/api-host-and-base-path/
 */
@RestController
@RequestMapping(value = "/api/websiteinfo")
public class SWebSiteInfoAPI {
    @Autowired
    SWebSiteInfoService webSiteInfoService;

    @Autowired
    RedisHelp redisClient;

    @ApiOperation(value = "添加 Web站点信息到数据库",notes = "添加 Web站点信息 DataBase")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "站点ID",required = true,dataType = "String",paramType = "post"),
            @ApiImplicitParam(name = "name",value = "站点名称",dataType = "String",paramType = "post")
    })
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public int add(@RequestParam("id") String id,@RequestParam("name") String name){
        return webSiteInfoService.add(id,name);
    }

    @ApiOperation(value = "添加 Web站点信息到 Redis",notes = "添加 Web站点信息 Redis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "Redis对应的Key",required = true,dataType = "String",paramType = "post"),
            @ApiImplicitParam(name = "value",value = "Redis对应的Value  ",dataType = "String",paramType = "post")
    })
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    public String setWebSiteInfoRedis(@RequestParam("key") String key,@RequestParam("value") String value){
        redisClient.setKey(key,value);
        return "OK";
    }

    @ApiOperation(value = "根据 Key 来从 Redis 中获取对应的 value")
    @ApiImplicitParam(name = "key",value = "Redis对应的Key",required = true,dataType = "String",paramType = "path")
    @GetMapping(value = "/redis/{key}")
    public String getWebSiteInfoRedis(@PathVariable("key") String key){
        return redisClient.getValue(key);
    }
}
