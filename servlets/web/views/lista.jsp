<%--
  Created by IntelliJ IDEA.
  User: emanuelvictor
  Date: 11/03/19
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exibir parâmetro</title>
</head>
<body>
<label> O parâmetro enviado é: <%= request.getParameter("parametro")%></label>
</body>
</html>
