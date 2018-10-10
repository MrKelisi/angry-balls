package dp.angryballs.vues;

import dp.angryballs.Outils;
import dp.angryballs.modele.DecorateurBille;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class PanneauAjoutBilles extends Panel {
    private Label labelAjoutBilles;
    private DecorateurComboBox availableDecorators;
    private DecorateurJList selectedDecorators;
    private Button addButton;
    private Button createButton;

    public PanneauAjoutBilles() {
        super();
        setBackground(Color.lightGray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelAjoutBilles = new Label("Création de billes");
        availableDecorators = new DecorateurComboBox();
        selectedDecorators = new DecorateurJList();
        createButton = new Button("Créer");
        addButton = new Button("Ajouter le décorateur");
        addButton.addActionListener(new EcouteurBoutonAjouterDecorateur(availableDecorators, selectedDecorators));

        try {
            Collection<Class<? extends DecorateurBille>> classes = Outils.getClasses("dp.angryballs.modele.comportements", DecorateurBille.class);
            for(Class<? extends DecorateurBille> c : classes) {
                availableDecorators.ajouterDecorateur(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        add(labelAjoutBilles);
        add(selectedDecorators);
        add(availableDecorators);
        add(addButton);
        add(createButton);
    }

    public Button getCreateButton() {
        return createButton;
    }

    public DecorateurJList getSelectedDecorators() {
        return selectedDecorators;
    }
}
