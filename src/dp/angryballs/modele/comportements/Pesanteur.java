package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.modele.Forme;
import mesmaths.geometrie.base.Vecteur;

import java.util.List;

public class Pesanteur extends DecorateurBille {

    private Vecteur pesanteur;

    public Pesanteur(Bille billeDecoree) {
        this(billeDecoree, new Vecteur(0, 0.001));
    }

    public Pesanteur(Bille billeDecoree, Vecteur pesanteur) {
        super(billeDecoree);
        this.pesanteur = pesanteur;
    }

    @Override
    public void gestionAcceleration(List<Forme> formes) {
        billeDecoree.gestionAcceleration(formes);
        getAcceleration().ajoute(pesanteur);
    }
}
