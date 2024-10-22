<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Connexion</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">

        <!-- Email field -->
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" class="form-control" id="email" required />
            <c:if test="${not empty param.errorEmail}">
                <div class="text-danger">${param.errorEmail}</div>
            </c:if>
        </div>

        <!-- Password field -->
        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe</label>
            <input type="password" name="password" class="form-control" id="password" required />
            <c:if test="${not empty param.errorPassword}">
                <div class="text-danger">${param.errorPassword}</div>
            </c:if>
        </div>

        <!-- Display general login error -->
        <c:if test="${not empty param.loginError}">
            <div class="text-danger">${param.loginError}</div>
        </c:if>

        <button type="submit" class="btn btn-primary">Se connecter</button>
    </form>

    <!-- Option to register -->
    <p class="mt-3">Pas encore de compte ? <a href="${pageContext.request.contextPath}/register">S'inscrire</a></p>
</div>
</body>
</html>
