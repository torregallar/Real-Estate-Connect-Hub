package com.ssdd.Inmobiliaria_CIP;

import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ssdd.Inmobiliaria_CIP.entities.Property;

import java.time.LocalDate;

@SpringBootApplication
public class InmobiliariaCipApplication {

	public static void main(String[] args) {
		SpringApplication.run(InmobiliariaCipApplication.class, args);
	}


}
