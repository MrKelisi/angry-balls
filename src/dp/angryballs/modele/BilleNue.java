package dp.angryballs.modele;

import java.awt.*;
import java.util.Vector;

import dp.angryballs.CollisionBille;
import mesmaths.cinematique.Cinematique;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

public class BilleNue implements Bille {
    private Vecteur position;       // centre de la bille
    private Vecteur vitesse;
    private Vecteur acceleration;
    private double rayon;           // rayon > 0
    private Color couleur;
    public  static double ro = 1;   // masse volumique

    private int clef;
    private static int prochaineClef = 0;

    protected BilleNue(Vecteur centre, double rayon, Vecteur vitesse, Vecteur acceleration, Color couleur) {
        this.position     = centre;
        this.vitesse      = vitesse;
        this.acceleration = acceleration;
        this.couleur      = couleur;
        this.clef         = BilleNue.prochaineClef++;
        setRayon(rayon);
    }

    public BilleNue(Vecteur position, double rayon, Vecteur vitesse, Color couleur) {
        this(position,rayon,vitesse,new Vecteur(),couleur);
    }


    @Override
    public Vecteur getPosition() {
        return this.position;
    }

    @Override
    public Vecteur getVitesse() {
        return this.vitesse;
    }

    @Override
    public Vecteur getAcceleration() {
        return this.acceleration;
    }

    @Override
    public double getRayon() {
        return this.rayon;
    }

    @Override
    public int getClef() {
        return this.clef;
    }

    @Override
    public double masse() {
        return ro*Geop.volumeSphère(rayon);
    }

    @Override
    public void setRayon(double rayon) {
        if(rayon < 0) {
            throw new IllegalArgumentException("Rayon < 0");
        }
        this.rayon = rayon;
    }


    /**
     * mise à jour de position et vitesse à t+deltaT à partir de position et vitesse à l'instant t
     * modifie le vecteur position et le vecteur vitesse
     * laisse le vecteur acceleration intact
     * La bille subit par défaut un mouvement uniformément accéléré
     * */
    @Override
    public void deplacer(double deltaT) {
        Cinematique.mouvementUniformémentAccéléré( this.getPosition(), this.getVitesse(), this.getAcceleration(), deltaT);
    }


    /**
     * calcul (c-à-d mise à jour) éventuel  du vecteur acceleration.
     * billes est la liste de toutes les billes en mouvement
     * Cette méthode peut avoir besoin de "billes" si this subit l'attraction gravitationnelle des autres billes
     * La nature du calcul du vecteur acceleration de la bille  est définie dans les classes dérivées
     * A ce niveau le vecteur acceleration est mis à zéro (c'est à dire pas d'acceleration)
     * */
    @Override
    public void gestionAcceleration(Vector<Forme> billes) {
        this.getAcceleration().set(Vecteur.VECTEURNUL);
    }


    /**
     * gestion de l'éventuelle  collision de cette bille avec les autres billes
     * billes est la liste de toutes les billes en mouvement
     * Le comportement par défaut est le choc parfaitement élastique (c-à-d rebond sans amortissement)
     *
     * @return true si il y a collision et dans ce cas les positions et vecteurs vitesses des 2 billes impliquées dans le choc sont modifiées
     * si renvoie false, il n'y a pas de collision et les billes sont laissées intactes
     * */
    @Override
    public void gestionCollision(Vector<Forme> formes) {
        CollisionBille collisionBille = new CollisionBille(this);
        collisionBille.tester(formes);
    }


    /**
     * gestion de l'éventuelle collision de la bille (this) avec le contour rectangulaire de l'écran défini par (abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur)
     * détecte si il y a collision et le cas échéant met à jour position et vitesse
     * La nature du comportement de la bille en réponse à cette collision est définie dans les classes dérivées
     * PAR DÉFAUT, la bille passe à travers les murs et se téléporte (PasseMurailles)
     * */
    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourPasseMuraille(
                this.getPosition(),
                abscisseCoinHautGauche, ordonneeCoinHautGauche,
                largeur, hauteur
        );
    }

    @Override
    public String toString() {
        return "\n{\n\tclef = " + clef + "\n\tcentre = " + position + " \n\trayon = "+rayon +  " \n\tvitesse = " + vitesse + " \n\tacceleration = " + acceleration + " \n\tcouleur = " + couleur + "\n}\n";
    }

    @Override
    public void visite(VisiteurForme v) {
        v.visite(this);
    }

    @Override
    public Color getColor() {
        return couleur;
    }

    @Override
    public void setVitesse(Vecteur vitesse) {
        this.vitesse = vitesse;
    }
}

