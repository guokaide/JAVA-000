# 学习笔记

## 作业1

> 在网关程序中，使用 HTTP Client 访问后端服务代替 Mock 数据。

解答：作业中使用上节课使用的 HTTP Client 4 访问后端服务，详见：`io.github.kimmking.gateway.outbound.httpclient4.HttpOutboundHandler.java` 

## 作业2

> 使用 Netty 实现作业 1 的功能。

解答：本部分仅仅实现了使用 Netty 实现 HTTP Client 的功能，见 `io.github.kimmking.gateway.outbound.netty4` 包下的实现，但是还差将响应返回给访问网关的客户端，算是完成了一半

## 作业3

> 实现一个 Filter，在访问后端服务的 HTTP header 中加入头 <nio, name>。

解答：实现见 `io.github.kimmking.gateway.filter.HttpHeaderFilter`， 使用见：`io.github.kimmking.gateway.inbound.HttpInboundHandler`

## 作业4

> 实现一个路由，负载均衡。

解答：作业使用最简单的随机选择一个后端服务的方式，见`io.github.kimmking.gateway.router.HttpEndpointRouter`



