package dp.angryballs.modele.comportements;

import dp.angryballs.Acceleration;
import dp.angryballs.AccelerationNewton;
import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.modele.Forme;
import dp.angryballs.modele.OutilsBille;

import java.util.List;

public class Repulsion extends DecorateurBille {

    public Repulsion(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(List<Forme> formes) {
        Acceleration acceleration = new AccelerationNewton(billeDecoree);
        billeDecoree.gestionAcceleration(formes);
        getAcceleration().ajoute(acceleration.tester(formes).opposé().produit(0.1));
    }
}
