<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="sourpath" value="${pageContext.request.contextPath}"/>
<head>
    <title>登录</title>
</head>
<body>
    <div style="text-align: center;">
        <form>
            <p>
                <label>username</label>
                <input id="username" type="text" name="username" />
            </p>
            <p>
                <label>password</label>
                <input id="password" type="password" name="password" />
            </p>
            <input id="confirm" type="button" value="登录" />
            <p id="info" ></p>
        </form>
    </div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="${sourpath}/resources/script/login.js"></script>
</html>
