import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
       // interactive();

        // test load balancing. please run docker-compose of top directory.
        testTCPLoadBalancing_ChannelEachRequest();
        testTCPLoadBalancing_sharedChannel();
        testL7LoadBalancing_sharedChannel();

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

        System.out.println("Interactive FizBuz. Input Numbers.");

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
