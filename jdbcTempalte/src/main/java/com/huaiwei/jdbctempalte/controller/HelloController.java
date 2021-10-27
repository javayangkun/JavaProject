package com.huaiwei.jdbctempalte.controller;

import com.huaiwei.jdbctempalte.domain.User;
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


    @GetMapping("/list")
    public List<User> list() {
        String sql = "select * from sys_user";
        List<User> userList = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }
}
