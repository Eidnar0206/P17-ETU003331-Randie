<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <a href="ajoutDepense"><button> Ajouter ligne de depense </button></a>
    <a href="dashboard"><button> Dashboard </button></a>
    
    <h1> Ajouter ligne de cr&eacute;dit </h1>
    <form action="ajoutCredit" method="post">
        <label for="libelle"> Libelle: </label>
        <input type="text" name="libelle" id="libelle">
        <label for="montant"> Montant: </label>
        <input type="number" name="montant" id="montant">
        <button type="submit"> Ajouter </button>
    </form>
</body>
</html>