package fr.iutfbleau.but2.sae312023;

import java.util.List;

/**
 * La classe <code>Resultat</code> représente un résultat de test.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class Resultat {
    /**
     * Liste des éléments visités.
     */
    private List<ElementVisite> elementsVisites;

    /**
     * Protocole associé au résultat.
     */
    private Protocole protocole;

    /**
     * Renvoie la liste des éléments visités.
     * @return la liste des éléments visités
     */
    public List<ElementVisite> getElementsVisites() {
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
     * @param e l'élément à ajouter
     * @return true si l'élément ajouté est l'action attendue, false sinon
     */
    public boolean ajouter(Element e) {
        // TODO: vérifier que l'élément n'est pas déjà dans la liste pour ne pas faire de doublons
        // TODO: vérifier si l'élément ajouté est l'action attendue pour renvoyer true
        int ordre = 0;
        if (!this.elementsVisites.isEmpty()) {
            ordre = this.elementsVisites.get(this.elementsVisites.size() - 1).getOrdre() + 1;
        }

        ElementVisite elementVisite = new ElementVisite(e, ordre, this);
        this.elementsVisites.add(elementVisite);
        return false;
    }

    /**
     * Enregistre le résultat dans la base de données.
     * @return true si le résultat a été enregistré avec succès, false sinon
     */
    public boolean enregistrer() {
        // TODO
        return false;
    }
}
