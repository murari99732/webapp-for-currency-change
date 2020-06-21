package MyNewProject.CloudServerImplementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudServerImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudServerImplementationApplication.class, args);
	}

}
