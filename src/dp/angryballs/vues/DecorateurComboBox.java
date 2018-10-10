package dp.angryballs.vues;

import dp.angryballs.modele.DecorateurBille;

import javax.swing.*;
import java.util.HashMap;

public class DecorateurComboBox extends JComboBox<String> implements SelectionDecorateur {
    private HashMap<String, Class<? extends DecorateurBille>> decorateurs;

    public DecorateurComboBox() {
        super();
        decorateurs = new HashMap<>();
    }

    @Override
    public void ajouterDecorateur(Class<? extends DecorateurBille> decorateur) {
        if(decorateur == null) {
            throw new NullPointerException("Decorateur null");
        }

        if(decorateurs.containsKey(decorateur.getSimpleName())) {
            throw new IllegalArgumentException("Decorateur " + decorateur.getSimpleName() + " déjà existant");
        }

        decorateurs.put(decorateur.getSimpleName(), decorateur);
        addItem(decorateur.getSimpleName());
    }

    @Override
    public Class<? extends DecorateurBille> getDecorateur() {
        return decorateurs.get((String) getSelectedItem());
    }
}
