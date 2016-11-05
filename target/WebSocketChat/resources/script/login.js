$(document).ready(function() {
    var $info = $("#info"),
        $username = $("#username"),
        $password = $("#password"),
        $confirm = $("#confirm");

    $username.focus();

    $confirm.click(function() {
        $info.html("");

        var username = $.trim($username.val()),
            password = $.trim($password.val());

        if (username == "") {
            $info.html("请输入用户名");
            $username.focus();
        } else if (password == "") {
            $info.html("请输入密码");
            $password.focus();
        } else {
            $.post("/WebSocketChat/user/login", {username:username, password:password}, function(data) {
                if (data.errorCode == 1) {
                    window.location.href = data.param;
                } else
                    $info.html(data.errorMsg);
            });
        }
    })
});
