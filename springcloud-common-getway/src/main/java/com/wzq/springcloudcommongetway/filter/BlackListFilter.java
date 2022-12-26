package com.wzq.springcloudcommongetway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@Component
public class BlackListFilter implements GlobalFilter, Ordered {
    private static List<String> blackList = new ArrayList<>();

    static {
        blackList.add("0:0:0:0:0:1:0:1"); // 模拟本机地址
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 思路：获取客户端ip，判断是否在⿊名单中，在的话就拒绝访问，不在的话就放⾏
        // 从上下⽂中取出request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 从request对象中获取客户端ip
        String clientIp = request.getRemoteAddress().getHostString();
        // 拿着clientIp去⿊名单中查询，存在的话就决绝访问
        if (blackList.contains(clientIp)) {
            // 决绝访问，返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
            System.out.println("=====>IP:" + clientIp + " 在⿊名单中，将被拒绝访 问！");
            String data = "请求被禁止!";

            DataBuffer wrap = null;
            try {
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                wrap = response.bufferFactory().wrap(data.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return response.writeWith(Mono.just(wrap));

        }

        // 合法请求，放⾏，执⾏后续的过滤器
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 0;//越小优先级越高
    }
}
