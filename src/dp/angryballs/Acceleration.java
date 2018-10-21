package dp.angryballs;

import dp.angryballs.modele.Bille;
import mesmaths.geometrie.base.Vecteur;

import java.util.List;

public abstract class Acceleration {
    protected Bille bille;

    public Acceleration(Bille bille) {
        if(bille == null) {
            throw new NullPointerException("Bille null");
        }

        this.bille = bille;
    }

    public abstract Vecteur tester(List<Bille> billes);
}
