package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.util.List;

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
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        billeDecoree.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }

    @Override
    public void gestionAcceleration(List<Bille> billes) {
        billeDecoree.gestionAcceleration(billes);
    }

    @Override
    public void deplacer(double deltaT) {
        billeDecoree.deplacer(deltaT);
    }

    @Override
    public void gestionCollision(List<Bille> billes) {
        billeDecoree.gestionCollision(billes);
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
    public void setVitesse(Vecteur vitesse) {
        billeDecoree.setVitesse(vitesse);
    }

    @Override
    public void visite(VisiteurForme v) {
        billeDecoree.visite(v);
    }

    @Override
    public void prendre(ObservableMouvement observableMouvement) {
        billeDecoree.prendre(observableMouvement);
    }

    @Override
    public void relacher() {
        billeDecoree.relacher();
    }

    @Override
    public void setPosition(Vecteur position) {
        billeDecoree.setPosition(position);
    }

    @Override
    public Color getOutline() {
        return billeDecoree.getOutline();
    }

    @Override
    public void setOutline(Color color) {
        billeDecoree.setOutline(color);
    }
}
