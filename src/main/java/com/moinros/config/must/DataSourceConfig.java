package com.moinros.config.must;

import com.alibaba.druid.pool.DruidDataSource;
import com.moinros.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);
    @Bean
    public DruidDataSource druidDataSource() {
        LOG.info(Config.get("datasource.url"));
        LOG.info(Config.get("datasource.username"));
        LOG.info(Config.get("datasource.password"));

        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl(Config.get("datasource.url"));
        source.setUsername(Config.get("datasource.username"));
        source.setPassword(Config.get("datasource.password"));
        // 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        source.setTestWhileIdle(true);
        return source;
    }
}
