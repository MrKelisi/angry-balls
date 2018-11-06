package dp.angryballs.modele;

import dp.angryballs.modele.states.DefaultState;
import dp.angryballs.modele.states.StateBille;

public class DecorateurState extends DecorateurBille {
    protected StateBille state;

    public DecorateurState(Bille billeDecoree) {
        super(billeDecoree);
        state = new DefaultState(billeDecoree);
    }
}
