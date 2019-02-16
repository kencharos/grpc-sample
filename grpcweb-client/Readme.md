# grpc-web client

## build js stub

+ install protoc
+ install grpc-web-code-gen
    + https://github.com/grpc/grpc-web/tree/master/net/grpc/gateway/examples/helloworld#generate-protobuf-messages-and-client-service-stub
    + downlod relase from below.  
    ```
sudo mv ~/Downloads/protoc-gen-grpc-web-1.0.3-darwin-x86_64 \
/usr/local/bin/protoc-gen-grpc-web

chmod +x /usr/local/bin/protoc-gen-grpc-web
```

対象のproto以外の、import しているものも別途生成が必要。

```
#third party proto 
 protoc -I=. \
  --js_out=import_style=commonjs:. \
  --grpc-web_out=import_style=commonjs,mode=grpcwebtext:. \
  --proto_path=$GOPATH/src/github.com/grpc-ecosystem/grpc-gateway/third_party/googleapis \
  google/api/annotations.proto 


 protoc -I=. \
  --js_out=import_style=commonjs:. \
  --grpc-web_out=import_style=commonjs,mode=grpcwebtext:. \
  --proto_path=$GOPATH/src/github.com/grpc-ecosystem/grpc-gateway/third_party/googleapis \
  google/api/http.proto 
```

```
 protoc -I=. \
  -I$GOPATH/src/github.com/grpc-ecosystem/grpc-gateway/third_party/googleapis \
  --js_out=import_style=commonjs:. \
  --grpc-web_out=import_style=commonjs,mode=grpcwebtext:. \
  --proto_path=../grpcserver/src/main/proto \
  my/rpc/fizbuz.proto 
```

```
 protoc -I=. \
  -I$GOPATH/src/github.com/grpc-ecosystem/grpc-gateway/third_party/googleapis \
  --js_out=import_style=commonjs:. \
  --grpc-web_out=import_style=commonjs,mode=grpcwebtext:. \
  --proto_path=../grpcserver/src/main/proto \
  validator/validator.proto 
```

generate js source files.

## useage

`npm install`
`npx webpack index.js`

open index.html