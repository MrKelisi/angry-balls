package dp.angryballs.vues;

import dp.angryballs.Outils;
import dp.angryballs.modele.DecorateurBille;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class PanneauAjoutBilles extends Panel {
    private Label labelAjoutBilles;
    private ArrayList<BoutonComportement> listDecorators = new ArrayList<>();
    private Button createButton;

    public PanneauAjoutBilles() {
        super();
        setBackground(Color.lightGray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelAjoutBilles = new Label("Création de billes");
        createButton = new Button("Créer");

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
        for(BoutonComportement bc : listDecorators) add(bc.getCheckbox());
        add(createButton);
    }

    public Button getCreateButton() {
        return createButton;
    }

    public ArrayList<BoutonComportement> getDecorators() {
        return listDecorators;
    }
}
