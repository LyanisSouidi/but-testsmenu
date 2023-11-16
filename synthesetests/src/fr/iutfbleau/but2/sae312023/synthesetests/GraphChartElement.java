package fr.iutfbleau.but2.sae312023.synthesetests;

import java.awt.*;

/**
 * La classe <code>GraphChartElement</code> représente un élément d'un graphique.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class GraphChartElement {
    /**
     * Le label de l'élément.
     */
    private String label;

    /**
     * La valeur de l'élément.
     */
    private int value;

    /**
     * Si la valeur doit être affichée dans la légende.
     */
    private boolean showValue;

    /**
     * Le pourcentage de l'élément.
     */
    private double pourcentage;

    /**
     * Si le pourcentage doit être affiché dans la légende.
     */
    private boolean showPourcentage;

    /**
     * La couleur de l'élément.
     */
    private Color color;

    /**
     * Constructeur d'un élément de graphique.
     * @param label le label de l'élément.
     */
    public GraphChartElement(String label) {
        this.label = label;
        this.value = 0;
        this.pourcentage = 0;
        this.showValue = false;
        this.showPourcentage = false;
    }

    /**
     * Retourne le label de l'élément.
     * @return le label de l'élément.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Definit la valeur de l'élément.
     * @param value la valeur de l'élément.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Retourne la valeur de l'élément.
     * @return la valeur de l'élément.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Si la valeur de l'élément doît être affiché dans la légende.
     * @param showValue true si la valeur doit être affichée dans la légende, false sinon.
     */
    public void showValue(boolean showValue) {
        this.showValue = showValue;
    }

    /**
     * Definit le pourcentage de l'élément.
     * @param pourcentage le pourcentage de l'élément.
     */
    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    /**
     * Retourne le pourcentage de l'élément.
     * @return le pourcentage de l'élément.
     */
    public double getPourcentage() {
        return this.pourcentage;
    }

    /**
     * Si le pourcentage de l'élément doît être affiché dans la légende.
     * @param showPourcentage true si le pourcentage doit être affiché dans la légende, false sinon.
     */
    public void showPourcentage(boolean showPourcentage) {
        this.showPourcentage = showPourcentage;
    }

    /**
     * Definit la couleur de l'élément.
     * @param color la couleur de l'élément.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Retourne la couleur de l'élément.
     * @return la couleur de l'élément.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Retourne une représentation textuelle de l'élément.
     * @return une représentation textuelle de l'élément.
     */
    @Override
    public String toString() {
        String result = this.getLabel();
        if (this.showValue || this.showPourcentage) {
            result += " (";
            if (this.showValue) {
                result += getValue();
            }
            if (this.showValue && this.showPourcentage) {
                result += " | ";
            }
            if (this.showPourcentage) {
                result += (Math.round(this.getPourcentage() * 100.0) / 100.0) + "%";
            }
            result += ")";
        }
        return result;
    }
}
