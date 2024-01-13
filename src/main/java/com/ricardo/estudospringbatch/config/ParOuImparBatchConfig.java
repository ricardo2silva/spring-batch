package com.ricardo.estudospringbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ParOuImparBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job  imprimiParImparJob(){
        return jobBuilderFactory.get("imprimiParImparJob")
                .start(imprimeParImparStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    public Step imprimeParImparStep(){
        return stepBuilderFactory
                .get("imprimeParImparStep")
                .<Integer,String>chunk(1) //primeiro é a entrada e o segundo é a sáida e escolho o tamanho do chunk
                .reader(contaAteDezReader()) // ler os dados
                .processor(parOuImparProcessor()) //processar a informação
                .writer(imprimeWriter()) // imprimir a informação
                .build();
    }
//usando uma implementação que o spring fornece
    // é representado por ItemReader, mas como vamos iterar utilizamos a IteratorItemReader
    public IteratorItemReader<Integer> contaAteDezReader(){
        List<Integer> numerosDeUmADez = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return  new IteratorItemReader<Integer>(numerosDeUmADez.iterator());
    }
    //os processadores é representados por Item processor, mas como vamos usar uma função, vamos utilizar uma implementação que  o spring nos fornece
    public FunctionItemProcessor<Integer,String> parOuImparProcessor(){ // espera uma entrada Integer e o retorno uma String
        return new FunctionItemProcessor<Integer,String>(item -> item % 2 == 0 ? String.format("o numero %s é par", item) : String.format("o numero %s é impar", item));
    }

    public ItemWriter<String> imprimeWriter(){
        return itens -> itens.forEach(System.out::println);
    }

}
