package Meeting_Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "Meeting_Management/System/Entity")
@SpringBootApplication
public class
MeetingManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingManagementSystemApplication.class, args);
	}

}
