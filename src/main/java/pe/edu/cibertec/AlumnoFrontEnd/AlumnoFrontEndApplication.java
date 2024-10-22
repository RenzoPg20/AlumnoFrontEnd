package pe.edu.cibertec.AlumnoFrontEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlumnoFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnoFrontEndApplication.class, args);
	}

}
