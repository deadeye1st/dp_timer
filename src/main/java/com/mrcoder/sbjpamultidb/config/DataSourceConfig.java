package com.mrcoder.sbjpamultidb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {



    @Primary
    @Bean(name = "masterDataSourceProperties")
    @Qualifier("masterDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(@Qualifier("masterDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }


    @Bean(name = "slaveDataSourceProperties")
    @Qualifier("slaveDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "slaveDataSource")
    @Qualifier("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(@Qualifier("slaveDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "slaveTZDataSourceProperties")
    @Qualifier("slaveTZDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.slave_TZ")
    public DataSourceProperties slaveTZDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "slaveTZDataSource")
    @Qualifier("slaveTZDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave_TZ")
    public DataSource slaveTZDataSource(@Qualifier("slaveTZDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
