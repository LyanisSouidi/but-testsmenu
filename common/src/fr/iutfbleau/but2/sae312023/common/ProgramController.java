package fr.iutfbleau.but2.sae312023.common;

import javax.swing.JFrame;

/**
 * L'interface <code>ProgramController</code> est utilisée pour définir le contrôleur du
 * programme à lancer après avoir choisi le protocole.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public interface ProgramController {
    /**
     * Lance le programme.
     * @param frame la fenêtre principale
     * @param protocole le protocole choisi
     */
    static void start(JFrame frame, Protocole protocole) {}
}
