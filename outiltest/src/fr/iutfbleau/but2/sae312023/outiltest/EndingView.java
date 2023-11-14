package fr.iutfbleau.but2.sae312023.outiltest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * La classe <code>EndingView</code> est la vue affichée en fin du test.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class EndingView extends JPanel {
    /**
     * Le bouton pour recommencer le test.
     */
    JButton buttonRestart;

    /**
     * Le bouton pour retourner au menu principal.
     */
    JButton buttonMainMenu;

    /**
     * Le bouton pour quitter l'outil de test.
     */
    JButton buttonQuit;

    /**
     * Constructeur de la classe <code>EndingView</code>.
     */
    public EndingView() {
        this.setLayout(new BorderLayout());
        JLabel text = new JLabel("Fin du test", SwingConstants.CENTER);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.buttonRestart = new JButton("Recommencer le test");
        this.buttonMainMenu = new JButton("Retour au menu principal");
        this.buttonQuit = new JButton("Quitter l'outil de test");
        buttonsPanel.add(this.buttonRestart);
        buttonsPanel.add(this.buttonMainMenu);
        buttonsPanel.add(this.buttonQuit);
        this.add(text, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /**
     * Ajoute un écouteur d'actions sur les boutons.
     * @param al l'écouteur d'actions.
     */
    public void addActionListener(ActionListener al) {
        this.buttonRestart.addActionListener(al);
        this.buttonMainMenu.addActionListener(al);
        this.buttonQuit.addActionListener(al);
    }
}
