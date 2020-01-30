package com.moinros.config.must;

import com.alibaba.druid.pool.DruidDataSource;
import com.moinros.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl(Config.get("datasource.url"));
        source.setUsername(Config.get("datasource.username"));
        source.setPassword(Config.get("datasource.password"));
        return source;
    }
}
