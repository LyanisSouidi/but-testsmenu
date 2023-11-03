CREATE TABLE IF NOT EXISTS sae31_Protocole (
    reference VARCHAR(25) PRIMARY KEY,
    actionAttendue INT NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS sae31_Element (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(50) NOT NULL,
    parent INT REFERENCES sae31_Element(id),
    protocole VARCHAR(25) NOT NULL REFERENCES sae31_Protocole(reference),
    priorite INT NOT NULL DEFAULT 0
);

ALTER TABLE sae31_Protocole ADD FOREIGN KEY (actionAttendue) REFERENCES sae31_Element(id);

CREATE TABLE IF NOT EXISTS sae31_Resultat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    protocole VARCHAR(25) NOT NULL REFERENCES sae31_Protocole(reference)
);

CREATE TABLE IF NOT EXISTS sae31_ElementVisite (
    element INT NOT NULL REFERENCES sae31_Element(id),
    ordre INT NOT NULL,
    resultat INT NOT NULL REFERENCES sae31_Resultat(id),
    UNIQUE (element, resultat)
);