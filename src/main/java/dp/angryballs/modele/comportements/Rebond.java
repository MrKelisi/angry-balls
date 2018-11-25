package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.cinematique.Collisions;

/**
 * La bille rebondit sur les bords
 */
public class Rebond extends DecorateurBille {

    public Rebond(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourAvecRebond(
                getPosition(),
                getRayon(),
                getVitesse(),
                abscisseCoinHautGauche, ordonneeCoinHautGauche,
                largeur, hauteur
        );
    }
}
