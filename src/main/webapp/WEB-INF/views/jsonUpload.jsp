<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload users file</title>
    <meta charset="utf-8">
</head>
<body>

<form:form method="POST" action="json" enctype="multipart/form-data">
    <table>
        <tr>
            <td><label for="file">Select a file to upload</label></td>
            <td><input id="file" type="file" name="file"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>


</body>
</html>
