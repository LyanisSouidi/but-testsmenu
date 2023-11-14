package fr.iutfbleau.but2.sae312023.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La classe <code>DatabaseController</code> est un singleton qui permet de gérer la connexion à la base de données.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class DatabaseController {
    /**
     * L'instance unique du contrôleur de base de données.
     */
    private static DatabaseController instance = null;

    /**
     * L'URL de connexion par défaut.
     */
    private static String defaultURL = "jdbc:mariadb://dwarves.iut-fbleau.fr/souidi";

    /**
     * L'utilisateur par défaut.
     */
    private static String defaultUser = "souidi";

    /**
     * Le préfixe de table par défaut.
     */
    private static String defaultTablePrefix = "sae31_";

    /**
     * L'URL utilisée lors de la dernière connexion.
     */
    private String lastURL;

    /**
     * L'utilisateur utilisé lors de la dernière connexion.
     */
    private String lastUser;

    /**
     * Le mot de passe utilisé lors de la dernière connexion.
     */
    private String lastPassword;

    /**
     * Le préfixe de table actuel.
     */
    private String tablePrefix;

    /**
     * La connexion à la base de données.
     */
    private Connection connection;

    /**
     * Indique si le contrôleur de base de données a été initialisé.
     */
    private boolean initialized;


    /**
     * Constructeur privé du contrôleur de base de données.
     * @param listener le listener de connexion
     * @see DatabaseController#getInstance(DatabaseConnectListener) méthode servant à initialiser le contrôleur de base de données
     */
    private DatabaseController(DatabaseConnectListener listener) {
        this.initialized = false;
        new DatabaseConnectUI(this, listener);
    }

    /**
     * Retourne le préfixe de table par défaut.
     * @return le préfixe de table par défaut
     */
    public static String getDefaultTablePrefix() {
        return DatabaseController.defaultTablePrefix;
    }

    /**
     * Retourne le préfixe de table actuel.
     * @return le préfixe de table actuel
     */
    public boolean isInitialized() {
        return this.initialized;
    }

    /**
     * Définit si le contrôleur de base de données a été initialisé.
     * @param initialized <code>true</code> si le contrôleur de base de données a été initialisé, <code>false</code> sinon
     */
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    /**
     * Retourne l'instance unique du contrôleur de base de données.
     * @param listener le listener de connexion utilisée si la connexion n'a pas été initialisée
     * @return l'instance unique du contrôleur de base de données
     */
    public static DatabaseController getInstance(DatabaseConnectListener listener) {
        if (instance == null) instance = new DatabaseController(listener);
        return instance;
    }

    /**
     * Retourne l'instance unique du contrôleur de base de données.
     * @return l'instance unique du contrôleur de base de données
     * @throws RuntimeException si le contrôleur de base de données n'a pas été initialisé
     * @see DatabaseController#getInstance(DatabaseConnectListener) méthode servant à initialiser le contrôleur de base de données
     */
    public static DatabaseController getInstance() {
        if (instance == null) throw new RuntimeException("Le contrôleur de base de données n'a pas été initialisé.");
        return instance;
    }

    /**
     * Retourne l'URL de connexion par défaut.
     * @return l'URL de connexion par défaut
     */
    public static String getDefaultURL() {
        return DatabaseController.defaultURL;
    }

    /**
     * Retourne l'utilisateur par défaut.
     * @return l'utilisateur par défaut
     */
    public static String getDefaultUser() {
        return DatabaseController.defaultUser;
    }

    /**
     * Ferme la connexion à la base de données.
     */
    public void close() {
        try {
            if (this.connection != null) this.connection.close();
        } catch (SQLException ignored) {}
    }

    /**
     * Connecte le contrôleur de base de données à la base de données.
     * @param url l'URL de connexion
     * @param user l'utilisateur
     * @param password le mot de passe
     */
    public void connect(String url, String user, String password) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.lastURL = url;
            this.lastUser = user;
            this.lastPassword = password;
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données : le pilote JDBC pour MariaDB n'a pas été trouvé.");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données. Vérifiez vos identifiants.");
        }
    }

    /**
     * Reconnecte le contrôleur de base de données à la base de données
     * en utilisant les derniers identifiants utilisées.
     */
    private void reconnect() {
        connect(this.lastURL, this.lastUser, this.lastPassword);
    }

    /**
     * Retourne la connexion à la base de données.
     * @return la connexion à la base de données
     */
    public Connection getConnection() {
        try {
            if (this.connection.isClosed()) reconnect();
        } catch (SQLException e) {
            throw new RuntimeException("Une erreur est survenue lors de la consultation du statut de la connexion à la base de données.");
        }

        return this.connection;
    }

    /**
     * Retourne le nom de la table en y ajoutant éventuel préfixe s'il existe
     * @param table le nom de la table sans préfix
     * @return le nom de la table avec préfix
     */
    public String getTableName(String table) {
        return this.tablePrefix + table;
    }

    /**
     * Definit le préfixe de table actuel.
     * @param tablePrefix le préfixe de table actuel
     */
    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }
}
