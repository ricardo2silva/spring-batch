package com.ricardo.estudospringbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ImprimeOlaTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        // aqui vai a lógica que precisamos, no nosso caso e em primeiro momento apenas um "olá mundo".
        System.out.println(String.format("olá mundo !"));
        return RepeatStatus.FINISHED;
    }
}
