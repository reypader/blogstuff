package com.rmpader.security.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private DataSourceProperties properties;

    /*
     * (non-javadoc)
     *
     * Use Tomcat 7 DBCP because GAE restricts usage on
     * java.lang.management.ManagementFactory
     *
     */
    @Bean
    public DataSource datasource() {
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName(properties.getDriverClassName());
        datasource.setUrl(properties.getUrl());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());
        datasource.setMaxActive(12); // Maximum CloudSQL connection per GAE instance.
        datasource.setMaxIdle(12);
        datasource.setMinIdle(12);
        datasource.setInitialSize(12);
        datasource.setTestOnBorrow(true);
        datasource.setValidationQuery("SELECT 1");
        return datasource;
    }
}
