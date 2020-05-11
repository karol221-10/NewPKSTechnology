package pl.kompikownia.pksmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class PKSManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PKSManagerApplication.class, args);
    }

}
