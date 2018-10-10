package dp.angryballs.modele;

import java.util.Vector;

public interface Bille extends Forme {
    double getRayon();

    void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur);
    void gestionAcceleration(Vector<Forme> billes);
    void gestionCollisionBille(Vector<Forme> billes);
    String toString();

    void setRayon(double rayon);
}
