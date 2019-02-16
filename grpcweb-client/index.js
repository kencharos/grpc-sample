const message = require('./my/rpc/fizbuz_pb.js');
const cl = require('./my/rpc/fizbuz_grpc_web_pb.js');

var client = new cl.FizBuzServicePromiseClient('http://localhost:8080'); // envoy proxy.

var request = new message.InputNumber();
request.setNum(12);

client.fizBuzOne(request, {})
.then(res => {console.log(res); window.alert("answer of 12 is " + res.getAnswer());})
.catch(e => console.log(e))

