package org.sjf.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.sjf.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by SJF on 2016/10/31.
 */
@Service
public class MyWebSocketHandler implements WebSocketHandler {

    private static final Map<Integer, WebSocketSession> userSocketSessionMap;
    private static final Logger logger;

    static {
        userSocketSessionMap = new HashMap<>();
        logger = LoggerFactory.getLogger(MyWebSocketHandler.class);
    }

    /**
     * 建立连接后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("成功建立socket连接");
        int id = (int) session.getAttributes().get("id");
        if (!userSocketSessionMap.containsKey(id)) {
            userSocketSessionMap.put(id, session);
        }
    }

    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(message.getPayloadLength() == 0) return;
        Message msg = new Gson().fromJson(message.getPayload().toString(),Message.class);
        msg.setDate(new Date());
        sendMessageToUser(msg.getTo(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

    /**
     * 消息传输错误处理
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.error("连接出现错误:" + exception.toString());
        remove(session);
    }

    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("Websocket:" + session.getId() + "已经关闭");
        remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     */
    public void broadcast(final TextMessage message) throws IOException {
        Iterator<Entry<Integer, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();

        // 多线程群发
        while (it.hasNext()) {
            final Entry<Integer, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                entry.getValue().sendMessage(message);
                //new Thread(new Runnable() {
                //    public void run() {
                //        try {
                //            if (entry.getValue().isOpen()) {
                //                entry.getValue().sendMessage(message);
                //            }
                //        } catch (IOException e) {
                //            e.printStackTrace();
                //        }
                //    }
                //}).start();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     */
    public void sendMessageToUser(int id, TextMessage message) throws IOException {
        WebSocketSession session = userSocketSessionMap.get(id);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }

    private void remove(WebSocketSession session) {
        Iterator<Entry<Integer, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<Integer, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }
}
