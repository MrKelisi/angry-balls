package dp.angryballs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.VisiteurForme;

import java.util.List;

public abstract class Collision implements VisiteurForme {
    protected Bille bille;

    public Collision(Bille bille) {
        if(bille == null) {
            throw new NullPointerException("Bille null");
        }

        this.bille = bille;
    }

    public void tester(List<Bille> billes) {
        for(Bille bille : billes) {
            if(this.bille.getClef() == bille.getClef()) {
                continue;
            }

            bille.visite(this);
        }
    }
}
