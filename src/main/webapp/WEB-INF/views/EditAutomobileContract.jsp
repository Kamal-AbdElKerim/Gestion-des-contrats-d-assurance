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
  <form method="post" action="${pageContext.request.contextPath}/updateContractAutomobile">
    <input type="hidden" name="id" value="${contrat.devis.id}"> <!-- Hidden field for contract ID -->


    <div class="container">
      <label for="conducteurAge"><b>Âge du conducteur</b></label>
      <input value="${contrat.devis.automobile.conducteurAge}" id="conducteurAge" type="number" placeholder="Entrez l'âge" name="conducteurAge" required>

      <label for="véhicule"><b>Type de véhicule</b></label>
      <select id="véhicule" name="typeVehicule" required>
        <option  ${contrat.devis.automobile.typeVehicule == 'luxe' ? 'selected' : ''} value="luxe">luxe</option>
        <option ${contrat.devis.automobile.typeVehicule == 'utilitaire' ? 'selected' : ''} value="utilitaire">utilitaire</option>
      </select>

      <label for="utilisation"><b>Utilisation du véhicule</b></label>
      <select id="utilisation" name="utilisationVehicule" required>
        <option  ${contrat.devis.automobile.utilisationVehicule == 'PRIVEE' ? 'selected' : ''} value="PRIVEE">Privée</option>
        <option  ${contrat.devis.automobile.utilisationVehicule == 'PROFESSIONNELLE' ? 'selected' : ''} value="PROFESSIONNELLE">Professionnelle</option>
      </select>

      <div class="form-check">
        <input  ${contrat.devis.automobile.historiqueConduite == true ? 'checked' : ''} name="historiqueConduite" class="form-check-input" type="checkbox"  id="flexCheckChecked" >
        <label class="form-check-label" for="flexCheckChecked">
          historiqueConduite
        </label>
      </div>

    </div>



    <!-- Add more fields as necessary -->

    <button type="submit" class="btn btn-primary">Update Contract</button>
    <a href="${pageContext.request.contextPath}/MyContrat" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
