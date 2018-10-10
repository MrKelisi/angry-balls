package dp.angryballs.modele.comportements;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.DecorateurBille;
import mesmaths.cinematique.Collisions;

public class PasseMurailles extends DecorateurBille {

    public PasseMurailles(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourPasseMuraille(
                this.getPosition(),
                abscisseCoinHautGauche, ordonnéeCoinHautGauche,
                largeur, hauteur
        );
    }
}
