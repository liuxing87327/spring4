<%--
 功能说明：websocket
 作者：liuxing(2014-11-15 16:54)
 修改者：liuxing(2014-11-15 16:54)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Simple Web Socket Client</title>
    <link rel="stylesheet" type="text/css" href="/static/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/style.css"/>
</head>
<body>
<div id="content">
    <fieldset>
        <legend>Server Location</legend>
        <div>
            <label>URL:</label>
            <input type="text" id="serverUrl" value="ws://localhost:8080/websocket"/>
            <button id="connectButton">打开</button>
            <button id="disconnectButton">关闭</button>
        </div>
        <div>
            <label>状态:</label>
            <span id="connectionStatus">已关闭</span>
        </div>
    </fieldset>
    <fieldset id="requestArea">
        <legend>Request</legend>
        <div>
            <textarea id="sendMessage" disabled="disabled"></textarea>
        </div>
        <div>
            <button id="sendButton" disabled="disabled">发送</button>
            [快捷键] Ctr + Enter
        </div>
    </fieldset>
    <fieldset id="messageArea">
        <legend>消息日志 <button id="clearMessage">清除</button></legend>
        <div id="messages"></div>
    </fieldset>
</div>
<script type="text/javascript" src="/static/js/lib/jquery-1.4.3.min.js"></script>
<script type="text/javascript" src="/static/js/index.js"></script>
</body>
</html>