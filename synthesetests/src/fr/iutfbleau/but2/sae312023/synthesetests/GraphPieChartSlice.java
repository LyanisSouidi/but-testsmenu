package fr.iutfbleau.but2.sae312023.synthesetests;

/**
 * La classe <code>GraphPieChartSlice</code> représente une part d'un graphique en camembert.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class GraphPieChartSlice extends GraphChartElement {
    /**
     * L'angle de la part.
     */
    private int arcAngle;

    /**
     * L'angle de départ de la part.
     */
    private int arcStartAngle;

    /**
     * Constructeur d'une part de graphique en camembert.
     * @param label le label de la part.
     */
    public GraphPieChartSlice(String label) {
        super(label);
    }

    /**
     * Definit l'angle de la part.
     * @param arcAngle
     */
    public void setArcAngle(int arcAngle) {
        this.arcAngle = arcAngle;
    }

    /**
     * Retourne l'angle de la part.
     * @return l'angle de la part.
     */
    public int getArcAngle() {
        return this.arcAngle;
    }

    /**
     * Definit l'angle de départ de la part.
     * @param arcStartAngle
     */
    public void setArcStartAngle(int arcStartAngle) {
        this.arcStartAngle = arcStartAngle;
    }

    /**
     * Retourne l'angle de départ de la part.
     * @return l'angle de départ de la part.
     */
    public int getArcStartAngle() {
        return this.arcStartAngle;
    }
}
