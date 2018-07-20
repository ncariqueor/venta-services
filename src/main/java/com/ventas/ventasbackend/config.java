package com.ventas.ventasbackend;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class config {

    @Bean(name = "JdbcVta")
    @Primary
    @ConfigurationProperties("app.datasource.vta")
    public JdbcTemplate firstDataSourceProperties(@Qualifier(value = "venta_datasource") DataSource firstDataSource) {
        return new JdbcTemplate(firstDataSource);
    }

    @Bean(name = "venta_datasource")
    @Primary
    @ConfigurationProperties("app.datasource.vta")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }
}
