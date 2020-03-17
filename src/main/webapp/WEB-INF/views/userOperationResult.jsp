<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Status</title>
    <meta charset="utf-8">
</head>

<body>


<h2 align="center">${message}</h2>

<table border="2" align="center" cellpadding="5">
    <tr align="center">
        <th> ID</th>
        <th> First Name</th>
        <th> Second Name</th>
        <th> Last Name</th>
        <th> Phone</th>
    </tr>
    <td> ${entity.id} </td>
    <td> ${entity.firstName} </td>
    <td> ${entity.secondName} </td>
    <td> ${entity.lastName} </td>
    <td>
        <ul>
            <c:forEach items="${entity.phoneNumbers}" var="phone">
                <li>${phone.company.name} - ${phone.number}</li>
            </c:forEach>
        </ul>
    </td>
</table>

<h4><a href="/user/all">To all users</a></h4>
</body>
</html>
