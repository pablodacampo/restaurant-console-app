package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("dev")
//@PropertySource('app.properties')
public class AppConfig {

	
	@Bean
	public Scanner scan() {
		return new Scanner(System.in);
	}
	
	
	
	
}
