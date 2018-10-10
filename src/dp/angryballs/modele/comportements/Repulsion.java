package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.modele.OutilsBille;

import java.util.Vector;

public class Repulsion extends DecorateurBille {

    public Repulsion(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
        billeDecoree.gestionAcceleration(billes);
        getAcceleration().ajoute(OutilsBille.gestionAccélérationNewton(billeDecoree, billes).opposé().produit(0.1));
    }
}
