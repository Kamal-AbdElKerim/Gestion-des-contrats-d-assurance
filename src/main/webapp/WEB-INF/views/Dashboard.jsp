<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
  <title>Dashboard</title>
</head>
<body>
<div class="container">
  <h2>Bienvenue, ${user.name}!</h2>
  <p>Voici votre tableau de bord.</p>
  <a href="logout">Logout</a>

  <button onclick="document.getElementById('modalAutomobile').style.display='block'" class="btn btn-primary">Automobile</button>
  <button onclick="document.getElementById('modalSante').style.display='block'" class="btn btn-primary">Santé</button>
  <button onclick="document.getElementById('modalHabitation').style.display='block'" class="btn btn-primary">Habitation</button>

  <!-- Modal for Automobile -->
  <div id="modalAutomobile" class="modal">
    <form class="modal-content animate" action="/automobile" method="post">
      <div class="imgcontainer">
        <span onclick="document.getElementById('modalAutomobile').style.display='none'" class="close" title="Close Modal">&times;</span>
        <img src="img_avatar2.png" alt="Avatar" class="avatar">
      </div>
      <div class="container">
        <label for="conducteurAge"><b>Âge du conducteur</b></label>
        <input id="conducteurAge" type="number" placeholder="Entrez l'âge" name="conducteurAge" required>

        <label for="typeVehicule"><b>Type de véhicule</b></label>
        <input id="typeVehicule" type="text" placeholder="Modèle, marque, type (luxe, utilitaire, etc.)" name="typeVehicule" required>

        <label for="utilisation"><b>Utilisation du véhicule</b></label>
        <select id="utilisation" name="utilisation" required>
          <option value="privée">Privée</option>
          <option value="professionnelle">Professionnelle</option>
        </select>

        <label for="historique"><b>Historique de conduite</b></label>
        <input id="historique" type="text" placeholder="Accidents, infractions" name="historique" required>

        <button type="submit" class="btn btn-primary">Calculer le devis</button>
      </div>
    </form>
  </div>

  <!-- Modal for Santé -->
  <div id="modalSante" class="modal">
    <form class="modal-content animate" action="${pageContext.request.contextPath}/sante" method="post">
      <div class="imgcontainer">
        <span onclick="document.getElementById('modalSante').style.display='none'" class="close" title="Close Modal">&times;</span>
      </div>
      <div class="container">
        <label for="age"><b>Âge</b></label>
        <input id="age" type="number" placeholder="Entrez votre âge" name="age" required>

        <label class="form-check-label" for="etatSante">
          Maladie chronique
        </label>

        <!-- Checkbox to send true when checked -->
        <input class="form-check-input" type="checkbox" name="etatSante"  id="etatSante">



        <label for="couverture"><b>Type de couverture</b></label>
        <select id="couverture" name="typeCouverture" required>
          <option value="BASE">De base</option>
          <option value="PREMIUM">Premium</option>
        </select>

        <button type="submit" class="btn btn-primary">Calculer le devis</button>
      </div>
    </form>
  </div>

  <!-- Modal for Habitation -->
  <div id="modalHabitation" class="modal">
    <form class="modal-content animate" action="/habitation" method="post">
      <div class="imgcontainer">
        <span onclick="document.getElementById('modalHabitation').style.display='none'" class="close" title="Close Modal">&times;</span>
        <img src="img_avatar2.png" alt="Avatar" class="avatar">
      </div>
      <div class="container">
        <label for="valeurBien"><b>Valeur du bien</b></label>
        <input id="valeurBien" type="number" placeholder="Entrez la valeur du bien" name="valeurBien" required>

        <label for="typeLogement"><b>Type de logement</b></label>
        <select id="typeLogement" name="typeLogement" required>
          <option value="appartement">Appartement</option>
          <option value="maison">Maison</option>
        </select>

        <label for="localisation"><b>Localisation</b></label>
        <input id="localisation" type="text" placeholder="Zone à risque" name="localisation" required>

        <label for="securite"><b>Système de sécurité</b></label>
        <select id="securite" name="securite" required>
          <option value="oui">Oui</option>
          <option value="non">Non</option>
        </select>

        <button type="submit" class="btn btn-primary">Calculer le devis</button>
      </div>
    </form>
  </div>

</div>

<script>
  // Close modal when clicking outside of it
  window.onclick = function(event) {
    if (event.target.classList.contains('modal')) {
      event.target.style.display = "none";
    }
  }
</script>

</body>
</html>
