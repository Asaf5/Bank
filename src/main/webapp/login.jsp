<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <h3>Login Form</h3>
</head>
    <%
    String profile_msg=(String)request.getAttribute("profile_msg");
    if(profile_msg!=null){
        out.println(profile_msg);
    }
    String login_msg=(String)request.getAttribute("login_msg");
    if(login_msg!=null){
        out.println(login_msg);
    }
%>
<br/>

<a href="register.jsp">Go To Register Form</a>

<form name="loginForm" method="get" action="/mainServlet">
    <label for="email"><b>Email </b></label><br/>
    <input type="email" placeholder="Enter Email" name="email" id="email" required>
    <br/>
    <label for="password"><b>Password</b></label><br/>
    <input type="password" placeholder="Enter Password" name="password" id="password" required>
    <br/><br/>
    <input name="act" type="hidden" value="login"/> <br/>
    <input type="submit" value="login"/>
</form>
<html/>