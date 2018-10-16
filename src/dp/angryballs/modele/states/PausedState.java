package dp.angryballs.modele.states;

import dp.angryballs.modele.Bille;

public class PausedState extends StateBille {
    public PausedState(Bille parent) {
        super(parent);
    }

    @Override
    public void deplacer(double deltaT) {

    }
}
