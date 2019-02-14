package my.grpcfront;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import my.rpc.FizBuzAnswer;
import my.rpc.InputNumber;
import my.rpc.ReactorFizBuzServiceGrpc;
import reactor.core.publisher.Mono;


@EnableEurekaClient
@RestController
public class FizBuzController {

    @Autowired
    ManagedChannel channel;

    @GetMapping("/fizbuz/{num}")
    public Mono<String> fizbuz(@PathVariable("num") int num) {

        return ReactorFizBuzServiceGrpc.newReactorStub(channel)
                                .fizBuzOne(InputNumber.newBuilder().setNum(num).build())
                                .map(FizBuzAnswer::getAnswer);

    }

}
