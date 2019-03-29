<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: emanuelvictor
  Date: 11/03/19
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<html>
<head>
    <title>Pesquisar piadas</title>
</head>
<body>
<form action='' method='get'>
    <input type='text' name='filtro' id='filtro'
           value='<%=request.getAttribute("filtro") == null ? "" : request.getAttribute("filtro") %>'>
    <input type='submit' value='Filtrar'>
</form>
<ul>
    <% final List<String> piadas = (List<String>) request.getAttribute("piadas");
        if (piadas.size() > 0)
            for (final String piada : piadas) { %>
                <li><%=piada%></li>
        <% }
        else
        %>
            <li>Nenhuma piada encontrada com esse filtro!</li>
        <%
        %>
</ul>
</body>
</html>
