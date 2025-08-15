-- Table LigneFacture
CREATE TABLE LigneFacture (
    idLigne BIGINT PRIMARY KEY AUTO_INCREMENT,
    idFacture BIGINT,
    nomProduit VARCHAR(255) NOT NULL,
    prixHt DOUBLE NOT NULL,
    tauxTva DOUBLE NOT NULL,
    quantite INT NOT NULL,
    montantTTC DOUBLE NOT NULL,
    FOREIGN KEY (idFacture) REFERENCES Facture(idFacture)
);