package de.codecentric.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.codecentric.batch.configuration.DataSourceConfiguration;

@Configuration
@EnableAutoConfiguration
@Import(DataSourceConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
