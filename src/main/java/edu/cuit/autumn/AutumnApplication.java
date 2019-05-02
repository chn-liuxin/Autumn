package edu.cuit.autumn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "edu.cuit.autumn.*")
@MapperScan(basePackages = "edu.cuit.autumn.*")
public class AutumnApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutumnApplication.class, args);
    }

}
