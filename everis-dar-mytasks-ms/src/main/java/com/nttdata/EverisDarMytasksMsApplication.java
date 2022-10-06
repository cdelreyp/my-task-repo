package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EverisDarMytasksMsApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EverisDarMytasksMsApplication.class, args);
		
		//System.out.println("PRUEBA");
	}

}
