package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.geometrie.base.Vecteur;

import java.util.List;

public class Pesanteur extends DecorateurBille {

    private static Vecteur DEFAULT_GRAVITY = new Vecteur(0, 0.001);
    private Vecteur pesanteur;

    public Pesanteur(Bille billeDecoree) {
        this(billeDecoree, DEFAULT_GRAVITY);
    }

    public Pesanteur(Bille billeDecoree, Vecteur pesanteur) {
        super(billeDecoree);
        this.pesanteur = pesanteur;
    }

    @Override
    public void gestionAcceleration(List<Bille> billes) {
        billeDecoree.gestionAcceleration(billes);
        getAcceleration().ajoute(pesanteur);
    }
}
