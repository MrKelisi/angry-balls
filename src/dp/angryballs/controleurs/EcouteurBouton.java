package dp.angryballs.controleurs;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class EcouteurBouton implements ActionListener, ObservableBouton {

    protected ArrayList<ObserverBouton> observeurs = new ArrayList<>();

    @Override
    public void ajoutObserveur(ObserverBouton observeur) {
        observeurs.add(observeur);
    }
}
