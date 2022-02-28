package cl.empresa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("cl.empresa.api.*")
public class PruebaApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApiRestApplication.class, args);
	}

 
}
