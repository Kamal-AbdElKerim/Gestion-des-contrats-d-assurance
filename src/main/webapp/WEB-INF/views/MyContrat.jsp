<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Contracts</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            margin: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .contract-table {
            margin: 0 auto;
            width: 80%;
        }
    </style>
</head>
<body>

<h1>My Contracts</h1>
<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<div class="container">
    <table class="table table-striped contract-table">
        <thead>
        <tr>
            <th>Date debut</th>
            <th>Date Fin</th>
            <th>typeAssurance</th>
            <th>montant</th>
            <th>age</th>
            <th>etatSante</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contrat" items="${contrats}">
            <tr>
                <td>${contrat.formattedDatedebut}</td>
                <td>${contrat.formattedDatefin}</td>
                <td>${contrat.devis.typeAssurance}</td>
                <td>${contrat.devis.montant}</td>
                <td>${contrat.devis.sante.age == null ? contrat.devis.automobile.conducteurAge : ''}</td>
                <td>${contrat.devis.sante.etatSante == null ? contrat.devis.automobile.typeVehicule : ''}</td>
                <td>
                    <c:if test="${contrat.devis.sante.age != null}">
                    <a href="${pageContext.request.contextPath}/editContractSante/${contrat.id}" class="btn btn-warning btn-sm">Edit</a>
                    </c:if>
                    <c:if test="${contrat.devis.automobile.conducteurAge != null}">
                        <a href="${pageContext.request.contextPath}/editContractAutomobile/${contrat.id}" class="btn btn-warning btn-sm">Edit</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/deleteContract/${contrat.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this contract?');">terminate contract</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
