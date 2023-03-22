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

    //precisamos de builders para construir nossas etapas(STEP)
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Bean
    public Job imprimirOlaJob() {
        return jobBuilderFactory
                .get("imprimeOláJob") // recebe o nome do job
                .start(imprimeOlaStep()) // recebe um flow que é um Step
                .incrementer(new RunIdIncrementer()) // quando quiser executar varias vezes sem alterar os parametros  eu coloco esse incremento
                .build();
    }
    // como utilizamos o spring precisamos colocar esse objeto no contexto da aplicação, utilizamos o @Bean
    public Step imprimeOlaStep(){
        return stepBuilderFactory
                .get("imprimeOlaStep")
                .tasklet(getTasklet(null))
                .build();
    }
    // para podermos acessar o nome que esta no contexto, devemos anotar com @StepScope
    @StepScope
    @Bean
    public Tasklet getTasklet(@Value("#{jobParameters['nome']}") String nome) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                // aqui vai a lógica que precisamos, no nosso caso e em primeiro momento apenas um "olá mundo".
                System.out.println(String.format("olá, %s!", nome));
                return RepeatStatus.FINISHED;
            }
        };
    }

    // tasklet - tarefa simples
}
