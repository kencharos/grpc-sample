package my.grpcfront;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class ChannelManager {

    @Autowired
    private EurekaClient client;

    private AtomicReference<ManagedChannel> instance = new AtomicReference<>();

    public ManagedChannel channel() {
        if (instance.get() == null) {
            instance.set(createChannel());
        }

        return instance.get();
    }

    private ManagedChannel createChannel() {

        final InstanceInfo instanceInfo = client.getNextServerFromEureka("grpc", false);
        System.out.println("Fetch from eureka," + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort());
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort())
                                                            .usePlaintext()
                                                            .build();
        return channel;
    }
}
