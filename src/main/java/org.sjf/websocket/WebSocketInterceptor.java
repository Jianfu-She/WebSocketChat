package org.sjf.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by SJF on 2016/10/31.
 * 在执行客户端服务器端握手之前，也就是在beforeHandshake()方法中，
 * 我们将HttpSession中我们登录后存储的对象放到WebSocketSession中，以此实现定向发送消息
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        if(request != null) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            if(session != null) {
                //区分socket连接以定向发送消息
                System.out.println(session.getAttribute("id"));
                attributes.put("id", session.getAttribute("id"));
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
