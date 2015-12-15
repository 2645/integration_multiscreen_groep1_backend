package be.ehb.restservermetdatabase.webservice;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@EnableAutoConfiguration
public class Webservice {
    public static void main(String[] args) {
        SpringApplication.run(Webservice.class, args);
    }
}
