package com.huaiwei.druid.controller;

import com.huaiwei.druid.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class HelloController {
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }


    /**
     * @return 返回user的list集合
     */
    @GetMapping("/list")
    public List<User> list() {
        String sql = "select * from sys_user";
        List<User> userList = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }


}