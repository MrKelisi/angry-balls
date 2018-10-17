package dp.angryballs.modele.states;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.ObserveurMouvement;
import mesmaths.geometrie.base.Vecteur;

public class GrabbedState extends PausedState implements ObserveurMouvement {
    private Long temps;

    public GrabbedState(Bille parent) {
        super(parent);
    }

    @Override
    public void onMove(Vecteur offset) {
        if(temps != null) {
            double diff = System.currentTimeMillis() - temps;
            parent.setVitesse(offset.produit(1.0/diff));
        }

        parent.setPosition(parent.getPosition().somme(offset));
        temps = System.currentTimeMillis();
    }
}
