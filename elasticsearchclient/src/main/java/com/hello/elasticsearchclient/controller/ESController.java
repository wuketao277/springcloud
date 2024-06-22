package com.hello.elasticsearchclient.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author wuketao
 * @date 2024/6/20
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("es")
public class ESController {
    @Autowired
    private RestHighLevelClient client;

    @GetMapping("get")
    public String get(@RequestParam(defaultValue = "test") String index, @RequestParam(defaultValue = "user") String type, @RequestParam(defaultValue = "1") String id) {
        String response = null;
        GetRequest getRequest = new GetRequest(index, type, id);
        RequestOptions.Builder requestOptions = RequestOptions.DEFAULT.toBuilder();
        try {
            GetResponse documentFields = client.get(getRequest, requestOptions.build());
            response = documentFields.getSourceAsString();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return response;
    }
}
