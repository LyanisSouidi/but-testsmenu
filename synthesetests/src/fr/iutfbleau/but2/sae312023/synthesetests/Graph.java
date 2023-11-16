package fr.iutfbleau.but2.sae312023.synthesetests;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>Graph</code> est un panneau affichant un graphique (titre, graph et légende).
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class Graph extends JPanel {
    public Graph(String title, GraphChart chart, GraphLegend legend) {
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.setLayout(new BorderLayout());

        this.add(new JLabel(title, SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(chart, BorderLayout.CENTER);
        this.add(legend, BorderLayout.SOUTH);
    }
}
