<%@ page import="java.util.List" %>
<%@ page import="credit.*" %>
<%@ page import="depense.*" %>
<%
    List<Credit> credits = (List<Credit>)request.getAttribute("credits");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <a href="ajoutCredit"><button> Ajouter ligne de cr&eacute;dit </button></a>
    <a href="dashboard"><button> Dashboard </button></a>
    
    <h1> Ajouter ligne de depense </h1>
    <form action="ajoutDepense" method="post">
        <label for="credit"> Credit: </label>
        <select name="idCredit">
            <% for(Credit c : credits) { %>
                <option value="<%= c.getIdCredit() %>"><%= c.getLibelle() %></option>
            <% } %>
        </select>
        <label for="montant"> Montant de depense: </label>
        <input type="number" name="montant" id="montant">

        <label for="dateDepense"> Date: </label>
        <input type="date" name="dateDepense" id="dateDepense">

        <button type="submit"> Ajouter </button>
    </form>
</body>
</html>