package com.huaiwei.jdbctempalte;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbcTemplateApplicationTests {

    @Autowired
    StringEncryptor encryptor;

    @Test
    void test1() {

       // java -jar app-0.0.1-SNAPSHOT.jar --jasypt.encryptor.password=xxxxx
        String url = encryptor.encrypt("jdbc:mysql://192.168.134.135:3306/rbac?serverTimezone=UTC&useSSL=false&characterEncoding=utf8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("12321573");
        System.out.println(url);
        System.out.println(name);
        System.out.println(password);
    }

}
