package org.sjf.web;

import com.google.gson.GsonBuilder;
import org.sjf.dto.Message;
import org.sjf.service.UserService;
import org.sjf.utils.JsonUtil;
import org.sjf.websocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


/**
 * Created by SJF on 2016/10/31.
 */
@Controller
@RequestMapping(value = "/user")
public class ChatController {
    @Autowired
    private UserService userService;

    @Autowired
    private MyWebSocketHandler webSocketHandler;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            if (userService.isPassword(username, password)) {
                int id = userService.getId(username);
                System.out.println("id: " + id);

                Cookie cookie_id = new Cookie("id", id + "");
                Cookie cookie_username = new Cookie("username", username);
                response.addCookie(cookie_id);
                response.addCookie(cookie_username);

                response.getWriter().write(JsonUtil.statusResponse(1, "登陆成功", "user/chat?id=" + id));
            } else {
                response.getWriter().write(JsonUtil.statusResponse(2, "密码错误，登录失败", ""));
            }
        } else {
            response.getWriter().write(JsonUtil.statusResponse(0, "后台异常", ""));
        }
    }

    @RequestMapping(value = "/chat")
    public String chat(HttpSession session, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        session.setAttribute("id", id);
        return "chat";
    }

    @RequestMapping(value = "/broadcast", method = RequestMethod.GET)
    public String broadcast() {
        return "broadcast";
    }

    @RequestMapping(value = "/broadcast", method = RequestMethod.POST)
    public void broadcast(String text) throws IOException{
        Message msg = new Message();
        msg.setDate(new Date());
        msg.setFrom(-1);
        msg.setFromName("系统广播");
        msg.setTo(0);
        msg.setText(text);
        webSocketHandler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

}
