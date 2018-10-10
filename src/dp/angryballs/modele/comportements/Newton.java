package dp.angryballs.modele.comportements;

import dp.angryballs.Acceleration;
import dp.angryballs.AccelerationNewton;
import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.modele.Forme;
import dp.angryballs.modele.OutilsBille;

import java.util.Vector;

public class Newton extends DecorateurBille {

    public Newton(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(Vector<Forme> formes) {
        Acceleration acceleration = new AccelerationNewton(billeDecoree);
        billeDecoree.gestionAcceleration(formes);
        getAcceleration().ajoute(acceleration.tester(formes));
    }
}
