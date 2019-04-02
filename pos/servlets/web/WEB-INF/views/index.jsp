<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Pesquisar piadas</title>
</head>
<body>
<form action='' method='get'>
    <input type='text' name='filtro' id='filtro' value='${filtro}'>
    <input type='submit' value='Filtrar'>
</form>
<ul>
    <c:forEach items='${piadas}' var='piada'>
        <li>${piada.conteudo}</li>
    </c:forEach>
</ul>
</body>
</html>
