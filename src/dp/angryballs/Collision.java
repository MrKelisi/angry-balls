package dp.angryballs;

import dp.angryballs.modele.Forme;
import dp.angryballs.modele.VisiteurForme;

import java.util.List;

public abstract class Collision implements VisiteurForme {
    protected Forme forme;

    public Collision(Forme forme) {
        if(forme == null) {
            throw new NullPointerException("Forme null");
        }

        this.forme = forme;
    }

    public void tester(List<Forme> formes) {
        for(Forme forme : formes) {
            if(this.forme.getClef() == forme.getClef()) {
                continue;
            }

            forme.visite(this);
        }
    }
}
