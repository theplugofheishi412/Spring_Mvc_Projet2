<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Nouvel emprunt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <p><a href="${pageContext.request.contextPath}/emprunts">&larr; Retour à la liste</a></p>
    <h1>Nouvel emprunt</h1>

    <form:errors path="*" cssClass="error"/>

    <form:form modelAttribute="emprunt" method="post" action="${pageContext.request.contextPath}/emprunts">

        <p>
            <label for="livre">Livre</label><br>
            <form:select path="livre.id" id="livre">
                <form:option value="" label="-- Choisir un livre --"/>
                <c:forEach var="livre" items="${livresDisponibles}">
                    <form:option value="${livre.id}" label="${livre.titre} (${livre.auteur})"/>
                </c:forEach>
            </form:select>
            <form:errors path="livre" cssClass="error"/>
        </p>

        <p>
            <label for="dateEmprunt">Date d'emprunt</label><br>
            <form:input type="date" path="dateEmprunt" id="dateEmprunt"/>
            <form:errors path="dateEmprunt" cssClass="error"/>
        </p>

        <button type="submit">Enregistrer</button>
    </form:form>
</body>
</html>
