package fr.iutfbleau.but2.sae312023.common;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>DatabaseConnectUI</code> est une fenêtre permettant de renseigner les
 * informations de connexion à la base de données.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class DatabaseConnectUI extends JFrame {
    /**
     * Listener appelé lorsque la connexion à la base de données est établie.
     */
    private DatabaseConnectListener listener;

    /**
     * Constructeur de la classe <code>DatabaseConnectUI</code>.
     * Initialise la fenêtre et affiche les champs de connexion.
     * @param databaseController le contrôleur de la base de données.
     * @param listener le listener appelé lorsque la connexion à la base de données est établie.
     */
    public DatabaseConnectUI(DatabaseController databaseController, DatabaseConnectListener listener) {
        super("Connexion à la base de données");
        this.listener = listener;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setVisible(true);

        Configuration cfg = Configuration.getInstance();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Veuillez entrer vos identifiants de connexion à la base de données.");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.X_AXIS));
        JLabel urlLabel = new JLabel("URL : ");
        urlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        urlPanel.add(urlLabel);
        JTextField urlField = new JTextField(cfg.getProperty("dbURL", DatabaseController.getDefaultURL()));
        urlField.setAlignmentX(Component.CENTER_ALIGNMENT);
        urlPanel.add(urlField);
        panel.add(urlPanel);

        JPanel tablePrefixPanel = new JPanel();
        tablePrefixPanel.setLayout(new BoxLayout(tablePrefixPanel, BoxLayout.X_AXIS));
        JLabel tablePrefixLabel = new JLabel("Préfixe des tables : ");
        tablePrefixLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePrefixPanel.add(tablePrefixLabel);
        JTextField tablePrefixField = new JTextField(cfg.getProperty("tablePrefix", DatabaseController.getDefaultTablePrefix()));
        tablePrefixField.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePrefixPanel.add(tablePrefixField);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        JLabel userLabel = new JLabel("Utilisateur : ");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(userLabel);
        JTextField userField = new JTextField(cfg.getProperty("dbUser", DatabaseController.getDefaultUser()));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(userField);
        panel.add(userPanel);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        JLabel passwordLabel = new JLabel("Mot de passe : ");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField(cfg.getProperty("dbPasswd", ""));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(passwordField);
        panel.add(passwordPanel);

        JButton connectButton = new JButton("Se connecter");
        connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(connectButton);

        this.add(panel);

        connectButton.addActionListener(e -> {
            String url = urlField.getText();
            String user = userField.getText();
            String password = new String(passwordField.getPassword());
            try {
                databaseController.setTablePrefix(tablePrefixField.getText());
                databaseController.connect(url, user, password);
                databaseController.setInitialized(true);
                if (listener != null) listener.onConnect();
                this.dispose();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
