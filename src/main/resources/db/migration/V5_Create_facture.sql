-- Table Facture
CREATE TABLE Facture (
    idFacture BIGINT PRIMARY KEY AUTO_INCREMENT,
    idClient BIGINT,
    dateFacture DATE NOT NULL,
    FOREIGN KEY (idClient) REFERENCES Client(idClient)
);