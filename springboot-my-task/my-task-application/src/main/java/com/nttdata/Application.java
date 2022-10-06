/********************************************************************************
 * 
 * Autor: NTT DATA - Digital Architecture - Grupo C
 * 
 * © Copyright 2022 NTT DATA Spain and Affiliates
 * 
 ********************************************************************************/

package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Clase para arrancar la aplicación Spring Boot
 * 
 * @author NTT DATA - Digital Architecture - Grupo C
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
@OpenAPIDefinition(info = @Info(title = "API - NTT DATA", version = "Versión 1.0.0", description = "DESCRIPCIÓN (editar)", contact = @Contact(name = "Álvaro Martín", email = "alvmarti@emeal.nttdata.com")), servers = {
		@Server(description = "Production server", url = "http://localhost:8080"),
		@Server(description = "Development server", url = "http://localhost:8080") })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}