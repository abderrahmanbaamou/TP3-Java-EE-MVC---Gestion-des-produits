
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>

    <h1>Connexion</h1>

    <c:if test="${not empty erreur}">
        <p style="color:red">
            ${erreur}
        </p>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/login">

        <label>Username :</label>
        <input type="text"
               name="username"
               value="${username}"
               required>

        <br><br>

        <label>Password :</label>
        <input type="password"
               name="password"
               required>

        <br><br>

        <button type="submit">Connexion</button>

    </form>

</body>
</html>
