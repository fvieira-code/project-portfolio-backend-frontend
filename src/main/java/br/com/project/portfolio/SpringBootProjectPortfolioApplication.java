package main.java.br.com.project.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProjectPortfolioApplication { //extends SpringBootServletInitializer {

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootProjectPortfolioApplication.class);
	}*/
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectPortfolioApplication.class, args);
	}

}
