package my.grpcserver;

import my.rpc.*;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@GRpcService(interceptors = ValidationInterceptor.class)
public class FizBuzReactorGrpcService extends ReactorFizBuzServiceGrpc.FizBuzServiceImplBase {

    @Autowired
    FizBuzCalc cal;

    // Unary
    @Override
    public Mono<FizBuzAnswer> fizBuzOne(Mono<InputNumber> request) {
        return request.map(InputNumber::getNum)
                .map(cal::calc)
                .map(a -> FizBuzAnswer.newBuilder().setAnswer(a).build());
    }

    // server side stream
    @Override
    public Flux<FizBuzAnswer> fizBuzRange(Mono<FromTo> request) {
        return request.flatMapMany(r -> Flux.range(r.getFrom(), r.getTo()))
                .as(cal::calcStream)
                .map(a -> FizBuzAnswer.newBuilder().setAnswer(a).build());
    }

    // client side stream
    @Override
    public Mono<FizBuzList> fizBuzBatch(Flux<InputNumber> request) {
        return request.map(InputNumber::getNum)
                .as(cal::calcStream)
                .collectList().map(list -> FizBuzList.newBuilder().addAllAnswer(list).build());
    }

    // bi directional stream
    @Override
    public Flux<FizBuzAnswer> fizBuzMany(Flux<InputNumber> request) {
        return request.map(InputNumber::getNum)
                .as(cal::calcStream)
                .map(a -> FizBuzAnswer.newBuilder().setAnswer(a).build());
    }
}
