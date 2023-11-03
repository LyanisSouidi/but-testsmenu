package fr.iutfbleau.but2.sae312023.common;

/**
 * La classe <code>ElementVisite</code> est utilisé pour contenir les attributs
 * d'un élement du menu visité lors d'un test.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class ElementVisite {
    /**
     * Élément visité.
     */
    private Element element;

    /**
     * Ordre de visite de l'élément.
     */
    private int ordre;

    /**
     * Résultat associé à l'élément visité.
     */
    private Resultat resultat;

    /**
     * Constructeur de la classe ElementVisite.
     * @param element l'élément visité
     * @param ordre l'ordre de visite de l'élément
     * @param resultat le résultat associé à l'élément visité
     */
    public ElementVisite(Element element, int ordre, Resultat resultat) {
        this.element = element;
        this.ordre = ordre;
        this.resultat = resultat;
    }

    /**
     * Renvoie l'élément visité.
     * @return l'élément visité
     */
    public Element getElement() {
        return this.element;
    }

    /**
     * Renvoie l'ordre de visite de l'élément.
     * @return l'ordre de visite de l'élément
     */
    public int getOrdre() {
        return this.ordre;
    }

    /**
     * Renvoie le résultat associé à l'élément visité.
     * @return le résultat associé à l'élément visité
     */
    public Resultat getResultat() {
        return this.resultat;
    }
}
