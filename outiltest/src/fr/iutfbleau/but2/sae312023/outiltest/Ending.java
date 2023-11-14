package fr.iutfbleau.but2.sae312023.outiltest;

import fr.iutfbleau.but2.sae312023.common.DatabaseController;
import fr.iutfbleau.but2.sae312023.common.Protocole;
import fr.iutfbleau.but2.sae312023.common.ProtocoleSelector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * La classe <code>Ending</code> est le contrôleur de la vue affichée en fin du test.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class Ending implements ActionListener {
    /**
     * La fenêtre principale.
     */
    private JFrame frame;

    /**
     * La vue affichée.
     */
    private EndingView view;

    /**
     * Le protocole testé.
     */
    private Protocole protocole;

    /**
     * Constructeur de la classe <code>Ending</code>.
     * @param frame la fenêtre principale.
     * @param protocole le protocole testé.
     */
    public Ending(JFrame frame, Protocole protocole) {
        this.frame = frame;
        this.protocole = protocole;
        this.frame.setTitle("Fin du test");
        this.view = new EndingView();
        this.frame.setContentPane(this.view);
        this.view.addActionListener(this);
        this.frame.setVisible(true);
    }

    /**
     * Gère les actions de l'utilisateur.
     * @param e l'événement.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Recommencer le test":
                MenuTester.start(this.frame, this.protocole);
                break;
            case "Retour au menu principal":
                new ProtocoleSelector(this.frame, MenuTester.class);
                break;
            case "Quitter l'outil de test":
                DatabaseController.getInstance().close();
                System.exit(0);
        }

    }
}
