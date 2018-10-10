package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public interface Bille {

    Vecteur getPosition();
    Vecteur getVitesse();
    Vecteur getAcceleration();
    int getClef();
    double getRayon();
    double masse();

    void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur);
    void gestionAcceleration(Vector<Bille> billes);
    void dessine (Graphics g);
    void deplacer(double deltaT);
    boolean gestionCollisionBilleBille(Vector<Bille> billes);
    String toString();

    void setRayon(double rayon);
}
