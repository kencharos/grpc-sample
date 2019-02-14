package my.grpcfront;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@EnableEurekaClient
@SpringBootApplication
public class GrpcfrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcfrontApplication.class, args);
    }


}

