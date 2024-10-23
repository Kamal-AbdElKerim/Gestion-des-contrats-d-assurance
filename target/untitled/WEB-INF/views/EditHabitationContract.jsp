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
  <form method="post" action="${pageContext.request.contextPath}/updateContractHabitation">
    <input type="hidden" name="id" value="${contrat.devis.id}"> <!-- Hidden field for contract ID -->


    <div class="container">
      <label for="valeurBien"><b>Valeur du bien</b></label>
      <input value="${contrat.devis.habitation.valeurBien}" id="valeurBien" type="number" placeholder="Entrez la valeur du bien" name="valeurBien" >

      <label for="typeLogement"><b>Type de logement</b></label>
      <select  id="typeLogement" name="typeLogement" >
        <option  ${contrat.devis.habitation.typeLogement == 'APPARTEMENT' ? 'selected' : ''} value="APPARTEMENT">APPARTEMENT</option>
        <option ${contrat.devis.habitation.typeLogement == 'HOUSE' ? 'selected' : ''} value="HOUSE">HOUSE</option>
      </select>

      <label for="localisation"><b>zone Risque</b></label>
      <input ${contrat.devis.habitation.zoneRisque == true ? 'checked' : ''}  id="localisation" type="checkbox" placeholder="Zone à risque" name="zoneRisque" >

      <label for="securite"><b>systeme Securite</b></label>
      <input ${contrat.devis.habitation.systemeSecurite == true ? 'checked' : ''} id="securite" type="checkbox"  name="systemeSecurite" >


    </div>



    <!-- Add more fields as necessary -->

    <button type="submit" class="btn btn-primary">Update Contract</button>
    <a href="${pageContext.request.contextPath}/MyContrat" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
