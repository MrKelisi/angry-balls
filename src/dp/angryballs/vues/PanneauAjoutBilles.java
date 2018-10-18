package dp.angryballs.vues;

import dp.angryballs.Outils;
import dp.angryballs.modele.DecorateurBille;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PanneauAjoutBilles extends Panel {
    private Label labelAjoutBilles;
    private ArrayList<BoutonComportement> listDecorators;
    private JSlider sliderRayon;
    private JColorChooser colorChooser;
    private Button createButton;

    public PanneauAjoutBilles() {
        super();
        setBackground(new Color(0xEEEEEE));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelAjoutBilles = new Label("Création de billes", Label.CENTER);
        listDecorators = new ArrayList<>();
        sliderRayon = new JSlider(15,100,30);
        colorChooser = new JColorChooser(Color.BLACK);
        createButton = new Button("Créer");

        labelAjoutBilles.setFont(new Font("Arial", Font.BOLD, 26));

        try {
            Collection<Class<? extends DecorateurBille>> classes = Outils.getClasses("dp.angryballs.modele.comportements", DecorateurBille.class);
            for(Class<? extends DecorateurBille> c : classes) {
                listDecorators.add(new BoutonComportement(c));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for(AbstractColorChooserPanel p : panels) {
            switch (p.getDisplayName()) {
                case "TSV":
                    colorChooser.removeChooserPanel(p);
                    break;
                case "TSL":
                    colorChooser.removeChooserPanel(p);
                    break;
                case "RVB":
                    colorChooser.removeChooserPanel(p);
                    break;
                case "CMYK":
                    colorChooser.removeChooserPanel(p);
                    break;
            }
        }
        colorChooser.setPreviewPanel(new JPanel());

        add(labelAjoutBilles);
        add(new Label("Comportements :", Label.LEFT));
        for(BoutonComportement bc : listDecorators) add(bc.getCheckbox());
        add(new Label("Rayon :", Label.LEFT));
        add(sliderRayon);
        add(new Label("Couleur :", Label.LEFT));
        add(colorChooser);
        add(createButton);
    }

    public Button getCreateButton() {
        return createButton;
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
