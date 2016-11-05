<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="sourpath" value="${pageContext.request.contextPath}"/>
<head>
    <title>聊天</title>
    <link href="${sourpath}/resources/css/chat.css" rel="stylesheet">
</head>
<body>
    欢迎：<p id="info" ></p>
    <div id="content"></div>
    <input type="text" placeholder="请输入要发送的信息" id="msg" onkeydown="send(event)">
    <input id="sendBtn" type="button" value="发送" onclick="sendMsg()" >
    <input id="clearBtn" type="button" value="清空" onclick="clearAll()">
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js" ></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script type="text/javascript" src="${sourpath}/resources/script/chat.js"></script>
</html>
