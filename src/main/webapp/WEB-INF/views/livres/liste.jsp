<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des livres</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <p><a href="${pageContext.request.contextPath}/">&larr; Accueil</a></p>
    <h1>Livres</h1>

    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <form method="get" action="${pageContext.request.contextPath}/livres">
        <input type="text" name="recherche" placeholder="Rechercher par titre" value="${recherche}">
        <button type="submit">Rechercher</button>
    </form>

    <p><a href="${pageContext.request.contextPath}/livres/nouveau">+ Ajouter un livre</a></p>

    <table>
        <thead>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>ISBN</th>
                <th>Disponible</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="livre" items="${livres}">
                <tr>
                    <td>${livre.titre}</td>
                    <td>${livre.auteur}</td>
                    <td>${livre.isbn}</td>
                    <td>${livre.disponible ? 'Oui' : 'Non'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/livres/modifier/${livre.id}">Modifier</a>
                        |
                        <a href="${pageContext.request.contextPath}/livres/supprimer/${livre.id}"
                           onclick="return confirm('Supprimer ce livre ?');">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty livres}">
                <tr><td colspan="5">Aucun livre.</td></tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
