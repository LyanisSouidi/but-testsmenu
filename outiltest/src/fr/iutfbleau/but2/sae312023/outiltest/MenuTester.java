package fr.iutfbleau.but2.sae312023.outiltest;

import fr.iutfbleau.but2.sae312023.common.Element;
import fr.iutfbleau.but2.sae312023.common.ProgramController;
import fr.iutfbleau.but2.sae312023.common.Protocole;
import fr.iutfbleau.but2.sae312023.common.Resultat;
import fr.iutfbleau.but2.sae312023.common.WaitingView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * La classe <code>MenuTester</code> permet de tester un protocole.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class MenuTester implements ProgramController, MouseListener {
    /**
     * La fenêtre principale.
     */
    private JFrame frame;

    /**
     * Le protocole testé.
     */
    private Protocole protocole;

    /**
     * La vue affichée.
     */
    private MenuTesterView view;

    /**
     * Le résultat du test.
     */
    private Resultat resultat;

    /**
     * L'arbre affiché.
     */
    private JTree tree;

    /**
     * Démarre le test d'un protocole.
     * @param frame la fenêtre principale.
     * @param protocole le protocole testé.
     */
    public static void start(JFrame frame, Protocole protocole) {
        new MenuTester(frame, protocole);
    }

    /**
     * Constructeur de la classe <code>MenuTester</code>.
     * @param frame la fenêtre principale.
     * @param protocole le protocole testé.
     */
    private MenuTester(JFrame frame, Protocole protocole) {
        this.frame = frame;
        this.protocole = protocole;
        this.frame.setContentPane(new WaitingView());
        this.frame.setVisible(true);
        this.frame.setTitle(this.protocole.toString());
        this.tree = new JTree();
        this.tree.setRootVisible(false);
        this.tree.addMouseListener(this);
        this.updateTree();
        this.view = new MenuTesterView(this.tree);
        this.frame.setContentPane(this.view);
        this.frame.setVisible(true);
    }

    /**
     * Met à jour l'arbre.
     */
    private void updateTree() {
        try {
            DefaultMutableTreeNode treeNode = this.buildTree();
            this.tree.setModel(new DefaultTreeModel(treeNode));
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this.view, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Construit l'arbre du menu.
     * @return l'arbre du menu.
     */
    public DefaultMutableTreeNode buildTree() {
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(this.protocole);

        for (Element element : this.protocole.getElements().values()) {
            if (element.getParent() == null) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(element);
                treeNode.add(childNode);
                this.buildTree(element, childNode);
            }
        }

        return treeNode;
    }

    /**
     * Construit l'arbre du menu.
     * @param element l'élément courant.
     * @param node le nœud courant.
     */
    private void buildTree(Element element, DefaultMutableTreeNode node) {
        for (Element e : this.protocole.getElements().values()) {
            if (element.equals(e.getParent())) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(e);
                node.add(childNode);
                this.buildTree(e, childNode);
            }
        }
    }

    /**
     * Gère les actions de l'utilisateur sur l'arbre.
     * @param e l'événement.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = this.tree.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            Element element = (Element)node.getUserObject();
            if (this.resultat == null) {
                this.resultat = new Resultat(this.protocole);
            }

            this.resultat.ajouter(element);
            if (node.getChildCount() == 0) {
                this.resultat.enregistrer();
                new Ending(this.frame, this.protocole);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
