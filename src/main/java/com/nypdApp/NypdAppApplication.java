package com.nypdApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Application Controller", description = "Rest controller", version = "1"))
@SpringBootApplication
public class NypdAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NypdAppApplication.class, args);
    }

}
