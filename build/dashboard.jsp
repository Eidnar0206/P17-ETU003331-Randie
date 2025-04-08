<%@ page import="java.util.List" %>
<%@ page import="credit.*" %>
<%@ page import="depense.*" %>
<%
    List<Credit> credits = (List<Credit>)request.getAttribute("credits");
    List<Depense> depenses = (List<Depense>)request.getAttribute("depenses");
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
    <a href="ajoutDepense"><button> Ajouter ligne de depense </button></a>
    
    <% if(credits.size()>0){ %>
        <h1> Liste Credits </h1>
        <table border="1">
            <tr>
                <th> Credit </th>
                <th> Montant </th>
                <th> Depense total </th>
                <th> Reste </th>
            </tr>
            <% for(Credit c : credits) { %>
                <tr>
                    <td><%= c.getLibelle() %></td>
                    <td><%= c.getMontant() %></td>
                    <td><%= c.getTotalDepense() %></td>
                    <td><%= c.getReste() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>

    <% if(depenses.size()>0){ %>
        <h1> Liste depenses par date </h1>
        <table border="1">
            <tr>
                <th> Libelle </th>
                <th> Montant </th>
                <th> Date </th>
            </tr>
            <% for(Depense d : depenses) { %>
                <tr>
                    <td><%= Credit.findById(d.getIdCredit()).getLibelle() %></td>
                    <td><%= d.getMontant() %></td>
                    <td><%= d.getDate() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>
</body>
