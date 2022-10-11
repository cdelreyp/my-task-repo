package com.nttdata;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Clase para arrancar la aplicación Spring Boot
 * 
 * @author NTT DATA - Digital Architecture - Grupo C
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
@OpenAPIDefinition(info = @Info(	title = "MyTasks App", version = "VERSION 1.0.0",
									description =	"The backend service will allow us to make the link between the web application and the data stored in the database.\r\n"
													+ "\r\n" + 
													"To facilitate the implementation of the application we will use an in-memory database type H2. These databases are reset at each login, although we can make the application have data loaded by default.\r\n"
													+ "\r\n" +
													"The backend will be in charge of validating the input data of the services, implementing the business logic in those services that are necessary and making queries against the stored data.",
									contact = @Contact(name = "GRUPO C", 
									email = "correo@emeal.nttdata.com")))
public class EverisDarMytasksMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EverisDarMytasksMsApplication.class, args);
	}

} /** Holaaa */
