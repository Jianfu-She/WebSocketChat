package org.sjf.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * Created by SJF on 2016/10/31.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Resource
    private MyWebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册处理拦截器,拦截url为ws的请求
        registry.addHandler(webSocketHandler, "/ws").addInterceptors(new WebSocketInterceptor());

        //注册SockJs的处理拦截器,拦截url为/ws/sockjs的请求
        registry.addHandler(webSocketHandler, "/sockjs/ws").addInterceptors(new WebSocketInterceptor()).withSockJS();
    }
}
