package com.javaee.code.class2.jdbc;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;

import java.sql.Connection;


public class DatabasePool {
    private static HikariDataSource dataSourse;
    public static HikariDataSource getHikariDataSourse(){
        if(dataSourse!=null){
            return dataSourse;
        }
        synchronized (DatabasePool.class){
            if(dataSourse==null){
                HikariConfig hikariConfig=new HikariConfig();
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("123456");
                hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://127.0.0.1:3306/school?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
                String allUrl = url + "&user=root&password=123456";
                hikariConfig.setAutoCommit(false);
                hikariConfig.setJdbcUrl(url);
                dataSourse=new HikariDataSource(hikariConfig);
                return dataSourse;
            }
            return null;
        }
    }
}
