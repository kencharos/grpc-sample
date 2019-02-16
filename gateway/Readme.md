# gRPC-gateway

see https://github.com/grpc-ecosystem/grpc-gateway

## Pre requirements

+ Go 
+ ProtocolBuffers

```
# protbuf
## prepare build tool
brew install autoconf automake libtool

## install protbuf
mkdir tmp
cd tmp
git clone https://github.com/protocolbuffers/protobuf
cd protobuf
./autogen.sh
./configure
make
make check
sudo make install

## check
which protoc
cd ../../
rm -rf tmp
```
# go
```
brew install go
```
set GOPATH
add follows in ~/.path_profile

```
export GOPATH=$HOME/go
export PATH=$GOPATH/bin:$PATH
```

## install go dependencies.

```
go get -u github.com/grpc-ecosystem/grpc-gateway/protoc-gen-grpc-gateway
go get -u github.com/grpc-ecosystem/grpc-gateway/protoc-gen-swagger
go get -u github.com/golang/protobuf/protoc-gen-go
go get -u google.golang.org/grpc
```

and protoeasy is useful.
https://github.com/peter-edge/protoeasy-go

but this tool yet gen swagger.

```
go get -v go.pedge.io/protoeasy/cmd/protoeasy
```
## generate go source

```
# in gateway dir
protoeasy --go --grpc --grpc-gateway --out . --go-import-path=../../ --go ../grpcserver/src/main/proto
```

### swagger

protoeasy not implement swagger yet.
(but there is fork. https://github.com/peter-edge/protoeasy-go/pull/10)

make service.swagger.json

Note! commnet in proto file became swagger description. use comment.

```
protoc -I/usr/local/include -I. \
  -I$GOPATH/src \
  -I$GOPATH/src/github.com/grpc-ecosystem/grpc-gateway/third_party/googleapis \
  --swagger_out=logtostderr=true:. \
  --proto_path=../grpcserver/src/main/proto \
  my/rpc/fizbuz.proto 
```

## running

+ run grpcserver application
+ `go run main.go`

or

+ `go build`
+ `./gateway`

access http://localhost:8080/fizbuz/30
