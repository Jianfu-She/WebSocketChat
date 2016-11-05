var id = getCookie("id");

if (id == null) {
    id = -1;
    location.href = "user/broadcast";
}

var from = id;
var fromName = getCookie("username");

var $info = $("#info");
$info.html(fromName);

var to = id==1?2:1;

var websocket = new SockJS("/WebSocketChat/sockjs/ws");

websocket.onopen = function(event) {
    console.log("WebSocket:已连接");
    console.log(event);
};
websocket.onmessage = function(event) {
    var data = JSON.parse(event.data);
    console.log("WebSocket:收到一条消息",data);
    var textCss = data.from==-1?"sfmsg_text":"fmsg_text";
    $("#content").append("<div><label class='name fmsg'>"+data.fromName+"&nbsp;"+data.date+"</label><div class='from fmsg "+textCss+"'>"+data.text+"</div></div>");
    scrollToBottom();
};
websocket.onerror = function(event) {
    console.log("WebSocket:发生错误 ");
    console.log(event);
};
websocket.onclose = function(event) {
    console.log("WebSocket:已关闭");
    console.log(event);
};
function sendMsg(){
    var v = $("#msg").val();
    if (v == "") {
        return;
    } else {
        var data = {};
        data["from"] = from;
        data["fromName"] = fromName;
        data["to"] = to;
        data["text"] = v;
        websocket.send(JSON.stringify(data));
        $("#content").append("<div><label class='name tmsg'>我&nbsp;"+new Date().Format("yyyy-MM-dd hh:mm:ss")+"</label><div class='to tmsg tmsg_text'>"+data.text+"</div></div>");
        scrollToBottom();
        $("#msg").val("");
    }
}

function scrollToBottom(){
    var div = document.getElementById('content');
    div.scrollTop = div.scrollHeight;
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function send(event){
    var code;
    if(window.event){
        code = window.event.keyCode; // IE
    }else{
        code = event.which; // Firefox
    }
    if(code == 13){
        sendMsg();
    }
}

function clearAll(){
    $("#content").empty();
}

function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}