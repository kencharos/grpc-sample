import java.util.Scanner;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Context;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.MethodDescriptor;
import io.grpc.StatusRuntimeException;
import my.rpc.FizBuzAnswer;
import my.rpc.FizBuzServiceGrpc;
import my.rpc.InputNumber;
import my.rpc.ReactorFizBuzServiceGrpc;
import reactor.core.publisher.Flux;

public class Main {

    /*
    基本のgrpc stubは、タイプ別に newStub, newBlockingStub, newFutureStubの３種類が選べる。
    newStubは4タイプ全ての処理が扱えるが、全てStreamObserverを扱う必要がある。
    newBlockingStubで扱えるのは、Unary Typeのみ。
    newFutureStubで扱えるのは、oneToMany Typeのみ。
    つまり、後ろの二つはクライアントから送る値が一つの場合だけ、楽ができる。
     */

    public static void main(String[] args) throws Exception {
        //fizbuz30();
        interactive();

        // test load balancing. please run docker-compose of top directory.
       // testTCPLoadBalancing_ChannelEachRequest();
       // testTCPLoadBalancing_sharedChannel();
       // testL7LoadBalancing_sharedChannel();

        //metadateSample();


    }

    private static void metadateSample() {

        var ch = createChannel();
        var stub = FizBuzServiceGrpc.newBlockingStub(ch)
                        // メタデータの設定は、ClientInterceptor でしか行えない。
                        .withInterceptors(new ClientInterceptor() {
                            @Override
                            public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
                                    MethodDescriptor<ReqT, RespT> method, CallOptions callOptions,
                                    Channel next) {
                                var call = next.newCall(method, callOptions);
                                return  new ForwardingClientCall.SimpleForwardingClientCall<>(call){
                                    @Override
                                    public void start(Listener<RespT> responseListener,
                                                      Metadata headers) {
                                        // add metadata
                                        // skip validation when not-validate set at metadata.
                                        //headers.put(Metadata.Key.of("not-validate", Metadata.ASCII_STRING_MARSHALLER), "true" );
                                        headers.put(Metadata.Key.of("test", Metadata.ASCII_STRING_MARSHALLER), "test" );
                                        super.start(responseListener, headers);
                                    }
                                };

                            }
                        });

        try {
            // num is 1 to 100;
            var res = stub.fizBuzOne(InputNumber.newBuilder().setNum(900).build())
                .getAnswer();
            System.out.println(res);
        }catch (StatusRuntimeException e) {
            System.out.println("error occurred");
            e.printStackTrace();
            for(var key : e.getTrailers().keys()) {
                System.out.println(key +";;"+ e.getTrailers().get(Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER)));
            }
        }
    }

    private static void testTCPLoadBalancing_sharedChannel() {
        // run, docker-compose.yaml

        // connect TCP connection same backend server in TCP load balancing.
        var chan = createChannel();
        for(int i = 0; i < 30; i++) {
            // these request send to same server...
            System.out.println(FizBuzServiceGrpc.newBlockingStub(chan)
                    .fizBuzOne(InputNumber.newBuilder().setNum(i).build())
                    .getAnswer());
        }

        chan.shutdownNow();

    }

    private static void testTCPLoadBalancing_ChannelEachRequest() {

        for(int i = 0; i < 30; i++) {
            // loadbancing in TCP loadbalancing..
            var chan = createChannel();
            System.out.println(FizBuzServiceGrpc.newBlockingStub(chan)
                    .fizBuzOne(InputNumber.newBuilder().setNum(i).build())
                    .getAnswer());

            chan.shutdownNow();

        }
    }

    private static void testL7LoadBalancing_sharedChannel() {
        // run, docker-compose.yaml

        // connect  connection to L7 LoadBalancer(nginx).
        var chan = createChannelForL7LoadBalancer();
        for(int i = 0; i < 30; i++) {
            // In L7 LB, request is distributed.
            System.out.println(FizBuzServiceGrpc.newBlockingStub(chan)
                    .fizBuzOne(InputNumber.newBuilder().setNum(i).build())
                    .getAnswer());
        }

        chan.shutdownNow();

    }


    private static void fizbuz30() throws Exception {

        var chan = createChannel();
        var stb = ReactorFizBuzServiceGrpc.newReactorStub(chan);
        Flux.range(1, 30)
            .map(n -> InputNumber.newBuilder().setNum(n).build())
            .as(stb::fizBuzMany)
            .log()
            .map(res -> {
                System.out.print("server response.");
                return res.getAnswer();
            })
            .log()
            .subscribe(a -> System.out.println("Answer:" + a), e -> e.printStackTrace())
        ;

        Thread.sleep(10000L);
    }

    private static void interactive() throws Exception {

        var chan = createChannel();
        var stb = ReactorFizBuzServiceGrpc.newReactorStub(chan);

        System.out.println("Interactive FizBuz. Input Numbers. number allow 1 to 100.");

        // 標準入力から無限に生成される Hot Stream なので、subscribe無しでデータを流すように、publishとconnectが必要。
        var source = Flux.create(sink -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                sink.next(scanner.nextLine());
            }
        });

        var connectable = source.publish();
        connectable
                .filter(l -> l.toString().matches("^[0-9]+$"))
                .map(l -> Integer.parseInt(l.toString()))
                .map(n -> InputNumber.newBuilder().setNum(n).build())
                //.log()
                .as(stb::fizBuzMany)
                //.log()
                .map(FizBuzAnswer::getAnswer)
                .subscribe(a -> System.out.println("Answer:" + a), e -> e.printStackTrace());

        connectable.connect();

    }

    private static ManagedChannel createChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 6565)
                                    .usePlaintext()
                                    .build();
    }


    private static ManagedChannel createChannelForL7LoadBalancer() {
        return ManagedChannelBuilder.forAddress("localhost", 6564)
                .usePlaintext()
                .build();
    }

}
