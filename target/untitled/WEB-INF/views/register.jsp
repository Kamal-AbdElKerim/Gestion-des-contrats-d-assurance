<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inscription</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h2>Inscription</h2>
  <form action="${pageContext.request.contextPath}/register" method="post">
    <div class="mb-3">
      <label for="name" class="form-label">Nom</label>
      <input type="text" name="name" class="form-control" id="name"  value="${user.name}" />

      <c:if test="${not empty validationErrors['nameError']}">
        <div class="text-danger">${validationErrors['nameError']}</div>
      </c:if>

    </div>

    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" name="email" class="form-control" id="email" value="${user.email}" />
      <c:if test="${not empty validationErrors['emailError']}">
        <div class="text-danger">${validationErrors['emailError']}</div>
      </c:if>
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">Mot de passe</label>
      <input type="password" name="password" class="form-control" id="password" />
      <c:if test="${not empty validationErrors['passwordError']}">
        <div class="text-danger">${validationErrors['passwordError']}</div>
      </c:if>
    </div>

    <div class="mb-3">
      <label for="phoneNumber" class="form-label">Numéro de téléphone</label>
      <input type="text" name="phoneNumber" class="form-control" id="phoneNumber"  value="${user.phoneNumber}"/>
      <c:if test="${not empty validationErrors['phoneNumberError']}">
        <div class="text-danger">${validationErrors['phoneNumberError']}</div>
      </c:if>
    </div>

    <div class="mb-3">
      <label for="address" class="form-label">Adresse</label>
      <input type="text" name="address" class="form-control" id="address" value="${user.address}" />
      <c:if test="${not empty validationErrors['addressError']}">
        <div class="text-danger">${validationErrors['addressError']}</div>
      </c:if>
    </div>

    <button type="submit" class="btn btn-primary">S'inscrire</button>
  </form>
</div>
</body>
</html>
