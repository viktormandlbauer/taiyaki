package at.technikum.taiyaki.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@EnableJpaAuditing
@SpringBootApplication
public class TaiyakiBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaiyakiBackendApplication.class, args);
    }
}
