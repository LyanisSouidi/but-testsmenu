package fr.iutfbleau.but2.sae312023.common;

/**
 * L'interface <code>DatabaseConnectListener</code> est utilisée pour informer du succès de la connexion à la base de données.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public interface DatabaseConnectListener {
    /**
     * Méthode appelée lorsque la connexion à la base de données a réussi.
     */
    void onConnect();
}
