package fr.iutfbleau.but2.sae312023.outiltest;

import fr.iutfbleau.but2.sae312023.common.Protocole;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * La classe <code>MenuTesterView</code> est la vue affichée lors du test.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class MenuTesterView extends JPanel {
    /**
     * Constructeur de la classe <code>MenuTesterView</code>.
     * @param tree l'arbre des éléments du protocole.
     */
    public MenuTesterView(JTree tree) {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        JTextArea description = new JTextArea("Description du protocole :\n" + ((Protocole) treeNode.getUserObject()).getDescription());
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        this.setLayout(new BorderLayout());
        this.add(description, BorderLayout.NORTH);
        this.add(new JScrollPane(tree), BorderLayout.CENTER);
    }
}
