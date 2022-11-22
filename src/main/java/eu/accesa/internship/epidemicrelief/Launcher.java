package eu.accesa.internship.epidemicrelief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:epidemicrelief-spring.xml")
public class Launcher {
//TODO: Test the application
    //TODO: NU MERGE READY LA PACKAGE
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}