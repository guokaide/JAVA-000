package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class HttpHeaderFilter implements HttpRequestFilter {
    // 这个 Filter 的一个作用是：
    // 后端可以从这个请求头中拿出值去选择机器或者增加token
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders headers = fullRequest.headers();
        headers.add("nio", "guokaide");
    }
}
