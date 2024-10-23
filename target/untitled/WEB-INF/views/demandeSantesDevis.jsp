<%@ page import="com.game.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Health Insurance Quotes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"><body>
<div class="container my-5">
  <h1 class="text-center mb-4">Health Insurance Quotes</h1>
  <div class="row container ">
    <c:forEach var="sante" items="${Santes}">
      <% User user = (User) session.getAttribute("user"); %>
      <c:if test="${sante.user.id == user.id}">  <!-- Compare user IDs -->

      <div class="col-4 mb-4 ">


          <div class="card " style="width: 18rem;">
            <img src="https://weassur.ma/wp-content/uploads/2022/05/ASSURANCE-SANTE.jpg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Assurance Type: ${sante.typeAssurance}</h5>
              <h6 class="card-subtitle mb-2 text-muted">Coverage Type: ${sante.typeCouverture}</h6>
              <p class="card-text">Amount: ${sante.devis.montant} MAD</p>
              <p class="card-text">User: ${sante.user.name} </p>
              <p class="card-text">Other Details: <!-- Add any additional details here --></p>


              <c:if test="${sante.devis.status != 'ACCEPTED'}">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${sante.id}">
                  Accepte
                </button>
              </c:if>
              <c:if test="${sante.devis.status == 'ACCEPTED' && sante.devis.contrat.datedebut == null }">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#Contrat${sante.id}">
                  Registre Contrat
                </button>
              </c:if>
              <c:if test="${sante.devis.contrat.datedebut != null }">
                <button disabled type="button" class="btn btn-primary">
                  Done
                </button>
              </c:if>
            </div>
          </div>
          </div>

        <div class="modal fade" id="exampleModal${sante.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  ${sante.id}
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <a href="${pageContext.request.contextPath}/AccepteDevis?id=${sante.devis.id}" class="btn btn-primary">Accepte</a>


              </div>
            </div>
          </div>
        </div>


      <div class="modal fade" id="Contrat${sante.id}" tabindex="-1" aria-labelledby="exampleModalLabe" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabe">Modal title</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

              <form method="post" action="${pageContext.request.contextPath}/contrat" enctype="multipart/form-data">
                <input class="form-control" type="file" id="formFile" name="formFile" required>

                <div class="form-check">
                  <input value="3" class="form-check-input" type="radio" name="datefin1" id="flexRadioDefault1" required>
                  <label class="form-check-label" for="flexRadioDefault1">3 mois</label>
                </div>
                <div class="form-check">
                  <input value="6" class="form-check-input" type="radio" name="datefin1" id="flexRadioDefault2">
                  <label class="form-check-label" for="flexRadioDefault2">6 mois</label>
                </div>
                <div class="form-check">
                  <input value="12" class="form-check-input" type="radio" name="datefin1" id="flexRadioDefault3">
                  <label class="form-check-label" for="flexRadioDefault3" checked>12 mois</label>
                </div>

                <input name="IDDevis" value="${sante.devis.id}" type="hidden">

                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <button type="submit" class="btn btn-primary">Register</button>
                </div>
              </form>

            </div>
          </div>
        </div>
      </div>
      </c:if>
    </c:forEach>

  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script></body>
</html>