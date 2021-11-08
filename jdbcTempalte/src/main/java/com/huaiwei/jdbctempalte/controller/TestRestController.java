package com.huaiwei.jdbctempalte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestRestController {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/baidu")
    public String RequestBaidu() throws Exception {
        //restTemplate.exchange("www.baidu.com", HttpMethod.GET, HttpEntity.EMPTY,String.class,null);
        Integer i = 5;
        Integer j = 6;
        i.equals(j);

        return null;
    }
}
