-- Table Produit
CREATE TABLE Produit (
    idProduit BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    prix DOUBLE NOT NULL,
    idCategorie BIGINT,
    idTaux BIGINT,
    FOREIGN KEY (idCategorie) REFERENCES Categorie(idCategorie),
    FOREIGN KEY (idTaux) REFERENCES TauxTVA(idTaux)
);