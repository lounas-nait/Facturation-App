-- Table TauxTVA
CREATE TABLE TauxTVA (
    idTaux BIGINT PRIMARY KEY AUTO_INCREMENT,
    valeur DOUBLE NOT NULL,
    dateDebut DATE NOT NULL,
    dateFin DATE
);