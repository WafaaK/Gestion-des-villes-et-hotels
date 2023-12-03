<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Hôtels</title>
    <style>
        body {
            background-color: #f2f2f2;
            color: #333;
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1, h2 {
            color: #3498db;
            text-align: center;
        }

        form {
            margin-bottom: 20px;
            background-color: #3498db;
            padding: 20px;
            border-radius: 10px;
            color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 50%;
            margin: 0 auto;
        }

        form label {
            display: block;
            margin: 10px 0;
            text-align: left;
        }

        form input {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 30px;
            box-sizing: border-box;
            border: none;
            border-radius: 5px;
        }

        form input[type="submit"] {
            background-color: #2980b9;
            cursor: pointer;
        }

        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #3498db;
            color: white;
            border-radius: 10px;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #fff;
        }

        th {
            background-color: #2980b9;
        }

        tr:hover {
            background-color: #2980b9;
        }

        .update-form {
            display: flex;
            align-items: center;
            flex-wrap: wrap;
        }

        .update-form input {
            width: calc(1000% - 20px);
            padding: 10px;
            margin-right: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        .action-buttons {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body>

    <!-- Titre -->
    <h1>Gestion des Hôtels</h1>

   <!-- Formulaire pour ajouter un nouvel hôtel -->
<form action="HotelController" method="post">
    <input type="hidden" name="action" value="create">
    <label>Nom :</label>
    <input type="text" name="nom" required>
    <label>Adresse :</label>
    <input type="text" name="adresse" required>
    <label>Téléphone :</label>
    <input type="text" name="telephone" required>
    <label>Ville :</label>
    <select name="selectedVille" required>
        <c:forEach items="${villes}" var="ville">
            <option value="${ville.id}">${ville.nom}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Ajouter un Hôtel">
</form>


    <!-- Liste des Hôtels -->
    <h2>Liste des Hôtels</h2>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Adresse</th>
                <th>Téléphone</th>
                <!-- Ajoutez d'autres colonnes au besoin -->
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="hotel" items="${Hotels}">
                <tr>
                    <td>${hotel.nom}</td>
                    <td>${hotel.adresse}</td>
                    <td>${hotel.telephone}</td>
                    <td>${hotel.ville}</td>
                    
                    <!-- Ajoutez d'autres colonnes au besoin -->
                    <td class="action-buttons">
                        <!-- Formulaire pour mettre à jour l'hôtel -->
                        <form class="update-form" action="HotelController" method="post">
                            <input type="hidden" name="action" value="modifier">
                            <input type="hidden" name="updateId" value="${hotel.id}">
                            <input type="text" name="updatedNom" value="${hotel.nom}" required>
                            <input type="text" name="updatedAdresse" value="${hotel.adresse}" required>
                            <input type="text" name="updatedTelephone" value="${hotel.telephone}" required>
                             <input type="text" name="updatedville" value="${hotel.ville}" required>
                            <input type="submit" value="Modifier">
                        </form>	
                        <!-- Formulaire pour supprimer l'hôtel -->
                        <form action="HotelController" method="post">
                            <input type="hidden" name="action" value="supprimer">
                            <input type="hidden" name="deleteId" value="${hotel.id}">
                            <input type="submit" value="Supprimer">
                        </form>
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
