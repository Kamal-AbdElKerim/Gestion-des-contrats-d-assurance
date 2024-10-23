<%@ page import="com.game.entity.Contrat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Contract</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
  <h1>Edit Contract</h1>
  <form method="post" action="${pageContext.request.contextPath}/updateContract">
    <input type="hidden" name="id" value="${contrat.devis.sante.id}"> <!-- Hidden field for contract ID -->


    <label for="age"><b>Âge</b></label>
    <input value="${contrat.devis.sante.age}" id="age" type="number" placeholder="Entrez votre âge" name="age" required>

    <label class="form-check-label" for="etatSante">
      Maladie chronique
    </label>
    <!-- Checkbox to send true when checked -->
    <input class="form-check-input" type="checkbox" name="etatSante"   id="etatSante" ${contrat.devis.sante.etatSante == true ? "checked" : ""}>



    <label for="couverture"><b>Type de couverture</b></label>
    <select id="couverture" name="typeCouverture" required>
      <option  ${contrat.devis.sante.typeCouverture == "BASE" ? "selected" :""} value="BASE">De base</option>
      <option ${contrat.devis.sante.typeCouverture == "PREMIUM" ? "selected" :""} value="PREMIUM">Premium</option>
    </select>


    <!-- Add more fields as necessary -->

    <button type="submit" class="btn btn-primary">Update Contract</button>
    <a href="${pageContext.request.contextPath}/MyContrat" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
