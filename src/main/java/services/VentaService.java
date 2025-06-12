package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.productos"})
public class VentaService {

	public static void main(String[] args) {
		SpringApplication.run(VentaService.class, args);
	}
	

}
