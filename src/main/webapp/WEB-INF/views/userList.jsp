<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>

<body>


<table border="2" align="center">
    <tr align="center">
        <th> ID</th>
        <th> First Name</th>
        <th> Second Name</th>
        <th> Last Name</th>
        <th> Phone</th>
    </tr>
    <c:forEach items="${entityList}" var="entity">
        <tr align="center">
            <td align="center">
                <c:out value="${entity.id}"/>
            </td>
            <td align="center">
                <c:out value="${entity.firstName}"/>
            </td>
            <td align="center">
                <c:out value="${entity.secondName}"/>
            </td>
            <td align="center">
                <c:out value="${entity.lastName}"/>
            </td>
            <td align="center">
                <ul>
                    <c:forEach items="${entity.phoneNumbers}" var="phone">
                        <li>${phone.company.name} - ${phone.number}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
    <tr align="right">
        <button type="button" ><a href="/user/create">Create new User</a></button>
    </tr>
</table>


</body>
</html>
