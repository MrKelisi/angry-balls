package dp.angryballs.vues.boutons;

import java.awt.*;
import java.util.ArrayList;

public abstract class Bouton extends Button implements ObservableBouton {

    protected ArrayList<ObserverBouton> observeurs = new ArrayList<>();

    protected Bouton(String label) {
        setLabel(label);
        this.addActionListener((actionEvent) -> onClick());
    }

    protected void onClick() {
        for(ObserverBouton ob : observeurs) {
            ob.buttonPressed(this, null);
        }
    }

    @Override
    public void ajoutObserveur(ObserverBouton observeur) {
        observeurs.add(observeur);
    }
}
