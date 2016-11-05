<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>广播</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js" ></script>
    <script type="text/javascript">
        function broadcast(){
            $.ajax({
                url:'broadcast',
                type:"post",
                data:{text:$("#msg").val()},
                dataType:"json",
                success:function(data){
                    alert("发送成功");
                }
            });
        }
    </script>
</head>
<body>
    发送广播
    <textarea style="width:100%;height:300px;" id="msg" ></textarea>
    <input type="button" value="发送" onclick="broadcast()">
</body>
</html>
