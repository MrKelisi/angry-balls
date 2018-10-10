package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.modele.OutilsBille;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class Frottements extends DecorateurBille {

    public Frottements(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
        billeDecoree.gestionAcceleration(billes);
        getAcceleration().ajoute(MecaniquePoint.freinageFrottement(masse(), getVitesse()));
    }
}
