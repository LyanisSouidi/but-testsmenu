package fr.iutfbleau.but2.sae312023.synthesetests;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * La classe <code>GraphLegend</code> est un panneau affichant la légende d'un graphique.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class GraphLegend extends JPanel {
    /**
     * Les éléments du graphique.
     */
    private List<? extends GraphChartElement> items;

    /**
     * Constructeur de la légende du graphique.
     * @param items les éléments du graphique.
     */
    public GraphLegend(List<? extends GraphChartElement> items) {
        this.items = items;
        this.setPreferredSize(new Dimension(0, 100));
    }

    /**
     * Dessine la légende du graphique.
     * @param g le contexte graphique.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        int lineHeight = 20;

        for (GraphChartElement item : items) {
            String text = item.toString();
            int textWidth = g.getFontMetrics().stringWidth(text);

            if (x + 15 + textWidth > this.getWidth()) {
                x = 10;
                y += lineHeight;
            }

            g.setColor(item.getColor());
            g.fillRect(x, y, 10, 10);
            g.setColor(Color.BLACK);
            g.drawString(text, x + 15, y + 10);

            x += 30 + textWidth;
        }
        this.setPreferredSize(new Dimension(0, lineHeight + y + 10));
    }
}
