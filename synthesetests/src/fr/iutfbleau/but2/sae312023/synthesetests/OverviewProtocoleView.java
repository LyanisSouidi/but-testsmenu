package fr.iutfbleau.but2.sae312023.synthesetests;

import fr.iutfbleau.but2.sae312023.common.Protocole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * La classe <code>OverviewProtocoleView</code> représente la vue d'ensemble des résultats d'un protocole.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class OverviewProtocoleView extends JPanel {
    /**
     * Le bouton pour changer de protocole.
     */
    private JButton buttonSwitchProtocole;

    /**
     * Le bouton pour afficher les détails des résultats.
     */
    private JButton buttonDetails;

    /**
     * Constructeur de la vue d'ensemble des résultats d'un protocole.
     * @param protocole le protocole à utiliser.
     * @param graphs les graphiques à afficher.
     */
    public OverviewProtocoleView(Protocole protocole, List<Graph> graphs) {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel titleText = new JLabel("Analyse du protocole " + protocole.getReference() + " : " + protocole.getResultats().values().size() + " résultats", SwingConstants.CENTER);

        this.setLayout(new BorderLayout());
        this.add(titleText, BorderLayout.NORTH);

        JPanel graphsPanel = new JPanel();
        graphsPanel.setLayout(new GridLayout(1, 2));
        for (Graph graph : graphs) {
            graphsPanel.add(graph);
        }

        this.add(graphsPanel, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.buttonSwitchProtocole = new JButton("Changer de protocole");
        this.buttonDetails = new JButton("Détails des résultats");
        panelButtons.add(this.buttonSwitchProtocole);
        panelButtons.add(this.buttonDetails);

        this.add(panelButtons, BorderLayout.SOUTH);
    }

    /**
     * Ajoute un écouteur d'événements sur les boutons.
     * @param al l'écouteur à ajouter.
     */
    public void addActionListener(ActionListener al) {
        this.buttonSwitchProtocole.addActionListener(al);
        this.buttonDetails.addActionListener(al);
    }
}
