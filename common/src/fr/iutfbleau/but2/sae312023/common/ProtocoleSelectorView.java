package fr.iutfbleau.but2.sae312023.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * La classe <code>ProtocoleSelectorView</code> correspond à la vue du sélecteur de protocole.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class ProtocoleSelectorView extends JPanel {
    /**
     * Le champ de recherche.
     */
    private JTextField searchField;

    /**
     * Constructeur de la classe <code>ProtocoleSelectorView</code>.
     * @param protocoleList La liste des protocoles.
     */
    public ProtocoleSelectorView(JList<Protocole> protocoleList) {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.setLayout(new BorderLayout());
        this.searchField = new JTextField(Configuration.getInstance().getProperty("protocole", ""));
        this.searchField.setColumns(20);
        JPanel search = new JPanel(new FlowLayout(FlowLayout.CENTER));
        search.add(new JLabel("Rechercher une réference de protocole :"));
        search.add(searchField);
        this.add(search, BorderLayout.NORTH);
        this.add(new JScrollPane(protocoleList), BorderLayout.CENTER);
    }

    /**
     * Ajoute un écouteur de saisie au champ de recherche.
     * @param kl L'écouteur de saisie.
     */
    public void addKeyListener(KeyListener kl) {
        this.searchField.addKeyListener(kl);
    }

    /**
     * Retourne le texte du champ de recherche.
     * @return Le texte du champ de recherche.
     */
    public String getSearchText() {
        return this.searchField.getText();
    }
}
