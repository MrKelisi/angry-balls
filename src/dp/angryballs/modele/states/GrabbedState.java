package dp.angryballs.modele.states;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.ObserveurMouvement;
import mesmaths.geometrie.base.Vecteur;

public class GrabbedState extends PausedState implements ObserveurMouvement {
    public GrabbedState(Bille parent) {
        super(parent);
    }

    @Override
    public void onMove(Vecteur offset) {
        parent.setPosition(parent.getPosition().somme(offset));
    }
}
