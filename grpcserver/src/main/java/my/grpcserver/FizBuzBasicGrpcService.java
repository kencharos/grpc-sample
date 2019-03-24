package my.grpcserver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.grpc.stub.StreamObserver;
import my.rpc.FizBuzAnswer;
import my.rpc.FizBuzList;
import my.rpc.FizBuzServiceGrpc;
import my.rpc.FromTo;
import my.rpc.InputNumber;

public class FizBuzBasicGrpcService extends FizBuzServiceGrpc.FizBuzServiceImplBase {

    private FizBuzCalc calc;

    public FizBuzBasicGrpcService(FizBuzCalc calc) {
        this.calc = calc;
    }

    // Unary
    @Override
    public void fizBuzOne(InputNumber request, StreamObserver<FizBuzAnswer> responseObserver) {
        String answer = calc.calc(request.getNum());
        // send response to client
        responseObserver.onNext(FizBuzAnswer.newBuilder().setAnswer(answer).build());
        // send RPC finish to client
        responseObserver.onCompleted();
    }

    // server side stream
    @Override
    public void fizBuzRange(FromTo request, StreamObserver<FizBuzAnswer> responseObserver) {

        var anserList = IntStream.range(request.getFrom(), request.getTo())
                 .boxed().map(i -> calc.calc(i)).collect(Collectors.toList());

        // send stream data
        for (String answer : anserList) {
            responseObserver.onNext(FizBuzAnswer.newBuilder().setAnswer(answer).build());
        }
        // send finish
        responseObserver.onCompleted();

    }

    // client side stream
    @Override
    public StreamObserver<InputNumber> fizBuzBatch(StreamObserver<FizBuzList> responseObserver) {

        // store client data.
        var inputNumbers = new ArrayList<InputNumber>();

        return new StreamObserver<InputNumber>() {
            @Override
            public void onNext(InputNumber value) {
                // store input until complete called.
                inputNumbers.add(value);
            }

            // when complete called from client, send server date once, then send finish.
            @Override
            public void onCompleted() {
                var answers = inputNumbers.stream()
                                          .map(i -> i.getNum())
                                          .map(i -> calc.calc(i)).collect(Collectors.toList());
                responseObserver.onNext(FizBuzList.newBuilder().addAllAnswer(answers).build());
                responseObserver.onCompleted();

            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        };
    }

    // Bi Directional Stream
    @Override
    public StreamObserver<InputNumber> fizBuzMany(StreamObserver<FizBuzAnswer> responseObserver) {

        return new StreamObserver<InputNumber>() {
            @Override
            public void onNext(InputNumber value) {
                // one by one data send.
                var answer = calc.calc(value.getNum());
                responseObserver.onNext(FizBuzAnswer.newBuilder().setAnswer(answer).build());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        };
    }
}
