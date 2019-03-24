package my.grpcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;

@SpringBootApplication
@EnableEurekaClient
public class GrpcserverApplication {

	public static void main(String[] args) throws Exception {
		//runnGrpcServerWithoutSpring();
		SpringApplication.run(GrpcserverApplication.class, args);
	}

	private static void runnGrpcServerWithoutSpring() throws Exception{

		var builder = (NettyServerBuilder)ServerBuilder
				.forPort(6566)
				.addService(new FizBuzBasicGrpcService(new FizBuzCalc()));
		// netty configuration(SSL, etc) here..
		// builder.sslContext(...);

		var server = builder.build();
		server.start();

		server.awaitTermination();

	}

}

