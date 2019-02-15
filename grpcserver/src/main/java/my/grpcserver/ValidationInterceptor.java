package my.grpcserver;

import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class ValidationInterceptor implements ServerInterceptor {

    private SampleValidator validator = new SampleValidator();

    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
                                                      ServerCallHandler<ReqT, RespT> next) {

        //headers.keys().forEach(s -> System.out.println(s));
        // test for metadata setting.
        var notValidate = headers.keys().stream().anyMatch(k -> k.contains("not-validate"));

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(next.startCall(call, headers)) {

            @Override
            public void onMessage(ReqT message) {
                // onMessageを上書きして、gRPCサービス呼び出し前にフックを仕掛けることができる。
                if (notValidate) {
                    System.out.println("validation skip because not-validate metadata set");
                    super.onMessage(message);
                    return;
                }

                var results = validator.validate(message);

                if (results.isEmpty()) {
                    super.onMessage(message);
                } else {
                    // onMessageを呼ばずに。close を行うことでgRPCサービスを実行せずに終了できる。ステータスの値で呼び出し側の正常・例外が決まり、
                    // Metadataで附帯情報を設定できる。
                    Status status = Status.INVALID_ARGUMENT.withDescription("Validation error");
                    var metadata = new Metadata();
                    metadata.put(Metadata.Key.of("errormessage", Metadata.ASCII_STRING_MARSHALLER), results.get().toString());
                    // ForwardingServerCall.SimpleForwardingServerCall で、call をラップすることで、
                    // close の挙動の変更もできる。例えば、サービス内で予期せぬ例外が起きた時のグローバル例外ハンドラのようなものが実現できる。
                    // see https://github.com/saturnism/grpc-java-by-example/blob/master/error-handling-example/error-server/src/main/java/com/example/grpc/server/UnknownStatusDescriptionInterceptor.java
                    call.close(status, metadata);
                }
            }

        };
    }
}

