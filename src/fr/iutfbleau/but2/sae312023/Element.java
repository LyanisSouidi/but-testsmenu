package fr.iutfbleau.but2.sae312023;

/**
 * Classe représentant un élément d'un menu.
 */
public class Element {
    /**
     * Identifiant de l'élément.
     */
    private int id;

    /**
     * Titre de l'élément.
     */
    private String titre;

    private Element parent;
    private Protocole protocole;
    private int priorite;

    public int getId() {
        return id;
    }
}
