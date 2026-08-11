<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Formulaire livre</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <p><a href="${pageContext.request.contextPath}/livres">&larr; Retour à la liste</a></p>
    <h1>${livre.id == null ? 'Ajouter un livre' : 'Modifier le livre'}</h1>

    <form:form modelAttribute="livre" method="post" action="${pageContext.request.contextPath}/livres">
        <form:hidden path="id"/>

        <p>
            <label for="titre">Titre</label><br>
            <form:input path="titre" id="titre"/>
            <form:errors path="titre" cssClass="error"/>
        </p>

        <p>
            <label for="auteur">Auteur</label><br>
            <form:input path="auteur" id="auteur"/>
            <form:errors path="auteur" cssClass="error"/>
        </p>

        <p>
            <label for="isbn">ISBN</label><br>
            <form:input path="isbn" id="isbn"/>
            <form:errors path="isbn" cssClass="error"/>
        </p>

        <p>
            <label for="disponible">
                <form:checkbox path="disponible" id="disponible"/>
                Disponible
            </label>
        </p>

        <button type="submit">Enregistrer</button>
    </form:form>
</body>
</html>
