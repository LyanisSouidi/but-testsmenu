# Évaluation de menu

Cette application permet d'analyser différente organisation de menus afin
de déterminer la meilleure organisation possible.

Le projet est décomposé en 3 parties :
- [Outil de test](./outiltest) : executé par un testeur afin de générer des données;
- [Sythèse des tests](./synthesetests) : executé par un développeur afin de réorganiser
    les menus en fonction des résultats des tests;
- [Module commun](./common) : contient les classes communes aux deux programmes
    précédents.

## Prérequis

Vous devez initialiser la base de données avant de lancer les programmes.
Utilisez [`common/db.sql`](./common/db.sql) pour créer les tables nécessaires.

## Configuration (facultatif)

Vous pouvez modifier la configuration à l'aide de variables, ces variables
vous seront demandé lors de l'utilisation du programme si elles ne sont pas définies
au préalables. Voici les variables disponibles
- `dbURL` : URL de connexion à la base de données;
- `dbUser` : nom d'utilisateur pour la connexion à la base de données;
- `dbPasswd` : mot de passe pour la connexion à la base de données;
- `tablePrefix` : préfixe des tables dans la base de données;
- `protocole` : protocole de test à utiliser.

Ces variables peuvent être définies de deux manières différentes avant le lancement du programme :

### via un fichier
Vous pouvez créer un fichier de configuration utilisable par les deux programmes.
Ce fichier doit se nommer `app.properties` et doit être placé dans le dossier de travail
depuis lequel vous lancer le programme.  
Voici un exemple de fichier de configuration :
```properties
dbURL=jdbc:mariadb://localhost:3306/sae31_2023
dbUser=sae31_2023
...
```

### via les arguments de la ligne de commande

Vous pouvez également passer les paramètres directement en ligne de commande sous la forme :
```console
$ java -jar outiltest.jar --protocole <protocole> ...
```

---

Réalisé par Hugo Dimitrijevic, Aissame Bai et Lyanis Souidi dans le cadre de la [SAÉ 3.1 FI 2023/2024 à l'IUT de Fontainebleau](http://www.iut-fbleau.fr/sitebp/sae3/31_2023/R9O9Y6NMKZMEE0M1.php).
