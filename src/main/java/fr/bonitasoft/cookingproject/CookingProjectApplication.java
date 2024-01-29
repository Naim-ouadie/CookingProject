package fr.bonitasoft.cookingproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cooking Project API", version = "1.0", description = "APIs to handle receips, Authors, Ingredients and Keywords"))
public class CookingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookingProjectApplication.class, args);
    }

}
