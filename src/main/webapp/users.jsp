<%@ page import="com.beans.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<div>

    <div align="center">

        <a style="align:center;" href="login.jsp">logout</a>
        <br/><br/>
        <%
            User user =(User)session.getAttribute("currentUser");
            if(user != null) {
                out.println("Hello " + user.getFullName());
            }
            out.println("You r successfully logged in"); %>
        <br/><br/>
        <input type="button" value="Load Table" onclick="loadUsers();"/>

    </div>
    <div id="tableDiv">

        <h1 align="center">Users</h1>
        <table id="tableID" align="center" border="1px">
            <thead>
            <tr>
                <th scope="col">email</th>
                <th scope="col">password</th>
                <th scope="col">fullName</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${list}" var="list1" varStatus="counter">
            <tr id="${list1.id}">
                <td style="display:none;">${list1.id}</td>
                <td>${list1.email}</td>
                <td>${list1.password}</td>
                <td>${list1.fullName}</td>
            </tr>
            </c:forEach>
            <tbody/>
        </table>
        <form name="reportForm" method="get" action="/mainServlet" style="text-align: center" >
            <input name="act" id="act" type="hidden" value="report"/> <br/>
            <input type="button" onclick="report('view');" value="see all Reports"/>
            <input type="button" onclick="report('enter');" value="report Entrance"/>
            <input type="button" onclick="report('exit');" value="report Exit"/>
        </form>
    </div>
</div>
</div>


</body>
</html>
