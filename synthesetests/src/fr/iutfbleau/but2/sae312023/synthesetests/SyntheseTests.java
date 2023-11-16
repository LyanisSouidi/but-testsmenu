package fr.iutfbleau.but2.sae312023.synthesetests;

import fr.iutfbleau.but2.sae312023.common.Configuration;
import fr.iutfbleau.but2.sae312023.common.ProtocoleSelector;

import javax.swing.*;

/**
 * La classe <code>SyntheseTests</code> est le point d'entrée du programme.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class SyntheseTests extends JFrame {
    /**
     * Le titre de la fenêtre.
     */
    private static final String frameTitle = "Synthèse des tests";

    /**
     * Constructeur de la fenêtre principale.
     */
    private SyntheseTests() {
        super(SyntheseTests.frameTitle);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setVisible(true);
    }

    /**
     * Change le titre de la fenêtre.
     * @param title le nouveau titre.
     */
    @Override
    public void setTitle(String title) {
        super.setTitle(title.isEmpty() ? SyntheseTests.frameTitle : SyntheseTests.frameTitle + " - " + title);
    }

    /**
     * Point d'entrée du programme.
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Configuration.init(args);
            SyntheseTests frame = new SyntheseTests();
            new ProtocoleSelector(frame, OverviewProtocole.class);
        });
    }
}
