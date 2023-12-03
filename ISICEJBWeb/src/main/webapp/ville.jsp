<%@ page import="entities.Ville" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="ISO-8859-1">
    <title>Gestion des Villes</title>
    <style>
        body {
            background-color: #3498db; /* Blue background color */
            font-family: Arial, sans-serif;
            color: #ecf0f1; /* Light text color */
            margin: 20px;
        }

        form {
            margin-bottom: 20px;
            background-color: #2980b9; /* Dark blue background color for forms */
            padding: 20px;
            border-radius: 10px;
            color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 50%;
            margin: 0 auto;
        }

        h1, h2 {
            color: #3498db; /* Blue header color */
            text-align: center;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            background-color: #2980b9; /* Dark blue background color for list items */
            margin-bottom: 10px;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            color: white;
        }

        /* Style for the delete button */
        .delete-button {
            background-color: #c0392b; /* Red background color for delete button */
            color: #fff;
            border: none;
            padding: 8px 12px;
            margin-left: 10px;
            cursor: pointer;
            border-radius: 5px;
        }

        /* Style for the forms */
        form input[type="text"],
        form button {
            padding: 10px;
            margin: 5px;
            border: 1px solid #3498db; /* Blue border color for input fields */
            border-radius: 5px;
        }

        form button {
            background-color: #3498db; /* Blue background color for buttons */
            color: #fff;
            cursor: pointer;
        }

        /* Style for the update form */
        form#updateForm {
            margin-top: 20px;
        }

        /* Responsive design for smaller screens */
        @media (max-width: 600px) {
            li {
                padding: 10px;
            }

            form input[type="text"],
            form button {
                width: 100%;
            }
        }
    </style>
    
    <script>
        function confirmDelete() {
            return confirm("Êtes-vous sûr de vouloir supprimer cette ville ?");
        }

        function confirmUpdate() {
            return confirm("Êtes-vous sûr de vouloir modifier cette ville ?");
        }
    </script>
</head>
<body>

    <form action="VilleController" method="post">
        <input type="hidden" name="action" value="create" />
        Nom : <input type="text" name="ville" /> <br>
        <button type="submit">Enregistrer</button>
    </form>

    <h1>Liste des villes : </h1>
    <ul>
        <c:forEach items="${villes}" var="v">
            <li>${v.id} - ${v.nom}
                <!-- Delete button for each city -->
                <form action="VilleController" method="post" style="display: inline;" onsubmit="return confirmDelete();">
                    <input type="hidden" name="action" value="supprimer" />
                    <input type="hidden" name="deleteId" value="${v.id}" />
                    <button type="submit" class="delete-button">Supprimer</button>
                </form>

                <!-- Update form for each city -->
                <form action="VilleController" method="post" id="updateForm" style="display: inline;" onsubmit="return confirmUpdate();">
                    <input type="hidden" name="action" value="modifier" />
                    <input type="hidden" name="updateId" value="${v.id}" />
                    <input type="text" name="updatedNom" placeholder="Nouveau nom de la ville" />
                    <button type="submit">Modifier</button>
                </form>
            </li>
        </c:forEach>
    </ul>

</body>
</html>
