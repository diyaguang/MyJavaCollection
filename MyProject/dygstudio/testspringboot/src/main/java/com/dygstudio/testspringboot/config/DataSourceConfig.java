package com.dygstudio.testspringboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author: diyaguang
 * @date: 2019/02/18 9:49 AM
 * @description: com.dygstudio.testspringboot.config
 */
@Configuration
public class DataSourceConfig {

    @Bean(name="dataSource")
    public DataSource datasource(Environment env){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }
}
