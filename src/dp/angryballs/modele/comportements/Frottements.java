package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.modele.Forme;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class Frottements extends DecorateurBille {

    public Frottements(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(Vector<Forme> formes) {
        billeDecoree.gestionAcceleration(formes);
        getAcceleration().ajoute(MecaniquePoint.freinageFrottement(masse(), getVitesse()));
    }
}
