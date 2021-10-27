package com.huaiwei.rbac.controller.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @PostMapping(value = "/ids", consumes = {"application/json"}, produces = {"application/json"})
    public List<String> ids(@RequestBody List<String> ids) {

        return ids;
    }
}
