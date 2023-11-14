package fr.iutfbleau.but2.sae312023.outiltest;

import fr.iutfbleau.but2.sae312023.common.Configuration;
import fr.iutfbleau.but2.sae312023.common.ProtocoleSelector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * La classe <code>OutilTest</code> est le point d'entrée du programme.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
class OutilTest extends JFrame {
    /**
     * Le titre de la fenêtre.
     */
    private static final String frameTitle = "Outil de test";

    /**
     * Constructeur de la fenêtre principale.
     */
    private OutilTest() {
        super(OutilTest.frameTitle);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Change le titre de la fenêtre.
     * @param title le nouveau titre.
     */
    @Override
    public void setTitle(String title) {
        super.setTitle(title.isEmpty() ? OutilTest.frameTitle : OutilTest.frameTitle + " - " + title);
    }

    /**
     * Point d'entrée du programme.
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Configuration.init(args);
            OutilTest var1 = new OutilTest();
            new ProtocoleSelector(var1, MenuTester.class);
        });
    }
}
