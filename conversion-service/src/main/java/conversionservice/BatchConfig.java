package conversionservice;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
public class BatchConfig   {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public ItemReader<CurrencyConversion> reader(){
		JdbcCursorItemReader<CurrencyConversion> cursorItemReader = new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql("SELECT * FROM Currency_Conversion");
		cursorItemReader.setRowMapper(new CurrencyConversionMapper());
		return cursorItemReader;
	}
	@Bean
	public CurrencyConversionProcessor processor(){
		return new CurrencyConversionProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<CurrencyConversion> writer(){
		FlatFileItemWriter<CurrencyConversion> writer = new FlatFileItemWriter<CurrencyConversion>();
		writer.setResource(new ClassPathResource("CurrencyConversion.csv"));
		
		DelimitedLineAggregator<CurrencyConversion> lineAggregator = new DelimitedLineAggregator<CurrencyConversion>();
		lineAggregator.setDelimiter(",");
		
		BeanWrapperFieldExtractor<CurrencyConversion>  fieldExtractor = new BeanWrapperFieldExtractor<CurrencyConversion>();
		fieldExtractor.setNames(new String[]{"id","conv_from","conv_to","quantity","conversion_multiple","total_calculated_amount","port"});
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
		return writer;
	}
	
	@Bean
	public Step step1(){
		return stepBuilderFactory.get("step1").<CurrencyConversion,CurrencyConversion>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
	}

	@Bean
	public Job exportPerosnJob(){
		return jobBuilderFactory.get("exportCsv").incrementer(new RunIdIncrementer()).flow(step1()).end().build(); //xstream is added for marshalling and unmarshalling
	}
	
}
