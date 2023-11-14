package fr.iutfbleau.but2.sae312023.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * La classe <code>ProtocoleModel</code> représente un protocole de test.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class Protocole {
    /**
     * Référence du protocole.
     */
    private String reference;

    /**
     * Action attendue par le protocole.
     */
    private Element actionAttendue;

    /**
     * Description du protocole.
     */
    private String description;

    /**
     * Eléments du menu pour le protocole.
     * La clé est l'identifiant de l'élément et la valeur est l'élément.
     */
    private Map<Integer, Element> elements;

    /**
     * Résultats du protocole.
     * La clé est l'identifiant du résultat et la valeur est le résultat.
     */
    private Map<Integer, Resultat> resultats;

    /**
     * Constructeur de la classe <code>ProtocoleModel</code>.
     * @param protocoleReference référence du protocole
     */
    public Protocole(String protocoleReference) {
        this.reference = protocoleReference;
        this.actionAttendue = null;
        this.description = null;
        this.elements = new HashMap<>();
        this.resultats = new HashMap<>();
    }

    /**
     * Renvoie la référence du protocole.
     * @return la référence du protocole
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * Récupération de la description et des éléments du menu pour le protocole actuel.
     */
    private void getAdditionalInfo() {
        if (this.elements.isEmpty() && this.actionAttendue != null && this.description != null) return;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            DatabaseController db = DatabaseController.getInstance();
            connection = db.getConnection();
            int protocoleActionAttendue;

            String queryProtocole = "SELECT actionAttendue, description FROM " + db.getTableName("Protocole") + " WHERE reference = ? LIMIT 1;";
            ps = connection.prepareStatement(queryProtocole);
            ps.setString(1, this.getReference());
            ResultSet rsProtocole = ps.executeQuery();

            if (rsProtocole.next()) {
                protocoleActionAttendue = rsProtocole.getInt("actionAttendue");
                this.description = rsProtocole.getString("description");
            } else {
                throw new RuntimeException("Erreur d'intégrité de la base de données : le protocole n'existe pas.");
            }

            rsProtocole.close();

            String queryElements = "SELECT id, titre, parent, priorite, COUNT(*) as nbVisites FROM " + db.getTableName("Element") + " E LEFT JOIN " + db.getTableName("ElementVisite") + " EV ON E.id = EV.element WHERE protocole = ? GROUP BY id, parent, priorite ORDER BY parent, priorite;";
            ps = connection.prepareStatement(queryElements);
            ps.setString(1, this.getReference());
            ResultSet rsElements = ps.executeQuery();

            while (rsElements.next()) {
                Element element;
                Element parent = null;

                if (rsElements.getInt("parent") != 0) {
                    if (this.elements.isEmpty())
                        throw new RuntimeException("Erreur d'intégrité de la base de données : un élément du menu possède un parent inconnu.");
                    parent = this.getElement(rsElements.getInt("parent"));
                }

                element = new Element(
                        rsElements.getInt("id"),
                        rsElements.getString("titre"),
                        parent,
                        this,
                        rsElements.getInt("priorite"),
                        rsElements.getInt("nbVisites")
                );

                this.elements.put(element.getId(), element);
                if (element.getId() == protocoleActionAttendue) this.actionAttendue = element;
            }

            rsElements.close();
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {}
            throw new RuntimeException("Erreur de base de données : " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ignored) {}
        }
    }

    /**
     * Renvoie l'action attendue par le protocole.
     * @return l'action attendue par le protocole
     */
    public Element getActionAttendue() {
        if (this.actionAttendue == null) this.getAdditionalInfo();
        return this.actionAttendue;
    }

    /**
     * Renvoie la description du protocole.
     * @return la description du protocole
     */
    public String getDescription() {
        if (this.description == null) this.getAdditionalInfo();
        return this.description;
    }

    /**
     * Retourne un élément du protocole
     * @param elementId id de l'élément
     * @return l'élément demandé ou null si aucun élément avec l'id spécifié fait partie du protocole
     */
    public Element getElement(int elementId) {
        if (this.elements.isEmpty()) this.getAdditionalInfo();
        return this.elements.get(elementId);
    }

    /**
     * Renvoie les éléments du menu pour le protocole.
     * @return un dictionnaire où la clé est l'identifiant de l'élément et la valeur est l'élément
     */
    public Map<Integer, Element> getElements() {
        if (this.elements.isEmpty()) this.getAdditionalInfo();
        return this.elements;
    }

    /**
     * Recherche de protocoles de test
     * @param protocoleReference filtrage des protocoles par référence
     * @return les 10 premiers protocoles correspondants à la référence recherchée
     */
    public static Map<String, Protocole> query(String protocoleReference) {
        DatabaseController db = DatabaseController.getInstance();
        Map<String, Protocole> protocoles = new HashMap<>();
        String query = "SELECT reference FROM " + db.getTableName("Protocole") + " WHERE reference LIKE CONCAT(?, '%') LIMIT 10;";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = db.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, protocoleReference);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String protocoleRef = rs.getString("reference");
                protocoles.put(protocoleRef, new Protocole(protocoleRef));
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {}
            throw new RuntimeException("Erreur de base de données : " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ignored) {}
        }

        return protocoles;
    }

    /**
     * Renvoie les résultats du protocole.
     * @return un dictionnaire où la clé est l'identifiant du résultat et la valeur est le résultat
     */
    public Map<Integer, Resultat> getResultats() {
        if (this.resultats.isEmpty()) {
            this.getAdditionalInfo();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                DatabaseController db = DatabaseController.getInstance();
                connection = db.getConnection();

                String query = "SELECT id FROM " + db.getTableName("Resultat") + " WHERE protocole = ?;";
                ps = connection.prepareStatement(query);
                ps.setString(1, this.getReference());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {;
                    this.resultats.put(rs.getInt("id"), new Resultat(rs.getInt("id"), this));
                }

                try {
                    rs.close();
                } catch (SQLException ignored) {}

                String queryElementsVisites = "SELECT element FROM " + db.getTableName("ElementVisite") + " WHERE resultat = ? ORDER BY ordre;";
                for (Resultat resultat : this.resultats.values()) {
                    ps = connection.prepareStatement(queryElementsVisites);
                    ps.setInt(1, resultat.getId());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Element element = this.getElement(rs.getInt("element"));
                        resultat.ajouter(element);
                    }
                }
                try {
                    rs.close();
                } catch (SQLException ignored) {}
            } catch (SQLException e) {
                try {
                    connection.close();
                } catch (SQLException ignored) {}
                throw new RuntimeException("Erreur de base de données : " + e.getMessage());
            } finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException ignored) {}
            }
        }
        
        return this.resultats;
    }

    /**
     * Vérifie que le protocole en paramètre est le même que le protocole actuel.
     * @param o Autre objet à comparer
     * @return true si les deux ProtocoleModel ont la même référence, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Protocole p)) return false;
        return this.getReference().equals(p.getReference());
    }

    /**
     * Renvoie une représentation textuelle du protocole.
     * @return une représentation textuelle du protocole
     */
    @Override
    public String toString() {
        return "Protocole " + this.getReference();
    }
}


