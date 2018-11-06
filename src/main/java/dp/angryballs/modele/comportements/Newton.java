package dp.angryballs.modele.comportements;

import dp.angryballs.Acceleration;
import dp.angryballs.AccelerationNewton;
import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;

import java.util.List;

public class Newton extends DecorateurBille {

    public Newton(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(List<Bille> billes) {
        Acceleration acceleration = new AccelerationNewton(billeDecoree);
        billeDecoree.gestionAcceleration(billes);
        getAcceleration().ajoute(acceleration.tester(billes));
    }
}
