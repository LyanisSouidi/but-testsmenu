package fr.iutfbleau.but2.sae312023.common;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>WaitingView</code> est utilisé pour faire patienter l'utilisateur
 * lors de la récupération des données depuis la base de données.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class WaitingView extends JPanel {
    /**
     * Constructeur de la classe <code>WaitingView</code>.
     */
    public WaitingView() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Veuillez patienter pendant le chargement des données...");
        panel.add(label, BorderLayout.CENTER);
    }
}
