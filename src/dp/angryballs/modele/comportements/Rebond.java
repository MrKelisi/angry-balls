package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.cinematique.Collisions;

public class Rebond extends DecorateurBille {

    public Rebond(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourAvecRebond(
                getPosition(),
                getRayon(),
                getVitesse(),
                abscisseCoinHautGauche, ordonnéeCoinHautGauche,
                largeur, hauteur
        );
    }
}
