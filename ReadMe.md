# gRPC java sample

## check

[x] gRPC Reactor plugin
[x] gRPC Sprint Boot Starter
[x] Proxy L4 LoadBalancer
[x] Proxy L7 LoadBalancer
[x] ClientSideLoadBalanser and name resolver with Eureka
[ ] Interceptor (Validation, autentication)
[ ] TLS

## projects


### grpcclient

standalon grpclient program.
this does't inclued docker-compose.

### grpcserver

grpc server implementation.

2 server runs at docker-compse
+ grpc1 port 6566(grpc),8081(http)
+ grpc2 port 6567(grpc),8082(http)

### l4lb

L4 Load Balanser by HA-proxy. run at 6565

### l7lb

L7 Load Balancer by NGINX grpc. run at 6564:80

### grpcfront

Front server using grpc client and eureka client load balancser. run at 8080

### grpceurela

Eureka server. run at 8761