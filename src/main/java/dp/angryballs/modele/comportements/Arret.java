package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.cinematique.Collisions;

/**
 * S'arrête lors de collisions
 */
public class Arret extends DecorateurBille {

    public Arret(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourAvecArretHorizontal(
                this.getPosition(),
                this.getRayon(),
                this.getVitesse(),
                abscisseCoinHautGauche,
                largeur
        );
        Collisions.collisionBilleContourAvecArretVertical(
                this.getPosition(),
                this.getRayon(),
                this.getVitesse(),
                ordonneeCoinHautGauche,
                hauteur
        );
    }
}
