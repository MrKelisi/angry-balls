package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.Vector;

public class Pesanteur extends DecorateurBille {

    private Vecteur pesanteur;

    public Pesanteur(Bille billeDecoree, Vecteur pesanteur) {
        super(billeDecoree);
        this.pesanteur = pesanteur;
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
        billeDecoree.gestionAcceleration(billes);
        getAcceleration().ajoute(pesanteur);
    }
}
