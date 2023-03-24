package com.ricardo.estudospringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// separando as repsonsabilidades
@Configuration
public class ImprimeOlaStepBatchConfig {

    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Bean
    public Step imprimeOlaStep(Tasklet imprimiOlaTasklet){
        return stepBuilderFactory
                .get("imprimeOlaStep")
                .tasklet(imprimiOlaTasklet)
                .build();
    }
}
