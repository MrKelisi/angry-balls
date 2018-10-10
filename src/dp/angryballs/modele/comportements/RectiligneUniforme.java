package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class RectiligneUniforme extends DecorateurBille {

    public RectiligneUniforme(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
        getAcceleration().set(Vecteur.VECTEURNUL);
    }
}
