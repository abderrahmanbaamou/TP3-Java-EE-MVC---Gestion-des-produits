
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>

    <h1>Dashboard</h1>

    <p>Bienvenue ${user} !</p>

    <a href="${pageContext.request.contextPath}/logout">
        Déconnexion
    </a>
    <a href="${pageContext.request.contextPath}/produits">
    Voir les produits
</a>

</body>
</html>
