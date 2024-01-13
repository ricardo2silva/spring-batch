package com.ricardo.estudospringbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // estamos definindo quem vai ser o banco default por padrao
    //neste bean definimos que o datasource dele vai ser o spring batch
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springDataSource(){
        return DataSourceBuilder.create().build();
    }

    // neste caso vamos definir que esse datasource será de nossa aplicação
    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource appDataSource(){
        return DataSourceBuilder.create().build();
    }

    // no ambiente produtivo é interessante ter varios datasources implementados, uma vez que um banco fica disponivel para os metadados do spring batch
    // e o outro com as informações pertinentes a regra de negocio

    // obs : será necessario alterar o properties  para acrescentar os demais datasource
}
