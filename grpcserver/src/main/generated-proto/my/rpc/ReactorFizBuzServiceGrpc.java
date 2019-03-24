package my.rpc;

import static my.rpc.FizBuzServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: my/rpc/fizbuz.proto")
public final class ReactorFizBuzServiceGrpc {
    private ReactorFizBuzServiceGrpc() {}

    public static ReactorFizBuzServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorFizBuzServiceStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * <pre>
     */
    public static final class ReactorFizBuzServiceStub extends io.grpc.stub.AbstractStub<ReactorFizBuzServiceStub> {
        private FizBuzServiceGrpc.FizBuzServiceStub delegateStub;

        private ReactorFizBuzServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = FizBuzServiceGrpc.newStub(channel);
        }

        private ReactorFizBuzServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = FizBuzServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorFizBuzServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorFizBuzServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         *  FizBuzAnser
         * 
         *  1 to 100 number allowed.
         * <pre>
         */
        public reactor.core.publisher.Mono<my.rpc.FizBuzAnswer> fizBuzOne(reactor.core.publisher.Mono<my.rpc.InputNumber> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::fizBuzOne);
        }

        public reactor.core.publisher.Flux<my.rpc.FizBuzAnswer> fizBuzRange(reactor.core.publisher.Mono<my.rpc.FromTo> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactorRequest, delegateStub::fizBuzRange);
        }

        public reactor.core.publisher.Mono<my.rpc.FizBuzList> fizBuzBatch(reactor.core.publisher.Flux<my.rpc.InputNumber> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToOne(reactorRequest, delegateStub::fizBuzBatch);
        }

        public reactor.core.publisher.Flux<my.rpc.FizBuzAnswer> fizBuzMany(reactor.core.publisher.Flux<my.rpc.InputNumber> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToMany(reactorRequest, delegateStub::fizBuzMany);
        }

        /**
         * <pre>
         *  FizBuzAnser
         * 
         *  1 to 100 number allowed.
         * <pre>
         */
        public reactor.core.publisher.Mono<my.rpc.FizBuzAnswer> fizBuzOne(my.rpc.InputNumber reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::fizBuzOne);
        }

        public reactor.core.publisher.Flux<my.rpc.FizBuzAnswer> fizBuzRange(my.rpc.FromTo reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::fizBuzRange);
        }

    }

    /**
     * <pre>
     *  The greeting service definition.
     * <pre>
     */
    public static abstract class FizBuzServiceImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         *  FizBuzAnser
         * 
         *  1 to 100 number allowed.
         * <pre>
         */
        public reactor.core.publisher.Mono<my.rpc.FizBuzAnswer> fizBuzOne(reactor.core.publisher.Mono<my.rpc.InputNumber> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Flux<my.rpc.FizBuzAnswer> fizBuzRange(reactor.core.publisher.Mono<my.rpc.FromTo> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<my.rpc.FizBuzList> fizBuzBatch(reactor.core.publisher.Flux<my.rpc.InputNumber> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Flux<my.rpc.FizBuzAnswer> fizBuzMany(reactor.core.publisher.Flux<my.rpc.InputNumber> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            my.rpc.FizBuzServiceGrpc.getFizBuzOneMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            my.rpc.InputNumber,
                                            my.rpc.FizBuzAnswer>(
                                            this, METHODID_FIZ_BUZ_ONE)))
                    .addMethod(
                            my.rpc.FizBuzServiceGrpc.getFizBuzRangeMethod(),
                            asyncServerStreamingCall(
                                    new MethodHandlers<
                                            my.rpc.FromTo,
                                            my.rpc.FizBuzAnswer>(
                                            this, METHODID_FIZ_BUZ_RANGE)))
                    .addMethod(
                            my.rpc.FizBuzServiceGrpc.getFizBuzBatchMethod(),
                            asyncClientStreamingCall(
                                    new MethodHandlers<
                                            my.rpc.InputNumber,
                                            my.rpc.FizBuzList>(
                                            this, METHODID_FIZ_BUZ_BATCH)))
                    .addMethod(
                            my.rpc.FizBuzServiceGrpc.getFizBuzManyMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            my.rpc.InputNumber,
                                            my.rpc.FizBuzAnswer>(
                                            this, METHODID_FIZ_BUZ_MANY)))
                    .build();
        }
    }

    private static final int METHODID_FIZ_BUZ_ONE = 0;
    private static final int METHODID_FIZ_BUZ_RANGE = 1;
    private static final int METHODID_FIZ_BUZ_BATCH = 2;
    private static final int METHODID_FIZ_BUZ_MANY = 3;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final FizBuzServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(FizBuzServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_FIZ_BUZ_ONE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((my.rpc.InputNumber) request,
                            (io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer>) responseObserver,
                            serviceImpl::fizBuzOne);
                    break;
                case METHODID_FIZ_BUZ_RANGE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToMany((my.rpc.FromTo) request,
                            (io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer>) responseObserver,
                            serviceImpl::fizBuzRange);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_FIZ_BUZ_BATCH:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToOne(
                            (io.grpc.stub.StreamObserver<my.rpc.FizBuzList>) responseObserver,
                            serviceImpl::fizBuzBatch);
                case METHODID_FIZ_BUZ_MANY:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToMany(
                            (io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer>) responseObserver,
                            serviceImpl::fizBuzMany);
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
