package fr.iutfbleau.but2.sae312023.common;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * La classe <code>ProtocoleSelector</code> permet de rechercher et selectionner un protocole
 * à utiliser avec le programme.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class ProtocoleSelector implements ListSelectionListener, KeyListener, DatabaseConnectListener {
    /**
     * La fenêtre principale.
     */
    private JFrame frame;

    /**
     * La vue.
     */
    private ProtocoleSelectorView view;

    /**
     * La liste des protocoles.
     */
    private JList<Protocole> protocoleList;

    /**
     * Le modèle de la liste des protocoles.
     */
    private DefaultListModel<Protocole> protocoleListModel;

    /**
     * Le contrôleur à lancer après la sélection du protocole.
     */
    private static Class<? extends ProgramController> nextController = null;


    /**
     * Constructeur de la classe <code>ProtocoleSelector</code>.
     * @param frame La fenêtre principale.
     * @param nextController Le contrôleur à lancer après la sélection du protocole.
     * @param <T> Le type du contrôleur à lancer après la sélection du protocole.
     */
    public <T extends ProgramController> ProtocoleSelector(JFrame frame, Class<T> nextController) {
        this.frame = frame;
        this.frame.setVisible(false);
        ProtocoleSelector.nextController = nextController;

        if (DatabaseController.getInstance(this).isInitialized()) {
            this.initUI();
        }
    }

    /**
     * Initialise l'interface utilisateur.
     */
    private void initUI() {
        this.frame = new JFrame("Sélection du protocole de test");
        this.frame.setSize(700, 500);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DatabaseController.getInstance().close();
            }
        });
        this.frame.setVisible(true);

        this.protocoleListModel = new DefaultListModel<>();
        this.protocoleList = new JList<>(this.protocoleListModel);
        this.protocoleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.protocoleList.addListSelectionListener(this);

        this.view = new ProtocoleSelectorView(protocoleList);
        this.view.addKeyListener(this);
        this.frame.setContentPane(this.view);
        this.updateList();
        this.frame.setVisible(true);
    }

    /**
     * Retourne le contrôleur à lancer après la sélection du protocole.
     * @return le contrôleur à lancer après la sélection du protocole.
     */
    public static Class<? extends ProgramController> getNextController() {
        return ProtocoleSelector.nextController;
    }

    /**
     * Met à jour la liste des protocoles.
     */
    private void updateList() {
        Collection<Protocole> protocoles = Protocole.query(this.view.getSearchText()).values();
        this.protocoleListModel.clear();
        for (Protocole protocole : protocoles) {
            protocoleListModel.addElement(protocole);
        }
    }

    /**
     * Lance le contrôleur suivant une fois le protocole sélectionné.
     * @param e l'evenement de sélection.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Protocole selectedProtocole = this.protocoleList.getSelectedValue();
            if (selectedProtocole != null) {
                try {
                    Method startController = nextController.getMethod("start", JFrame.class, Protocole.class);
                    this.frame.setContentPane(new WaitingView());
                    startController.invoke(null, frame, selectedProtocole);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                    throw new RuntimeException("ProgramController n'est pas implémenté correctement dans " + nextController.getName() + ".");
                }
            }
        }
    }

    /**
     * Initialise l'interface utilisateur lorsque la connexion à la base de données est établie.
     */
    @Override
    public void onConnect() {
        this.initUI();
    }

    /**
     * Met à jour la liste des protocoles lorsque des modifications ont été apporté au champ de recherche.
     * @param e l'evenement de relachement de touche.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.updateList();
    }

    @Override
    public void keyPressed(KeyEvent ignored) {}
    @Override
    public void keyTyped(KeyEvent ignored) {}
}
