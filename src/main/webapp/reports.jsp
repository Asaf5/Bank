<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reports</title>
</head>
<body>
<div id="tableDiv">
    <h1 align="center">Reports</h1>
    <table id="tableID" align="center" border="1px">
        <thead>
        <tr>
            <th &lt;%&ndash;style="display: none"&ndash;%&gt; scope="col">id</th>
            <th scope="col">enterance</th>
            <th scope="col">exit</th>
            <th scope="col">user</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reports}" var="report"  varStatus="counter">
        <tr>
            <td &lt;%&ndash;style="display:none;"&ndash;%&gt;> <c:out value="${report.identity}"/> </td>
            <td> <c:out value="${report.enterance}"/></td>
            <td> <c:out value="${report.exit}"/></td>
            <td> <c:out value="${report.user}"/></td>
        </tr>
        </c:forEach>
        <tbody/>
    </table>
    <div style="text-align:center">
        <a style="margin:auto; text-align:center; display:block;" href="users.jsp">Back To Users</a>
    </div>
</div>

</body>
</html>