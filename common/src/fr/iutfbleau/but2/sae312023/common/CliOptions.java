package fr.iutfbleau.but2.sae312023.common;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe <codde>CliOptions</codde> permette de traiter les options passées en ligne de commande
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class CliOptions {
    /**
     * Map contenant les options
     */
    private Map<String, String> options = new HashMap<>();

    /**
     * Constructeur de la classe CliOptions
     * @param args les arguments de la ligne de commande
     */
    public CliOptions(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--") && args[i].length() > 2) {
                this.options.put(args[i].substring(2), args[i+1]);
                i++;
            } else {
                throw new IllegalArgumentException("Argument invalide : " + args[i]);
            }
        }
    }

    /**
     * Retourne la valeur d'une option
     * @return la valeur de l'option
     */
    public String getOption(String option) {
          return options.get(option);
    }
}
