<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion de Bibliothèque</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <h1>Spring MVC </h1>
    <p>Application : Gestion de Bibliothèque (Livre / Emprunt)</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/livres">Liste des livres</a></li>
        <li><a href="${pageContext.request.contextPath}/emprunts">Liste des emprunts</a></li>
    </ul>
</body>
</html>
