<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des emprunts</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <p><a href="${pageContext.request.contextPath}/">&larr; Accueil</a></p>
    <h1>Emprunts</h1>

    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/emprunts/nouveau">+ Nouvel emprunt</a></p>

    <table>
        <thead>
            <tr>
                <th>Livre</th>
                <th>Date d'emprunt</th>
                <th>Date de retour</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="emprunt" items="${emprunts}">
                <tr>
                    <td>${emprunt.livre.titre}</td>
                    <td>${emprunt.dateEmprunt}</td>
                    <td>${emprunt.dateRetour != null ? emprunt.dateRetour : 'En cours'}</td>
                    <td>
                        <c:if test="${emprunt.dateRetour == null}">
                            <a href="${pageContext.request.contextPath}/emprunts/retour/${emprunt.id}">Marquer comme rendu</a>
                            |
                        </c:if>
                        <a href="${pageContext.request.contextPath}/emprunts/supprimer/${emprunt.id}"
                           onclick="return confirm('Supprimer cet emprunt ?');">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty emprunts}">
                <tr><td colspan="4">Aucun emprunt.</td></tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
