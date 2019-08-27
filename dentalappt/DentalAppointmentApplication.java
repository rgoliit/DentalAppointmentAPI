package dentalappt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("dentalappt.persistance") 
@EntityScan("dentalappt.model")
@SpringBootApplication
public class DentalAppointmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(DentalAppointmentApplication.class, args);
	}
}
