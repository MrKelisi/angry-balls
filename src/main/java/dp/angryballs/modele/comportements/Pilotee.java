package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurState;
import dp.angryballs.modele.ObservableMouvement;
import dp.angryballs.modele.ObserveurMouvement;
import dp.angryballs.modele.states.DefaultState;
import dp.angryballs.modele.states.GrabbedState;

/**
 * L'utilisateur peut contrôler la bile à l'aide de la souris
 */
public class Pilotee extends DecorateurState {
    private ObservableMouvement current;

    public Pilotee(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void prendre(ObservableMouvement observableMouvement) {
        super.prendre(observableMouvement);

        state = new GrabbedState(billeDecoree);
        observableMouvement.ajoutObserveur((GrabbedState) state);
        current = observableMouvement;
    }

    @Override
    public void relacher() {
        super.relacher();

        if(current != null && state instanceof ObserveurMouvement) {
            current.supprimerObserveur((ObserveurMouvement) state);
        }
        current = null;

        state = new DefaultState(billeDecoree);
    }
}
