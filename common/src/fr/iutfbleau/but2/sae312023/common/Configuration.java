package fr.iutfbleau.but2.sae312023.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * La classe <code>Configuration</code> permet de gérer la configuration du programme.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class Configuration extends Properties {
    /**
     * Nom du fichier de configuration
     */
    private static String propertiesFile = "app.properties";

    /**
     * Liste des arguments valides
     */
    private static String[] validArguments = {"dbURL", "dbUser", "dbPasswd", "tablePrefix", "protocole"};

    /**
     * Instance active de la classe Configuration
     */
    private static Configuration instance = null;


    /**
     * Constructeur de la classe CliOptions
     * @param args les arguments de la ligne de commande
     */
    public Configuration(String[] args) {
        this.loadConfigFile();
        this.parseCliArgs(args);
    }

    /**
     * Initialiseur de la configuration
     * @param args les arguments de la ligne de commande
     */
    public static void init(String[] args) {
        instance = new Configuration(args);
    }

    /**
     * Retourne l'instance active de la classe Configuration
     * @return l'instance active de la classe Configuration
     * @throws RuntimeException si la configuration n'a pas été initialisée
     * @see Configuration#init(String[])
     */
    public static Configuration getInstance() {
        if (instance == null) throw new RuntimeException("La configuration n'a pas été initialisée.");
        return instance;
    }

    /**
     * Charge les propriétés depuis le fichier de configuration
     */
    private void loadConfigFile() {
        try {
            this.load(new FileReader(Configuration.propertiesFile));
        } catch (FileNotFoundException | NullPointerException ignored) {}
        catch (IOException e) {
            System.err.println("Le fichier de configuration " + Configuration.propertiesFile + " n'a pas pu être chargé car il est corrompu.");
        }
    }

    /**
     * Parse les arguments de la ligne de commande puis les stocke dans la configuration
     * @param args les arguments de la ligne de commande
     */
    private void parseCliArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--") && args[i].length() > 2 && Arrays.asList(Configuration.validArguments).contains(args[i].substring(2))) {
                this.setProperty(args[i].substring(2), args[i+1]);
                i++;
            } else {
                throw new IllegalArgumentException("Argument invalide : " + args[i]);
            }
        }
    }
}
