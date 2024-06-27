package dev.mvc.team4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:application-secret.properties")
@ComponentScan(basePackages = {"dev.mvc"})
@EntityScan(basePackages = {"dev.mvc"})
@EnableJpaRepositories(basePackages = {"dev.mvc.ai_history", "dev.mvc.ai_sort"})
public class Team4V2sbm3cApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team4V2sbm3cApplication.class, args);
	}

}
