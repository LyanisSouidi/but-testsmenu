package fr.iutfbleau.but2.sae312023.common;

/**
 * La classe <code>Element</code> représente un élément d'un menu.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
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

    /**
     * Parent de l'élément.
     */
    private Element parent;

    /**
     * Protocole de test lié à l'élément.
     */
    private Protocole protocole;

    /**
     * Priorité de l'élément dans le menu.
     */
    private int priorite;

    /**
     * Renvoie l'identifiant de l'élément.
     * @return l'identifiant de l'élément
     */
    public int getId() {
        return this.id;
    }

    /**
     * Renvoie le titre de l'élément.
     * @return le titre de l'élément
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Renvoie le parent de l'élément s'il existe.
     * @return le parent de l'élément
     */
    public Element getParent() {
        return this.parent;
    }

    /**
     * Renvoie le protocole de l'élément.
     * @return le protocole de l'élément
     */
    public Protocole getProtocole() {
        return this.protocole;
    }

    /**
     * Renvoie la priorité de l'élément dans le menu.
     * @return la priorité de l'élément
     */
    public int getPriorite() {
        return this.priorite;
    }
}
