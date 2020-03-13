<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PDF Exception</title>

    <style>
        img {
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>

<h3 align="center">${status}</h3>
<h4>${message}</h4>
<h3>${cause}</h3>

<img align="middle"
     src="https://cdn4.iconfinder.com/data/icons/smoothfill-file/30/072-damaged_file-text-document-broken-512.png"
     width="40%">
</body>
</html>
