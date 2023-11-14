package fr.iutfbleau.but2.sae312023.common;

import java.util.*;

/**
 * La classe <code>ElementModel</code> représente un élément d'un menu.
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
     * Nombre de fois où l'élément a été visité lors des différents tests.
     */
    private int nombreVisite;

    /**
     * Constructeur de la classe <code>ElementModel</code>.
     * @param id Identifiant de l'élément
     * @param titre Titre de l'élément
     * @param parent Parent de l'élément
     * @param protocole Protocole de test lié à l'élément
     * @param priorite Priorité de l'élément dans le menu
     * @param nbVisite Nombre de fois où l'élément a été visité lors des différents tests
     */
    public Element(int id, String titre, Element parent, Protocole protocole, int priorite, int nbVisite) {
        this.id = id;
        this.titre = titre;
        this.parent = parent;
        this.protocole = protocole;
        this.priorite = priorite;
        this.nombreVisite = nbVisite;
    }

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

    /**
     * Renvoie le nombre de fois où l'élément a été visité lors des différents tests.
     * @return le nombre de vistes
     */
    public int getNombreVisite() {
        return this.nombreVisite;
    }

    /**
     * Récupère les enfants de l'élément.
     * @return un dictionnaire où la clé est l'identifiant de l'élément et la valeur est l'élément
     */
    public Map<Integer, Element> getChildren() {
        Map<Integer, Element> childrens = new HashMap<>();
        for (Element element : this.protocole.getElements().values()) {
            if (element.getParent().equals(this)) childrens.put(element.getId(), element);
        }
        return childrens;
    }

    /**
     * Retourne le chemin de l'élément.
     * @return le chemin de l'élément
     */
    public String getPath() {
        String path = this.getTitre();
        Element parent = this.getParent();
        while (parent != null) {
            path = parent.getTitre() + " > " + path;
            parent = parent.getParent();
        }
        return path;
    }

    /**
     * Vérifie que l'élément en paramètre est le même que l'élément actuel.
     * @param o Autre objet à comparer
     * @return true si les deux ElementModel ont le même id, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Element e)) return false;
        return this.getId() == e.getId();
    }

    /**
     * Retourne une représentation de l'élément sous forme de chaîne de caractères.
     * @return une représentation de l'élément sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        return this.getTitre();
    }
}
