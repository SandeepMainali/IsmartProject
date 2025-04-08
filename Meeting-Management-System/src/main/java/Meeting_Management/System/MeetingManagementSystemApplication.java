package Meeting_Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "Entity")  // Changed from Meeting_Management.System.entity
@EnableJpaRepositories(basePackages = "Repository")  // Changed from Meeting_Management.System.repository
@ComponentScan(basePackages = {"Controller", "Service", "Entity", "Repository", "dto","exception"})  // Include all your packages
public class MeetingManagementSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeetingManagementSystemApplication.class, args);
	}
}