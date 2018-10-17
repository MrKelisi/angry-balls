package dp.angryballs;

import dp.angryballs.modele.Forme;
import dp.angryballs.modele.VisiteurForme;
import mesmaths.geometrie.base.Vecteur;

import java.util.List;

public abstract class Acceleration {
    protected Forme forme;

    public Acceleration(Forme forme) {
        if(forme == null) {
            throw new NullPointerException("Forme null");
        }

        this.forme = forme;
    }

    public abstract Vecteur tester(List<Forme> formes);
}
