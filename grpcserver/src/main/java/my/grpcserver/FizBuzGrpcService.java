package my.grpcserver;

import my.rpc.*;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@GRpcService(interceptors = ValidationInterceptor.class)
public class FizBuzGrpcService extends ReactorFizBuzServiceGrpc.FizBuzServiceImplBase {

    @Autowired
    FizBuzCalc cal;

    @Override
    public Mono<FizBuzAnswer> fizBuzOne(Mono<InputNumber> request) {
        return request.map(i ->{
            System.out.println(i);
            var fs = i.getAllFields().keySet().iterator().next();
            System.out.println(fs.getOptions());
            return i.getNum();
        })
                .map(cal::calc)
                .map(a -> FizBuzAnswer.newBuilder().setAnswer(a).build());
    }

    @Override
    public Flux<FizBuzAnswer> fizBuzRange(Mono<FromTo> request) {
        return request.flatMapMany(r -> Flux.range(1, r.getFromTo()))
                .as(cal::calcStream)
                .map(a -> FizBuzAnswer.newBuilder().setAnswer(a).build());
    }

    @Override
    public Mono<FizBuzList> fizBuzBatch(Flux<InputNumber> request) {
        return request.map(InputNumber::getNum)
                .as(cal::calcStream)
                .collectList().map(list -> FizBuzList.newBuilder().addAllAnswer(list).build());
    }

    @Override
    public Flux<FizBuzAnswer> fizBuzMany(Flux<InputNumber> request) {
        return request.map(InputNumber::getNum)
                .as(cal::calcStream)
                .map(a -> FizBuzAnswer.newBuilder().setAnswer(a).build());
    }
}
