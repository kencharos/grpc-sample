package my.eureka;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.Application;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.stream.Collectors;

/*
ref https://medium.com/@mykidong/howto-grpc-java-client-side-load-balancing-using-consul-8f729668d3f8
 */
public class EurekaNameResolver extends NameResolver {

    private final String serviceName;
    private final String portMetaData;
    private final EurekaClient eurekaClient;

    private Listener listener;

    public EurekaNameResolver(EurekaClientConfig clientConfig, URI targetUri, String portMetaData) {
        this.portMetaData = portMetaData;
        serviceName = targetUri.getAuthority();

        MyDataCenterInstanceConfig instanceConfig = new MyDataCenterInstanceConfig();

        ApplicationInfoManager applicationInfoManager = initializeApplicationInfoManager(instanceConfig);

        eurekaClient = new DiscoveryClient(applicationInfoManager, clientConfig);
    }

    private static synchronized ApplicationInfoManager initializeApplicationInfoManager(EurekaInstanceConfig instanceConfig) {
        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
        return new ApplicationInfoManager(instanceConfig, instanceInfo);
    }

    @Override
    public String getServiceAuthority() {
        return serviceName;
    }



    @Override
    public void start(Listener listener) {
        this.listener = listener;
        update(listener);
    }

    @Override
    public void refresh() {
        System.out.println("NameResolver Refresh Called.");
        update(listener);
    }

    private void update(Listener listener) {
        System.out.println("NameResolver starting..");
        Application application = eurekaClient.getApplication(serviceName);
        var instances = application.getInstances();
        if (instances != null) {
            var resolvedServerInfos = application.getInstances().stream().map(instanceInfo -> {
                int port;
                if (portMetaData != null) {
                    String s = instanceInfo.getMetadata().get(portMetaData);
                    port = Integer.parseInt(instanceInfo.getMetadata().get(portMetaData));
                } else {
                    port = instanceInfo.getPort();
                }
                return new EquivalentAddressGroup(new InetSocketAddress(instanceInfo.getHostName(), port), Attributes.EMPTY);
            }).collect(Collectors.toList());

            listener.onAddresses(resolvedServerInfos, Attributes.EMPTY);
        } else {
            listener.onAddresses(Collections.emptyList(), Attributes.EMPTY);
        }
    }

    @Override
    public void shutdown() {

    }
}
