package dp.angryballs.vues;

import dp.angryballs.vues.boutons.Bouton;
import dp.angryballs.vues.boutons.BoutonCreer;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.reflexion.ClassFinder;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Panneau permettant de créer de nouvelles billes
 */
public class PanneauAjoutBilles extends Panel {
    private Label labelAjoutBilles;
    private ArrayList<BoutonComportement> listDecorators;
    private JSlider sliderRayon;
    private JColorChooser colorChooser;
    private Bouton boutonCreer;

    public PanneauAjoutBilles(Billard billard) {
        super();
        setBackground(new Color(0xEEEEEE));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        //-------------- Définitions des composants ---------------------------------

        labelAjoutBilles = new Label("Création de billes", Label.CENTER);
        labelAjoutBilles.setFont(new Font("Arial", Font.BOLD, 26));

        listDecorators = new ArrayList<>();
        try {
            Collection<Class<? extends DecorateurBille>> classes = ClassFinder.getInstance().findClasses("dp.angryballs.modele.comportements", DecorateurBille.class);
            for(Class<? extends DecorateurBille> c : classes)
                listDecorators.add(new BoutonComportement(c));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sliderRayon = new JSlider(15,100,30);

        colorChooser = new JColorChooser(Color.BLACK);
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for(AbstractColorChooserPanel p : panels) {
            switch (p.getDisplayName()) {
                case "TSV":
                case "TSL":
                case "RVB":
                case "CMYK":
                    colorChooser.removeChooserPanel(p);
                    break;
            }
        }
        colorChooser.setPreviewPanel(new JPanel());

        boutonCreer = new BoutonCreer("Créer", this, billard);


        //-------------- Ajout des composants au panneau ----------------------------

        add(labelAjoutBilles);

        add(new Label("Comportements :", Label.LEFT));
        for(BoutonComportement bc : listDecorators) add(bc.getCheckbox());

        add(new Label("Rayon :", Label.LEFT));
        add(sliderRayon);

        add(new Label("Couleur :", Label.LEFT));
        add(colorChooser);

        add(boutonCreer);
    }

    public Bouton getBoutonCreer() {
        return boutonCreer;
    }

    public ArrayList<BoutonComportement> getDecorators() {
        return listDecorators;
    }

    public Color getColor() {
        return colorChooser.getColor();
    }

    public double getRayon() {
        return sliderRayon.getValue();
    }
}
