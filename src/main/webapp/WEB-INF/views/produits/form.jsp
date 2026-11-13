
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire produit</title>
</head>
<body>

<c:choose>
    <c:when test="${empty produit.id}">
        <h1>Nouveau produit</h1>
    </c:when>
    <c:otherwise>
        <h1>Modifier le produit</h1>
    </c:otherwise>
</c:choose>

<form action="${ctx}/produits" method="post">

    <c:if test="${not empty produit.id}">
        <input type="hidden" name="id" value="${produit.id}">
    </c:if>

    <label>Nom :</label>
    <input type="text" name="nom"
           value="${fn:escapeXml(produit.nom)}">

    <p style="color:red;">
        <c:out value="${erreurs.nom}" />
    </p>

    <label>Description :</label>
    <textarea name="description"><c:out value="${produit.description}" /></textarea>

    <p style="color:red;">
        <c:out value="${erreurs.description}" />
    </p>

    <label>Prix :</label>
    <input type="text" name="prix"
           value="${produit.prix}">

    <p style="color:red;">
        <c:out value="${erreurs.prix}" />
    </p>

    <label>Stock :</label>
    <input type="text" name="stock"
           value="${produit.stock}">

    <p style="color:red;">
        <c:out value="${erreurs.stock}" />
    </p>

    <button type="submit">Enregistrer</button>

</form>

<br>
<a href="${ctx}/produits">Retour à la liste</a>

</body>
</html>
