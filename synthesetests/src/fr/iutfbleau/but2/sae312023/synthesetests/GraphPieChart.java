package fr.iutfbleau.but2.sae312023.synthesetests;

import java.awt.*;
import java.util.List;

/**
 * La classe <code>GraphPieChart</code> est un panneau affichant un graphique en camembert.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
class GraphPieChart extends GraphChart {
    /**
     * Les parts du camembert.
     */
    private List<GraphPieChartSlice> slices;

    /**
     * Constructeur du graphique en camembert.
     * @param graphPieChartSlices les parts du camembert.
     */
    public GraphPieChart(List<GraphPieChartSlice> graphPieChartSlices) {
        this.slices = graphPieChartSlices;
        this.setPreferredSize(new Dimension(300, 300));
    }

    /**
     * Dessine le graphique en camembert.
     * @param g le contexte graphique.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int maxSize = Math.min(this.getBounds().width, this.getBounds().height);

        for (GraphPieChartSlice slice : this.slices) {
            g.setColor(slice.getColor());
            g.fillArc((this.getBounds().width - maxSize) / 2, 0, maxSize, maxSize, slice.getArcStartAngle(), slice.getArcAngle());
        }
    }
}
    