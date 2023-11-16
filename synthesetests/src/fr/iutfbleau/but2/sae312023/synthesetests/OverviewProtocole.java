package fr.iutfbleau.but2.sae312023.synthesetests;

import fr.iutfbleau.but2.sae312023.common.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * La classe <code>OverviewProtocole</code> est un contrôleur affichant une vue d'ensemble des résultats d'un protocole.
 * @version 1.0
 * @author Lyanis Souidi
 * @author Hugo Dimitrijevic
 * @author Aissame Bai
 */
public class OverviewProtocole implements ProgramController, ActionListener {
    /**
     * La fenêtre principale.
     */
    private JFrame frame;

    /**
     * La vue d'ensemble des résultats.
     */
    private OverviewProtocoleView view;

    /**
     * Le protocole à utiliser.
     */
    private Protocole protocole;

    /**
     * Le nombre total de visites des éléments du menu.
     */
    private int nombreVisitesTotal;

    /**
     * Démarre le contrôleur.
     * @param frame la fenêtre principale.
     * @param protocole le protocole à utiliser.
     */
    public static void start(JFrame frame, Protocole protocole) {
        new OverviewProtocole(frame, protocole);
    }

    /**
     * Constructeur du contrôleur.
     * @param frame la fenêtre principale.
     * @param protocole le protocole à utiliser.
     */
    private OverviewProtocole(JFrame frame, Protocole protocole) {
        this.frame = frame;
        this.protocole = protocole;
        this.frame.setTitle("Analyses des données du protocole " + this.protocole.getReference());
        this.frame.setSize(1200, 800);
        this.frame.setLayout(new FlowLayout());

        for (Element e : this.protocole.getElements().values()) {
            this.nombreVisitesTotal += e.getNombreVisite();
        }

        List<Graph> graphs = new ArrayList<>();
        graphs.add(this.generateGraphActionsChoisies());
        graphs.add(this.generateGraphNbMenusOuverts());

        this.view = new OverviewProtocoleView(this.protocole, graphs);
        this.view.addActionListener(this);
        this.frame.setContentPane(this.view);
    }

    /**
     * Convertit une teinte HSL en une composante RGB.
     * @param p Premier paramètre intermédiaire pour la conversion, dérivé de la luminosité.
     * @param q Deuxième paramètre intermédiaire pour la conversion, dérivé de la luminosité et de la saturation.
     * @param t Teinte normalisée et ajustée pour la conversion.
     * @return La valeur de la composante RGB correspondant à la teinte HSL donnée.
     */
    private float hueToRgb(float p, float q, float t) {
        if (t < 0) t += 1;
        if (t > 1) t -= 1;
        if (t < 1 / 6f) return p + (q - p) * 6 * t;
        if (t < 1 / 2f) return q;
        if (t < 2 / 3f) return p + (q - p) * (2 / 3f - t) * 6;
        return p;
    }

    /**
     * Convertit une couleur HSL en RGB.
     * @param hue l'angle de la couleur.
     * @param saturation la saturation de la couleur.
     * @param lightness la luminosité de la couleur.
     * @return la couleur RGB.
     */
    private Color hslToRgb(float hue, float saturation, float lightness) {
        float r, g, b;

        if (saturation == 0) {
            r = g = b = lightness;
        } else {
            float q = lightness < 0.5 ? lightness * (1 + saturation) : lightness + saturation - lightness * saturation;
            float p = 2 * lightness - q;
            r = hueToRgb(p, q, hue + 1 / 3f);
            g = hueToRgb(p, q, hue);
            b = hueToRgb(p, q, hue - 1 / 3f);
        }
        return new Color(r, g, b);
    }

    /**
     * Génère une couleur aléatoire qui n'est pas verte.
     * @return la couleur aléatoire.
     */
    private Color RandomColor() {
        Random rand = new Random();
        float hue = rand.nextFloat();

        // Retrait de la couleur verte (entre 0.25 et 0.40 sur le cercle HSL)
        while (hue > 0.25 && hue < 0.40) {
            hue = rand.nextFloat();
        }
        return hslToRgb(hue, 0.5f, 0.6f);
    }

    /**
     * Retourne le pourcentage de visite d'un élément.
     * @param element l'élément.
     * @return le pourcentage de visite de l'élément.
     */
    private double getPourcentage(Element element) {
        if (this.nombreVisitesTotal == 0 || element.getNombreVisite() == 0) return 0;

        return (double) element.getNombreVisite() / this.nombreVisitesTotal * 100;
    }

    /**
     * Génère le graphique représentant la répartition des actions choisies.
     * @return le graphique représentant la répartition des actions choisies.
     */
    private Graph generateGraphActionsChoisies() {
        List<GraphPieChartSlice> slices = new ArrayList<>();

        int arcStartAngle = 0;

        for (Element element : this.protocole.getElements().values()){
            double pourcentage = this.getPourcentage(element);
            if (pourcentage == 0) continue;

            int arcAngle = (int) Math.round(pourcentage * 360.0 / 100.0);

            GraphPieChartSlice slice = new GraphPieChartSlice(element.getPath());
            slice.setValue(element.getNombreVisite());
            slice.setPourcentage(this.getPourcentage(element));
            slice.setArcAngle(arcAngle);
            slice.setArcStartAngle(arcStartAngle);
            slice.setColor(this.protocole.getActionAttendue().equals(element) ? Color.GREEN : RandomColor());
            slice.showValue(true);
            slice.showPourcentage(true);

            slices.add(slice);
            arcStartAngle += arcAngle;
        }

        GraphPieChart chart = new GraphPieChart(slices);
        GraphLegend legend = new GraphLegend(slices);

        return new Graph("Répartition des actions choisies", chart, legend);
    }

    /**
     * Génère le graphique représentant la répartition du nombre de sous-menus déployés.
     * @return le graphique représentant la répartition du nombre de sous-menus déployés.
     */
    private Graph generateGraphNbMenusOuverts() {
        List<GraphPieChartSlice> slices = new ArrayList<>();
        Map<Integer, Integer> nbMenusOuverts = new HashMap<>();

        int nbTotalElement = 0;

        for (Resultat resultat : this.protocole.getResultats().values()) {
            int nbVisites = resultat.getElementsVisites().size();
            int nbElements = Optional
                        .ofNullable(nbMenusOuverts.get(nbVisites))
                        .orElse(0) + 1;

            nbMenusOuverts.put(nbVisites, nbElements);
        }

        for (Integer nbOuvertures : nbMenusOuverts.keySet()) {
            if (nbOuvertures == 0) continue;
            nbTotalElement += nbMenusOuverts.get(nbOuvertures);
        }

        int arcStartAngle = 0;

        for (Integer nbOuvertures : nbMenusOuverts.keySet()){
            if (nbOuvertures == 0) continue;

            double pourcentage = (double) nbMenusOuverts.get(nbOuvertures) / nbTotalElement * 100;
            int arcAngle = (int) Math.round(pourcentage * 360.0 / 100.0);

            GraphPieChartSlice slice = new GraphPieChartSlice(nbOuvertures + "");
            slice.setValue(nbMenusOuverts.get(nbOuvertures));
            slice.setPourcentage(pourcentage);
            slice.setArcAngle(arcAngle);
            slice.setArcStartAngle(arcStartAngle);
            slice.setColor(RandomColor());
            slice.showPourcentage(true);

            slices.add(slice);
            arcStartAngle += arcAngle;
        }

        GraphPieChart chart = new GraphPieChart(slices);
        GraphLegend legend = new GraphLegend(slices);

        return new Graph("Répartition du nombre de sous-menus déployés", chart, legend);
    }

    /**
     * Gère les actions de l'utilisateur.
     * @param e l'action de l'utilisateur.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Changer de protocole":
                new ProtocoleSelector(this.frame, OverviewProtocole.class);
                break;
            case "Détails des résultats":
                new DetailedResults(this.protocole);
                break;
        }
    }
}
