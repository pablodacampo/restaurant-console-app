package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.*;

//Configuration pilotée par des annotation
@Configuration
//activer la recherche de beans Spring
@ComponentScan({"dev"})
@PropertySource("app.properties")
public class AppConfig {

	// constructeur de bean car classe particulière comme on ne peut pas toucher au JDK pour ajouter les annotation
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
	
	
}
