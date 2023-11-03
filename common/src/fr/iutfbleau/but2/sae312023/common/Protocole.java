package fr.iutfbleau.but2.sae312023.common;

/**
 * La classe <code>Protocole</code> représente un protocole de test.
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
     * Liste des éléments du menu pour le protocole.
     */
    private Element[] elements;

    /**
     * Renvoie la référence du protocole.
     * @return la référence du protocole
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * Renvoie l'action attendue par le protocole.
     * @return l'action attendue par le protocole
     */
    public Element getActionAttendue() {
        return this.actionAttendue;
    }

    /**
     * Renvoie la description du protocole.
     * @return la description du protocole
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Renvoie la liste des éléments du menu pour le protocole.
     * @return la liste des éléments du menu pour le protocole
     */
    public Element[] getElements() {
        return this.elements;
    }
}
