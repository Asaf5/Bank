<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <h3>Register Form</h3>
</head>
<body>
<br/>

<div style=" margin: auto;  width: 60%; padding: 10px; ">

    <a href="login.jsp">Go To Login Form</a>

    <form id="registerForm" name="registerForm" action="/mainServlet" method="post">
        <label for="identityNo"><b>Identity No</b></label> <br/>
        <input type="text" placeholder="Enter your identity No" name="identityNo" id="identityNo" required>
        <br/>
        <label for="fullName"><b>Full Name</b></label> <br/>
        <input type="text" placeholder="Enter your full Name" name="fullName" id="fullName" required>
        <br/>
        <label for="email"><b>Email</b></label> <br/>
        <input type="text" placeholder="Enter Email" name="email" id="email" required>
        <br/>
        <label for="psw"><b>Password</b></label> <br/>
        <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
        <br/>
        <input name="act" type="hidden" value="register"/> <br/>
        <button type="submit" class="registerbtn">Register</button>
    </form>

</div>
</body>
</html>
