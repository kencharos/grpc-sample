import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Spliterators;
import java.util.concurrent.CountDownLatch;

import com.google.common.util.concurrent.ListenableFuture;

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
import io.grpc.stub.StreamObserver;
import my.rpc.FizBuzAnswer;
import my.rpc.FizBuzList;
import my.rpc.FizBuzServiceGrpc;
import my.rpc.FromTo;
import my.rpc.InputNumber;
import my.rpc.ReactorFizBuzServiceGrpc;
import reactor.core.publisher.Flux;

public class Main {

    /*
    基本のgrpc stubは、タイプ別に newStub, newBlockingStub, newFutureStubの３種類が選べる。
    newStubは4タイプ全ての処理が扱えるが、全てStreamObserverを扱う必要がある。
    newBlockingStubで扱えるのは、Unary Typeと ServerSideStream のみ(クライアントの引数が単一のもの)。
    newFutureStubで扱えるのは、Unary Typeのみ。
    つまり、後ろの二つはクライアントから送る値が一つの場合だけ、楽ができる。
     */

    public static void main(String[] args) throws Exception {
        //testAccessByBasicStub();
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

    private static void testAccessByBasicStub() throws Exception{
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 6566)
                .usePlaintext().build();

        var blockStub = FizBuzServiceGrpc.newBlockingStub(channel);
        var futureStub = FizBuzServiceGrpc.newFutureStub(channel);
        var stub = FizBuzServiceGrpc.newStub(channel);

        // Unary
        System.out.println("--unaly");

        FizBuzAnswer a1 = blockStub.fizBuzOne(InputNumber.newBuilder().setNum(1).build());
        System.out.println(a1.getAnswer());

        ListenableFuture<FizBuzAnswer> a1_1 = futureStub.fizBuzOne(InputNumber.newBuilder().setNum(1).build());
        System.out.println(a1_1.get().getAnswer());

        // server side stream
        // send one message, receive messageom server.
        System.out.println("--server side stream--");
        Iterator<FizBuzAnswer> a2 = blockStub.fizBuzRange(FromTo.newBuilder().setFrom(2).setTo(20).build());
        while (a2.hasNext()) {
            System.out.println(a2.next().getAnswer());
        }

        CountDownLatch wait1 = new CountDownLatch(1);

        // client side stream
        System.out.println("--client side stream--");

        // setup callback responseObserver first.
        var requestSender =  stub.fizBuzBatch(new StreamObserver<FizBuzList>() {

            @Override
            public void onNext(FizBuzList value) {
                // called after requestSender send all message and complete.
                System.out.println("received answers");
                for(int i = 0; i < value.getAnswerCount();i++) {
                    System.out.println(value.getAnswer(i));
                }
            }

            @Override
            public void onCompleted() {
                wait1.countDown();
            }
            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        });
        // send request and send complete.
        requestSender.onNext(InputNumber.newBuilder().setNum(11).build());
        requestSender.onNext(InputNumber.newBuilder().setNum(10).build());
        requestSender.onNext(InputNumber.newBuilder().setNum(27).build());
        requestSender.onCompleted();
        // and execute callback's onNext once..
        wait1.await();

        // bi directional stream
        System.out.println("--Bi Directional Stream--");
        CountDownLatch wait2 = new CountDownLatch(1);

        var biRequestSender = stub.fizBuzMany(new StreamObserver<FizBuzAnswer>() {
            @Override
            public void onNext(FizBuzAnswer value) {
                System.out.println("receive answer " + value.getAnswer());
            }
            @Override
            public void onCompleted() {
                wait2.countDown();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

        });
        // receive server data each onNext.
        biRequestSender.onNext(InputNumber.newBuilder().setNum(3).build());
        biRequestSender.onNext(InputNumber.newBuilder().setNum(1).build());
        biRequestSender.onNext(InputNumber.newBuilder().setNum(80).build());
        biRequestSender.onNext(InputNumber.newBuilder().setNum(90).build());

        // send finish to server.
        biRequestSender.onCompleted();

        wait2.await();

        channel.shutdown();

    }

}
