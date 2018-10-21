package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.mecanique.MecaniquePoint;

import java.util.List;

public class Frottements extends DecorateurBille {

    public Frottements(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(List<Bille> billes) {
        billeDecoree.gestionAcceleration(billes);
        getAcceleration().ajoute(MecaniquePoint.freinageFrottement(masse(), getVitesse()));
    }
}
