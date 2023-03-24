package com.ricardo.estudospringbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// devemos trazer o contexto do framework
@EnableBatchProcessing
@Configuration
public class BatchConfig {

    //precisamos de builders para construir nossas rotinas
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job imprimirOlaJob(Step imprimiOlaStep) {
        return jobBuilderFactory
                .get("imprimeOláJob") // recebe o nome do job
                .start(imprimiOlaStep) // recebe um flow que é um Step
                .incrementer(new RunIdIncrementer()) // quando quiser executar varias vezes sem alterar os parametros  eu coloco esse incremento
                .build();
    }

}
