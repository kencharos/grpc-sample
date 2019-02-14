package my.grpcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GrpcserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcserverApplication.class, args);
	}

}

