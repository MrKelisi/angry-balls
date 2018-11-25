package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.cinematique.Collisions;

/**
 * La bille peut traverser les murs
 */
public class PasseMurailles extends DecorateurBille {

    public PasseMurailles(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourPasseMuraille(
                this.getPosition(),
                abscisseCoinHautGauche, ordonneeCoinHautGauche,
                largeur, hauteur
        );
    }
}
