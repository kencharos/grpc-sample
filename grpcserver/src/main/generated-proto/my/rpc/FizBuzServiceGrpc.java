package my.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: my/rpc/fizbuz.proto")
public final class FizBuzServiceGrpc {

  private FizBuzServiceGrpc() {}

  public static final String SERVICE_NAME = "my.rpc.FizBuzService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<my.rpc.InputNumber,
      my.rpc.FizBuzAnswer> getFizBuzOneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FizBuzOne",
      requestType = my.rpc.InputNumber.class,
      responseType = my.rpc.FizBuzAnswer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<my.rpc.InputNumber,
      my.rpc.FizBuzAnswer> getFizBuzOneMethod() {
    io.grpc.MethodDescriptor<my.rpc.InputNumber, my.rpc.FizBuzAnswer> getFizBuzOneMethod;
    if ((getFizBuzOneMethod = FizBuzServiceGrpc.getFizBuzOneMethod) == null) {
      synchronized (FizBuzServiceGrpc.class) {
        if ((getFizBuzOneMethod = FizBuzServiceGrpc.getFizBuzOneMethod) == null) {
          FizBuzServiceGrpc.getFizBuzOneMethod = getFizBuzOneMethod = 
              io.grpc.MethodDescriptor.<my.rpc.InputNumber, my.rpc.FizBuzAnswer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "my.rpc.FizBuzService", "FizBuzOne"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.InputNumber.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.FizBuzAnswer.getDefaultInstance()))
                  .setSchemaDescriptor(new FizBuzServiceMethodDescriptorSupplier("FizBuzOne"))
                  .build();
          }
        }
     }
     return getFizBuzOneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<my.rpc.FromTo,
      my.rpc.FizBuzAnswer> getFizBuzRangeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FizBuzRange",
      requestType = my.rpc.FromTo.class,
      responseType = my.rpc.FizBuzAnswer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<my.rpc.FromTo,
      my.rpc.FizBuzAnswer> getFizBuzRangeMethod() {
    io.grpc.MethodDescriptor<my.rpc.FromTo, my.rpc.FizBuzAnswer> getFizBuzRangeMethod;
    if ((getFizBuzRangeMethod = FizBuzServiceGrpc.getFizBuzRangeMethod) == null) {
      synchronized (FizBuzServiceGrpc.class) {
        if ((getFizBuzRangeMethod = FizBuzServiceGrpc.getFizBuzRangeMethod) == null) {
          FizBuzServiceGrpc.getFizBuzRangeMethod = getFizBuzRangeMethod = 
              io.grpc.MethodDescriptor.<my.rpc.FromTo, my.rpc.FizBuzAnswer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "my.rpc.FizBuzService", "FizBuzRange"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.FromTo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.FizBuzAnswer.getDefaultInstance()))
                  .setSchemaDescriptor(new FizBuzServiceMethodDescriptorSupplier("FizBuzRange"))
                  .build();
          }
        }
     }
     return getFizBuzRangeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<my.rpc.InputNumber,
      my.rpc.FizBuzList> getFizBuzBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FizBuzBatch",
      requestType = my.rpc.InputNumber.class,
      responseType = my.rpc.FizBuzList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<my.rpc.InputNumber,
      my.rpc.FizBuzList> getFizBuzBatchMethod() {
    io.grpc.MethodDescriptor<my.rpc.InputNumber, my.rpc.FizBuzList> getFizBuzBatchMethod;
    if ((getFizBuzBatchMethod = FizBuzServiceGrpc.getFizBuzBatchMethod) == null) {
      synchronized (FizBuzServiceGrpc.class) {
        if ((getFizBuzBatchMethod = FizBuzServiceGrpc.getFizBuzBatchMethod) == null) {
          FizBuzServiceGrpc.getFizBuzBatchMethod = getFizBuzBatchMethod = 
              io.grpc.MethodDescriptor.<my.rpc.InputNumber, my.rpc.FizBuzList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "my.rpc.FizBuzService", "FizBuzBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.InputNumber.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.FizBuzList.getDefaultInstance()))
                  .setSchemaDescriptor(new FizBuzServiceMethodDescriptorSupplier("FizBuzBatch"))
                  .build();
          }
        }
     }
     return getFizBuzBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<my.rpc.InputNumber,
      my.rpc.FizBuzAnswer> getFizBuzManyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FizBuzMany",
      requestType = my.rpc.InputNumber.class,
      responseType = my.rpc.FizBuzAnswer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<my.rpc.InputNumber,
      my.rpc.FizBuzAnswer> getFizBuzManyMethod() {
    io.grpc.MethodDescriptor<my.rpc.InputNumber, my.rpc.FizBuzAnswer> getFizBuzManyMethod;
    if ((getFizBuzManyMethod = FizBuzServiceGrpc.getFizBuzManyMethod) == null) {
      synchronized (FizBuzServiceGrpc.class) {
        if ((getFizBuzManyMethod = FizBuzServiceGrpc.getFizBuzManyMethod) == null) {
          FizBuzServiceGrpc.getFizBuzManyMethod = getFizBuzManyMethod = 
              io.grpc.MethodDescriptor.<my.rpc.InputNumber, my.rpc.FizBuzAnswer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "my.rpc.FizBuzService", "FizBuzMany"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.InputNumber.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  my.rpc.FizBuzAnswer.getDefaultInstance()))
                  .setSchemaDescriptor(new FizBuzServiceMethodDescriptorSupplier("FizBuzMany"))
                  .build();
          }
        }
     }
     return getFizBuzManyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FizBuzServiceStub newStub(io.grpc.Channel channel) {
    return new FizBuzServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FizBuzServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FizBuzServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FizBuzServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FizBuzServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class FizBuzServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void fizBuzOne(my.rpc.InputNumber request,
        io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer> responseObserver) {
      asyncUnimplementedUnaryCall(getFizBuzOneMethod(), responseObserver);
    }

    /**
     */
    public void fizBuzRange(my.rpc.FromTo request,
        io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer> responseObserver) {
      asyncUnimplementedUnaryCall(getFizBuzRangeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.rpc.InputNumber> fizBuzBatch(
        io.grpc.stub.StreamObserver<my.rpc.FizBuzList> responseObserver) {
      return asyncUnimplementedStreamingCall(getFizBuzBatchMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.rpc.InputNumber> fizBuzMany(
        io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer> responseObserver) {
      return asyncUnimplementedStreamingCall(getFizBuzManyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFizBuzOneMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                my.rpc.InputNumber,
                my.rpc.FizBuzAnswer>(
                  this, METHODID_FIZ_BUZ_ONE)))
          .addMethod(
            getFizBuzRangeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                my.rpc.FromTo,
                my.rpc.FizBuzAnswer>(
                  this, METHODID_FIZ_BUZ_RANGE)))
          .addMethod(
            getFizBuzBatchMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                my.rpc.InputNumber,
                my.rpc.FizBuzList>(
                  this, METHODID_FIZ_BUZ_BATCH)))
          .addMethod(
            getFizBuzManyMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                my.rpc.InputNumber,
                my.rpc.FizBuzAnswer>(
                  this, METHODID_FIZ_BUZ_MANY)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class FizBuzServiceStub extends io.grpc.stub.AbstractStub<FizBuzServiceStub> {
    private FizBuzServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FizBuzServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FizBuzServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FizBuzServiceStub(channel, callOptions);
    }

    /**
     */
    public void fizBuzOne(my.rpc.InputNumber request,
        io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFizBuzOneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fizBuzRange(my.rpc.FromTo request,
        io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFizBuzRangeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.rpc.InputNumber> fizBuzBatch(
        io.grpc.stub.StreamObserver<my.rpc.FizBuzList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getFizBuzBatchMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<my.rpc.InputNumber> fizBuzMany(
        io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getFizBuzManyMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class FizBuzServiceBlockingStub extends io.grpc.stub.AbstractStub<FizBuzServiceBlockingStub> {
    private FizBuzServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FizBuzServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FizBuzServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FizBuzServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public my.rpc.FizBuzAnswer fizBuzOne(my.rpc.InputNumber request) {
      return blockingUnaryCall(
          getChannel(), getFizBuzOneMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<my.rpc.FizBuzAnswer> fizBuzRange(
        my.rpc.FromTo request) {
      return blockingServerStreamingCall(
          getChannel(), getFizBuzRangeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class FizBuzServiceFutureStub extends io.grpc.stub.AbstractStub<FizBuzServiceFutureStub> {
    private FizBuzServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FizBuzServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FizBuzServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FizBuzServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<my.rpc.FizBuzAnswer> fizBuzOne(
        my.rpc.InputNumber request) {
      return futureUnaryCall(
          getChannel().newCall(getFizBuzOneMethod(), getCallOptions()), request);
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
          serviceImpl.fizBuzOne((my.rpc.InputNumber) request,
              (io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer>) responseObserver);
          break;
        case METHODID_FIZ_BUZ_RANGE:
          serviceImpl.fizBuzRange((my.rpc.FromTo) request,
              (io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIZ_BUZ_BATCH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.fizBuzBatch(
              (io.grpc.stub.StreamObserver<my.rpc.FizBuzList>) responseObserver);
        case METHODID_FIZ_BUZ_MANY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.fizBuzMany(
              (io.grpc.stub.StreamObserver<my.rpc.FizBuzAnswer>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FizBuzServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FizBuzServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return my.rpc.Fizbuz.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FizBuzService");
    }
  }

  private static final class FizBuzServiceFileDescriptorSupplier
      extends FizBuzServiceBaseDescriptorSupplier {
    FizBuzServiceFileDescriptorSupplier() {}
  }

  private static final class FizBuzServiceMethodDescriptorSupplier
      extends FizBuzServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FizBuzServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FizBuzServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FizBuzServiceFileDescriptorSupplier())
              .addMethod(getFizBuzOneMethod())
              .addMethod(getFizBuzRangeMethod())
              .addMethod(getFizBuzBatchMethod())
              .addMethod(getFizBuzManyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
