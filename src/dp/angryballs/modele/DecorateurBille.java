package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.Vector;

public abstract class DecorateurBille implements Bille {

    protected Bille billeDecoree;

    public DecorateurBille(Bille billeDecoree) {
        this.billeDecoree = billeDecoree;
    }

    @Override
    public Vecteur getPosition() {
        return billeDecoree.getPosition();
    }

    @Override
    public Vecteur getVitesse() {
        return billeDecoree.getVitesse();
    }

    @Override
    public Vecteur getAcceleration() {
        return billeDecoree.getAcceleration();
    }

    @Override
    public double getRayon() {
        return billeDecoree.getRayon();
    }

    @Override
    public int getClef() {
        return billeDecoree.getClef();
    }

    @Override
    public double masse() {
        return billeDecoree.masse();
    }

    @Override
    public void setRayon(double rayon) {
        billeDecoree.setRayon(rayon);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        billeDecoree.collisionContour(abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);
    }

    @Override
    public void gestionAcceleration(Vector<Forme> billes) {
        billeDecoree.gestionAcceleration(billes);
    }

    @Override
    public void deplacer(double deltaT) {
        billeDecoree.deplacer(deltaT);
    }

    @Override
    public void gestionCollisionBille(Vector<Forme> billes) {
        billeDecoree.gestionCollisionBille(billes);
    }

    @Override
    public String toString() {
        return billeDecoree.toString();
    }

    @Override
    public Color getColor() {
        return billeDecoree.getColor();
    }

    @Override
    public void visite(VisiteurForme v) {
        billeDecoree.visite(v);
    }
}
