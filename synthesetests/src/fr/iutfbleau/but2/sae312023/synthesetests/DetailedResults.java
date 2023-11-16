package fr.iutfbleau.but2.sae312023.synthesetests;

import fr.iutfbleau.but2.sae312023.common.ElementVisite;
import fr.iutfbleau.but2.sae312023.common.Protocole;
import fr.iutfbleau.but2.sae312023.common.Resultat;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * La classe <code>DetailedResults</code> est une fenêtre affichant les détails des résultats d'un protocole.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class DetailedResults extends JFrame implements ListSelectionListener {
    /**
     * Le protocole dont on veut afficher les résultats.
     */
    private Protocole protocole;

    /**
     * Le modèle de la liste des résultats.
     */
    private DefaultListModel<Resultat> resultatListModel;

    /**
     * La liste des résultats.
     */
    private JList<Resultat> resultatList;

    /**
     * La zone de texte affichant les détails d'un résultat.
     */
    private JTextArea textArea;

    /**
     * Construit une fenêtre affichant les détails des résultats d'un protocole.
     * @param protocole le protocole dont on veut afficher les résultats.
     */
    public DetailedResults(Protocole protocole) {
        super("Détails des résultats du protocole " + protocole.getReference());
        this.protocole = protocole;
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        this.resultatListModel = new DefaultListModel<>();

        for (Resultat resultat : this.protocole.getResultats().values()) {
            resultatListModel.addElement(resultat);
        }

        this.resultatList = new JList<>(this.resultatListModel);
        this.resultatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.resultatList.addListSelectionListener(this);

        this.add(new JScrollPane(resultatList), BorderLayout.WEST);

        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    /**
     * Affiche les informations du résultat sélectionné.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Resultat selectedResultat = this.resultatList.getSelectedValue();
            if (selectedResultat != null) {
                StringBuilder text = new StringBuilder(selectedResultat + "\n\n");
                for (ElementVisite elementVisite : selectedResultat.getElementsVisites().values()) {
                    text.append((elementVisite.getOrdre() + 1)).append(". ").append(elementVisite.getPath()).append("\n");
                }

                this.textArea.setText(text.toString());
            }
        }
    }
}
