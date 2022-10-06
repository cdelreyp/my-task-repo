package com.nttdata;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableConfigurationProperties
@OpenAPIDefinition(info = @Info(title = "API - NTT DATA", version = "Versión 1.0.0", description = "DESCRIPCIÓN (editar)", contact = @Contact(name = "GRUPO C", email = "correo@emeal.nttdata.com")))
public class EverisDarMytasksMsApplication {

	
	public static void main(String[] args) {
	
		SpringApplication.run(EverisDarMytasksMsApplication.class, args);
	}
	


}
