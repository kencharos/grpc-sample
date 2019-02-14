package my.grpcfront;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;

import io.grpc.LoadBalancerRegistry;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import my.eureka.EurekaNameResolverProvider;

@Component
public class ChannelManager {

    @Autowired
    private EurekaClient client;
    @Autowired
    private EurekaClientConfig config;

    private AtomicReference<ManagedChannel> instance = new AtomicReference<>();

    public ManagedChannel channel() {
        if (instance.get() == null) {
            instance.set(createChannel());
        }

        return instance.get();
    }

    private ManagedChannel createChannel() {

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("eureka://" + "grpc")
                                                            .nameResolverFactory(new EurekaNameResolverProvider(config))
                                                            // use all service round robin in single channel
                                                            .defaultLoadBalancingPolicy("round_robin")
                                                            .usePlaintext()
                                                            .build();
        return channel;
    }
}
