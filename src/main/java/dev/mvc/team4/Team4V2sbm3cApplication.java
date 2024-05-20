package dev.mvc.team4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-secret.properties")
@ComponentScan(basePackages = {"dev.mvc"})
public class Team4V2sbm3cApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team4V2sbm3cApplication.class, args);
	}

}
