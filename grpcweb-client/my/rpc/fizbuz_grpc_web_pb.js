/**
 * @fileoverview gRPC-Web generated client stub for my.rpc
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');


var google_api_annotations_pb = require('../../google/api/annotations_pb.js')

var validator_validator_pb = require('../../validator/validator_pb.js')
const proto = {};
proto.my = {};
proto.my.rpc = require('./fizbuz_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.my.rpc.FizBuzServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

  /**
   * @private @const {?Object} The credentials to be used to connect
   *    to the server
   */
  this.credentials_ = credentials;

  /**
   * @private @const {?Object} Options for the client
   */
  this.options_ = options;
};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.my.rpc.FizBuzServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!proto.my.rpc.FizBuzServiceClient} The delegate callback based client
   */
  this.delegateClient_ = new proto.my.rpc.FizBuzServiceClient(
      hostname, credentials, options);

};


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.my.rpc.InputNumber,
 *   !proto.my.rpc.FizBuzAnswer>}
 */
const methodInfo_FizBuzService_FizBuzOne = new grpc.web.AbstractClientBase.MethodInfo(
  proto.my.rpc.FizBuzAnswer,
  /** @param {!proto.my.rpc.InputNumber} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.my.rpc.FizBuzAnswer.deserializeBinary
);


/**
 * @param {!proto.my.rpc.InputNumber} request The
 *     request proto
 * @param {!Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.my.rpc.FizBuzAnswer)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.my.rpc.FizBuzAnswer>|undefined}
 *     The XHR Node Readable Stream
 */
proto.my.rpc.FizBuzServiceClient.prototype.fizBuzOne =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/my.rpc.FizBuzService/FizBuzOne',
      request,
      metadata,
      methodInfo_FizBuzService_FizBuzOne,
      callback);
};


/**
 * @param {!proto.my.rpc.InputNumber} request The
 *     request proto
 * @param {!Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.my.rpc.FizBuzAnswer>}
 *     The XHR Node Readable Stream
 */
proto.my.rpc.FizBuzServicePromiseClient.prototype.fizBuzOne =
    function(request, metadata) {
  return new Promise((resolve, reject) => {
    this.delegateClient_.fizBuzOne(
      request, metadata, (error, response) => {
        error ? reject(error) : resolve(response);
      });
  });
};


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.my.rpc.FromTo,
 *   !proto.my.rpc.FizBuzAnswer>}
 */
const methodInfo_FizBuzService_FizBuzRange = new grpc.web.AbstractClientBase.MethodInfo(
  proto.my.rpc.FizBuzAnswer,
  /** @param {!proto.my.rpc.FromTo} request */
  function(request) {
    return request.serializeBinary();
  },
  proto.my.rpc.FizBuzAnswer.deserializeBinary
);


/**
 * @param {!proto.my.rpc.FromTo} request The request proto
 * @param {!Object<string, string>} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.my.rpc.FizBuzAnswer>}
 *     The XHR Node Readable Stream
 */
proto.my.rpc.FizBuzServiceClient.prototype.fizBuzRange =
    function(request, metadata) {
  return this.client_.serverStreaming(this.hostname_ +
      '/my.rpc.FizBuzService/FizBuzRange',
      request,
      metadata,
      methodInfo_FizBuzService_FizBuzRange);
};


/**
 * @param {!proto.my.rpc.FromTo} request The request proto
 * @param {!Object<string, string>} metadata User defined
 *     call metadata
 * @return {!grpc.web.ClientReadableStream<!proto.my.rpc.FizBuzAnswer>}
 *     The XHR Node Readable Stream
 */
proto.my.rpc.FizBuzServicePromiseClient.prototype.fizBuzRange =
    function(request, metadata) {
  return this.delegateClient_.client_.serverStreaming(this.delegateClient_.hostname_ +
      '/my.rpc.FizBuzService/FizBuzRange',
      request,
      metadata,
      methodInfo_FizBuzService_FizBuzRange);
};


module.exports = proto.my.rpc;

