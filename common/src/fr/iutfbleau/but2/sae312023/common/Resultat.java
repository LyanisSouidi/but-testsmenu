package fr.iutfbleau.but2.sae312023.common;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe <code>ResultatModel</code> représente un résultat de test.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class Resultat {
    /**
     * Identifiant du résultat.
     */
    private int id;

    /**
     * Liste des éléments visités.
     */
    private Map<Integer, ElementVisite> elementsVisites = new HashMap<>();

    /**
     * Protocole associé au résultat.
     */
    private Protocole protocole;

    /**
     * Constructeur de la classe <code>Resultat</code>.
     * @param protocole Le protocole associé au résultat.
     */
    public Resultat(Protocole protocole) {
        this.protocole = protocole;
    }

    /**
     * Constructeur de la classe <code>Resultat</code>.
     * @param id L'identifiant du résultat.
     * @param protocole Le protocole associé au résultat.
     */
    public Resultat(int id, Protocole protocole) {
        this.id = id;
        this.protocole = protocole;
    }

    /**
     * Renvoie l'identifiant du résultat.
     * @return l'identifiant du résultat
     */
    public int getId() {
        return this.id;
    }

    /**
     * Définit l'identifiant du résultat.
     * @param id l'identifiant du résultat
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Renvoie la liste des éléments visités.
     * @return la liste des éléments visités
     */
    public Map<Integer, ElementVisite> getElementsVisites() {
        return this.elementsVisites;
    }

    /**
     * Renvoie le protocole associé au résultat.
     * @return le protocole associé au résultat
     */
    public Protocole getProtocole() {
        return this.protocole;
    }

    /**
     * Ajouter un élément à la liste des éléments visités.
     * @param element l'élément à ajouter
     * @return true si l'élément ajouté est l'action attendue, false sinon
     */
    public boolean ajouter(Element element) {
        if (this.elementsVisites.containsKey(element.getId())) return false;

        ElementVisite elementVisite = new ElementVisite(
            element,
            this.elementsVisites.size(),
            this
        );
        this.elementsVisites.put(elementVisite.getId(), elementVisite);

        return element.equals(this.protocole.getActionAttendue());
    }

    /**
     * Enregistre le résultat dans la base de données.
     */
    public void enregistrer() {
        DatabaseController db = DatabaseController.getInstance();
        Connection connection = db.getConnection();

        String insertResult = "INSERT INTO sae31_Resultat (protocole) VALUES (?)";
        String insertElementVisite = "INSERT INTO sae31_ElementVisite (element, ordre, resultat) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insertResult, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.protocole.getReference());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Erreur d'intégrité de la base de données : l'identifiant du résultat n'a pas été généré.");
            }

            ps = connection.prepareStatement(insertElementVisite);
            for (ElementVisite element : this.getElementsVisites().values()) {
                ps.setInt(1, element.getId());
                ps.setInt(2, element.getOrdre());
                ps.setInt(3, this.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ignored) {}
            throw new RuntimeException("Erreur lors de l'enregistrement du résultat.");
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ignored) {}
        }
    }

    /**
     * Retourne le statut du résultat.
     * @return true si le résultat est réussi, false sinon
     */
    public boolean isSuccessful() {
        for (ElementVisite elementVisite : this.getElementsVisites().values()) {
            if (elementVisite.equals(this.protocole.getActionAttendue())) return true;
        }
        return false;
    }

    /**
     * Verifie si le résultat est égal à un autre résultat via son id.
     * @param o l'objet à comparer
     * @return true si les deux résultats sont les mêmes, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Resultat r)) return false;
        return this.getId() == r.getId();
    }

    /**
     * Retourne une représentation textuelle du résultat.
     * @return une représentation textuelle du résultat
     */
    @Override
    public String toString() {
        return "Résultat n°" + this.getId() + " (" + (this.isSuccessful() ? "réussi" : "échoué") + " après avoir visité " + this.getElementsVisites().size() + " éléments du menu)";
    }
}
