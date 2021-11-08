package com.huaiwei.druid.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class DruidDataSourceConfig extends DruidDataSource {

    private static String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKfkK2hgtAKuV3uuU1wjo0kyuCFcf4QXxF" +
            "4+zFUYWf4a5s6CJneKGzwE+onpUL3tsa3SB1Q1HtIlykXO+g7hlkECAwEAAQ==";

    @Override
    public void setPassword(String password) {
        try {
            ConfigTools.decrypt(publicKey, password);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void setUsername(String username) {
        try {
            ConfigTools.decrypt(publicKey, username);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        String password = "kunkun1573";
        String username = "root";
        password = ConfigTools.encrypt(password);
        System.out.println(password);
        username = ConfigTools.encrypt(username);
        System.out.println(username);
    }
}
