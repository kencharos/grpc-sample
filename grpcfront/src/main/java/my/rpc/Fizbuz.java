// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fizbuz.proto

package my.rpc;

public final class Fizbuz {
  private Fizbuz() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_my_rpc_InputNumber_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_my_rpc_InputNumber_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_my_rpc_FromTo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_my_rpc_FromTo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_my_rpc_FizBuzAnswer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_my_rpc_FizBuzAnswer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_my_rpc_FizBuzList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_my_rpc_FizBuzList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\014fizbuz.proto\022\006my.rpc\"\032\n\013InputNumber\022\013\n" +
      "\003num\030\001 \001(\005\"\030\n\006FromTo\022\016\n\006fromTo\030\001 \001(\005\"\036\n\014" +
      "FizBuzAnswer\022\016\n\006answer\030\001 \001(\t\"\034\n\nFizBuzLi" +
      "st\022\016\n\006answer\030\001 \003(\t2\375\001\n\rFizBuzService\0228\n\t" +
      "FizBuzOne\022\023.my.rpc.InputNumber\032\024.my.rpc." +
      "FizBuzAnswer\"\000\0227\n\013FizBuzRange\022\016.my.rpc.F" +
      "romTo\032\024.my.rpc.FizBuzAnswer\"\0000\001\022:\n\013FizBu" +
      "zBatch\022\023.my.rpc.InputNumber\032\022.my.rpc.Fiz" +
      "BuzList\"\000(\001\022=\n\nFizBuzMany\022\023.my.rpc.Input" +
      "Number\032\024.my.rpc.FizBuzAnswer\"\000(\0010\001B\n\n\006my" +
      ".rpcP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_my_rpc_InputNumber_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_my_rpc_InputNumber_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_my_rpc_InputNumber_descriptor,
        new String[] { "Num", });
    internal_static_my_rpc_FromTo_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_my_rpc_FromTo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_my_rpc_FromTo_descriptor,
        new String[] { "FromTo", });
    internal_static_my_rpc_FizBuzAnswer_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_my_rpc_FizBuzAnswer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_my_rpc_FizBuzAnswer_descriptor,
        new String[] { "Answer", });
    internal_static_my_rpc_FizBuzList_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_my_rpc_FizBuzList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_my_rpc_FizBuzList_descriptor,
        new String[] { "Answer", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
