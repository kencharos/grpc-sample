import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;
import my.rpc.FizBuzAnswer;
import my.rpc.FizBuzServiceGrpc;
import my.rpc.FizBuzServiceGrpc.FizBuzServiceBlockingStub;
import my.rpc.InputNumber;

public class StubTest {

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private final FizBuzServiceGrpc.FizBuzServiceImplBase serviceImpl =
            mock(FizBuzServiceGrpc.FizBuzServiceImplBase.class, delegatesTo(new FizBuzServiceGrpc.FizBuzServiceImplBase() {
                @Override
                public void fizBuzOne(InputNumber request, StreamObserver<FizBuzAnswer> responseObserver) {
                    responseObserver.onNext(FizBuzAnswer.newBuilder().setAnswer("A").build());
                    responseObserver.onCompleted();
                }
            }));
    private FizBuzServiceBlockingStub blockStub;

    @Before
    public void setUp() throws Exception {
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();

        // Create a server, add service, start, and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessServerBuilder
                                     .forName(serverName).directExecutor().addService(serviceImpl).build().start());

        // Create a client channel and register for automatic graceful shutdown.
        ManagedChannel channel = grpcCleanup.register(
                InProcessChannelBuilder.forName(serverName).directExecutor().build());

        blockStub = FizBuzServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void testBlocking() {

        var r = blockStub.fizBuzOne(InputNumber.newBuilder().setNum(1).build());

        Assert.assertEquals("A", r.getAnswer());
    }

}
