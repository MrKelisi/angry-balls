package dp.angryballs.modele.states;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.ObserveurMouvement;
import mesmaths.geometrie.base.Vecteur;

/**
 * État d'une bille lorsqu'elle est attrapée
 */
public class GrabbedState extends StateBille implements ObserveurMouvement {
    private Long temps;

    public GrabbedState(Bille parent) {
        super(parent);
    }

    @Override
    public void onMove(Vecteur offset) {
        if(temps != null) {
            double diff = System.currentTimeMillis() - temps;
            if(diff <= 0) {
                return;
            }
            Vecteur vitesse = parent.getVitesse().somme(offset.produit(10000.0/(diff*parent.masse())));
            parent.setVitesse(vitesse);
        }

        temps = System.currentTimeMillis();
    }
}
