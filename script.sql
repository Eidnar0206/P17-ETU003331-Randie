CREATE DATABASE WebDyn_credit_depense;
USE WebDyn_credit_depense;

CREATE TABLE WebDyn_credit(
    idCredit INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(250),
    montant FLOAT
);

CREATE TABLE WebDyn_depense(
    idDepense INT AUTO_INCREMENT PRIMARY KEY,
    idCredit INT, 
    montant FLOAT,
    dateDepense DATE,
    FOREIGN KEY (idCredit) REFERENCES WebDyn_credit(idCredit)
);


CREATE TABLE WebDyn_login(
    idLogin INT PRIMARY KEY,
    nom VARCHAR(250),
    motdepasse VARCHAR(250)
);

INSERT INTO WebDyn_login VALUES (1, "webdynamique", "examen");