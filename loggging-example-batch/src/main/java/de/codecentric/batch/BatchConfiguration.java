package de.codecentric.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.codecentric.batch.domain.Item;
import de.codecentric.batch.item.LogItemProcessor;
import de.codecentric.batch.item.LogItemWriter;
import de.codecentric.batch.item.StaticItemReader;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    @StepScope
    public ItemReader<Item> reader(@Value("#{jobParameters[duration]}") Integer duration) {
    	StaticItemReader reader = new StaticItemReader();
    	reader.setDuration(duration);
    	return reader;
    }

    @Bean
    public ItemProcessor<Item, Item> processor() {
        return new LogItemProcessor();
    }

    @Bean
    public ItemWriter<Item> writer() {
        return new LogItemWriter();
    }

    @Bean
    public Job exampleJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("exampleJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Item> reader,
            ItemWriter<Item> writer, ItemProcessor<Item, Item> processor) {
        return stepBuilderFactory.get("step1")
                .<Item, Item> chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
