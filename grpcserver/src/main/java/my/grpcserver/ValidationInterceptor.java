package my.grpcserver;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class ValidationInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
                                                      ServerCallHandler<ReqT, RespT> next) {

        System.out.println(headers);
        headers.keys().forEach(s -> System.out.println(s));
        System.out.println(call);

        System.out.println(call.getAttributes());
        var existsSomeHeader = headers.keys().stream().anyMatch(k -> k.contains("hoge"));

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(next.startCall(call, headers)) {

            @Override
            public void onMessage(ReqT message) {
                if (existsSomeHeader) {

                    super.onMessage(message);
                } else {
                    Status status = Status.INVALID_ARGUMENT.withDescription("Validation test");
                    var metadata = new Metadata();
                    metadata.put(Metadata.Key.of("errormessage", Metadata.ASCII_STRING_MARSHALLER), "hoge hedder nothing");
                    call.close(status, metadata);
                }
            }

        };
    }
}

