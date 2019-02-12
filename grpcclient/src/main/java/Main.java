import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import my.rpc.FizBuzAnswer;
import my.rpc.FizBuzServiceGrpc;
import my.rpc.InputNumber;
import my.rpc.ReactorFizBuzServiceGrpc;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Scanner;

public class Main {

    /*
    基本のgrpc stubは、タイプ別に newStub, newBlockingStub, newFutureStubの３種類が選べる。
    newStubは4タイプ全ての処理が扱えるが、全てStreamObserverを扱う必要がある。
    newBlockingStubで扱えるのは、Unary Typeのみ。
    newFutureStubで扱えるのは、oneToMany Typeのみ。
    つまり、後ろの二つはクライアントから送る値が一つの場合だけ、楽ができる。
     */

    public static void main(String[] args) throws Exception{
        fizbuz30();
        //interactive();

    }

    private static void fizbuz30() throws Exception {

        var chan = createChannel();
        var stb = ReactorFizBuzServiceGrpc.newReactorStub(chan);
        Flux.range(1,30)
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

    private static void interactive() throws Exception{

        var chan = createChannel();
        var stb = ReactorFizBuzServiceGrpc.newReactorStub(chan);

        System.out.println("Interactive FizBuz. Input Numbers.");

        Flux.create(sink -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                sink.next(scanner.nextLine());
            }
        })
                .filter(l -> l.toString().matches("^[0-9]+$"))
                .map(l -> Integer.parseInt(l.toString()))
                .map(n -> InputNumber.newBuilder().setNum(n).build())
                .log()
                .as(stb::fizBuzMany)
                .log()
                .map(res -> {
                    System.out.println("server response.");
                    return res.getAnswer();
                })
                .subscribeOn(Schedulers.newSingle("io"))
                .subscribe(a -> System.out.println("Answer:" + a), e -> e.printStackTrace());
        Thread.sleep(10000L);
    }

    private static ManagedChannel createChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
    }



}
