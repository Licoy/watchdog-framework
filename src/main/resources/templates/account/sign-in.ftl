<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <style>
        #sign-in{
            margin: 100px auto;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="sign-in">
    <#if singInMsg??>
        <h2>${singInMsg!'未知原因'}</h2>
    </#if>
    <form action="/account/sign-in" method="post">
        <label>
            <span>用户名</span>
            <input type="text" name="username">
        </label>
        <br><br>
        <label>
            <span>密码</span>
            <input type="password" name="password">
        </label>
        <br><br>
        <button type="submit">登录</button>
    </form>
</div>
</body>
</html>