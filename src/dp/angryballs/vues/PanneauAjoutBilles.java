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
    private ArrayList<BoutonComportement> listDecorators = new ArrayList<>();
    private JColorChooser colorChooser = new JColorChooser(Color.BLACK);
    private JSlider sliderRayon = new JSlider(15,100,30);
    private Button createButton;

    public PanneauAjoutBilles() {
        super();
        setBackground(Color.lightGray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelAjoutBilles = new Label("Création de billes");
        createButton = new Button("Créer");

        AbstractColorChooserPanel panels[] = { };
        //colorChooser.setChooserPanels(panels);
        colorChooser.setPreviewPanel(new JPanel());

        try {
            Collection<Class<? extends DecorateurBille>> classes = Outils.getClasses("dp.angryballs.modele.comportements", DecorateurBille.class);
            for(Class<? extends DecorateurBille> c : classes) {
                listDecorators.add(new BoutonComportement(c));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        add(labelAjoutBilles);
        for(BoutonComportement bc : listDecorators) {
            add(bc.getCheckbox());
        }
        add(sliderRayon);
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
