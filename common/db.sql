CREATE TABLE IF NOT EXISTS sae31_Protocole (
    reference VARCHAR(25) PRIMARY KEY COLLATE utf8mb4_unicode_ci, -- Force la réference à être insensible à la casse pour faciliter la recherche
    actionAttendue INT,
    description TEXT
);

CREATE TABLE IF NOT EXISTS sae31_Element (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(50) NOT NULL,
    parent INT REFERENCES sae31_Element(id),
    protocole VARCHAR(25) NOT NULL REFERENCES sae31_Protocole(reference) ON DELETE CASCADE ON UPDATE CASCADE,
    priorite INT NOT NULL DEFAULT 0
);

ALTER TABLE sae31_Protocole ADD FOREIGN KEY (actionAttendue) REFERENCES sae31_Element(id);

CREATE TABLE IF NOT EXISTS sae31_Resultat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    protocole VARCHAR(25) NOT NULL REFERENCES sae31_Protocole(reference) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS sae31_ElementVisite (
    element INT NOT NULL REFERENCES sae31_Element(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ordre INT NOT NULL,
    resultat INT NOT NULL REFERENCES sae31_Resultat(id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (element, resultat)
);

-- Données de démonstration --

INSERT INTO sae31_Protocole (reference, actionAttendue, description)
VALUES
    ('FILE_MANAGEMENT', NULL, 'Trouver l\'option pour créer un nouveau dossier.');
INSERT INTO sae31_Element (id, titre, parent, protocole, priorite)
VALUES
    (1, 'Fichier', NULL, 'FILE_MANAGEMENT', 1),
    (2, 'Nouveau', 1, 'FILE_MANAGEMENT', 1),
    (3, 'Dossier', 2, 'FILE_MANAGEMENT', 1),
    (4, 'Fichier', 2, 'FILE_MANAGEMENT', 2),
    (5, 'Ouvrir', 1, 'FILE_MANAGEMENT', 2),
    (6, 'Fermer', 1, 'FILE_MANAGEMENT', 3);
UPDATE sae31_Protocole SET actionAttendue = 3 WHERE reference = 'FILE_MANAGEMENT';

INSERT INTO sae31_Protocole (reference, actionAttendue, description)
VALUES
    ('USER_CONFIG', NULL, 'Modifier le mot de passe du compte utilisateur.');
INSERT INTO sae31_Element (id, titre, parent, protocole, priorite)
VALUES
    (7, 'Paramètres', NULL, 'USER_CONFIG', 1),
    (8, 'Compte', 7, 'USER_CONFIG', 1),
    (9, 'Sécurité', 5, 'USER_CONFIG', 1),
    (10, 'Modifier mot de passe', 9, 'USER_CONFIG', 1),
    (11, 'Confidentialité', 8, 'USER_CONFIG', 2);
UPDATE sae31_Protocole SET actionAttendue = 10 WHERE reference = 'USER_CONFIG';
