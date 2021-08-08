package com.backbase.moviesRankService.config;


import com.backbase.moviesRankService.listeners.JobCompletionListener;
import com.backbase.moviesRankService.processor.AcademyAwardItemProcessor;
import com.backbase.moviesRankService.types.AcademyAwardsDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobCompletionListener jobCompletionListener;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    //to be suplanted by jobParameter on runtime
    private static final String OVERRIDDEN_BY_EXPRESSION = null;

    @Bean
    @StepScope
    //StepScope anotation in other to read the jobParameter value
    public FlatFileItemReader<AcademyAwardsDTO> reader(@Value("academy_awards.csv") String path){
        System.out.println("FlatFileItemReader path" + path);
        FlatFileItemReader<AcademyAwardsDTO> reader = new FlatFileItemReader<AcademyAwardsDTO>();

        reader.setResource(new ClassPathResource(path));
        reader.setLinesToSkip(1);
        reader.setStrict(true);
        reader.setLineMapper(new DefaultLineMapper<AcademyAwardsDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer(","){{
                setNames("year","category","nominee","additionalInfo","won","field1","field2","field3","field4","field5","field6");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<AcademyAwardsDTO>() {{
                setDistanceLimit(0);
                setTargetType(AcademyAwardsDTO.class);
            }});
        }});

        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<AcademyAwardsDTO> writer() {
        System.out.println("JdbcBatchItemWriter");
        JdbcBatchItemWriter<AcademyAwardsDTO> writer = new JdbcBatchItemWriter<AcademyAwardsDTO>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<AcademyAwardsDTO>());
        writer.setSql("INSERT INTO public.academy_award(year, category, nominee, additional_info, won) VALUES (:year, :category, :nominee, :additionalInfo, :won)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job customUserJob(JobCompletionListener listener) {
        System.out.println("customUserJob");
        return jobBuilderFactory.get("customUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public AcademyAwardItemProcessor processor(){
        return new AcademyAwardItemProcessor();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                //bigger chunkSize to test faster, would go down on production
                .<AcademyAwardsDTO, AcademyAwardsDTO> chunk(4000)
                .reader(reader(OVERRIDDEN_BY_EXPRESSION))
                .processor(processor())
                .writer(writer())
                .build();
    }


}


