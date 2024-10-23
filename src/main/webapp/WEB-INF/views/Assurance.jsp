<!-- demande_devis.jsp -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Demande de Devis d'Assurance</title>
</head>
<body>
<h1>Demande de Devis d'Assurance</h1>
<form:form action="/soumettreDevis" modelAttribute="assureQuoteRequest">
  <label for="typeAssurance">Type d'Assurance :</label>
  <select id="typeAssurance" path="typeAssurance">
    <option value="automobile">Automobile</option>
    <option value="habitation">Habitation</option>
    <option value="sante">Santé</option>
  </select><br/>

  <label for="age">Âge :</label>
  <input type="number" id="age" path="age"/><br/>

  <label for="valeurBien">Valeur du Bien :</label>
  <input type="number" id="valeurBien" path="valeurBien"/><br/>

  <label for="historiqueSinistres">Historique de Sinistres :</label>
  <input type="text" id="historiqueSinistres" path="historiqueSinistres"/><br/>

  <input type="submit" value="Obtenir un Devis"/>
</form:form>
</body>
</html>
