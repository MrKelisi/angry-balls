package dp.angryballs.modele.states;

import dp.angryballs.modele.Bille;

public abstract class StateBille {
    Bille parent;

    public StateBille(Bille parent) {
        if(parent == null) {
            throw new NullPointerException("Parent null");
        }

        this.parent = parent;
    }

    public void deplacer(double deltaT) {
        parent.deplacer(deltaT);
    }
}
