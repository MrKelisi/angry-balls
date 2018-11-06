package dp.angryballs.modele;

import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

/**
 * 
 * 
 * Opérations utiles sur les billes
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 *  */

public class OutilsBille {
    /**
     * @param billes est la liste de TOUTES les billes en mouvement
     * @param cetteBille est l'une d'entre elles.
     * @return la liste des autres billes que cetteBille, c'est-à-dire la liste de toutes les billes sauf cetteBille
     *
     * */
    public static Vector<Bille> autresBilles(Bille cetteBille, Vector<Bille> billes) {
        Vector<Bille> autresBilles = new Vector<Bille>();

        Bille billeCourante;

        int i;

        for( i = 0; i < billes.size(); ++i) {
            billeCourante = billes.get(i);
            if (billeCourante.getClef() != cetteBille.getClef()) {
               autresBilles.add(billeCourante);
            }
        }

        return autresBilles;
    }


    /**
     * @param cetteBille : une bille particulière
     * @param other : une bille
     *
     * gestion de l'éventuelle  collision de cette bille avec les autres billes
     *
     * billes est la liste de toutes les billes en mouvement
     *
     * Le comportement par défaut est le choc parfaitement élastique (c-à-d rebond sans amortissement)
     *
     * @return true si il y a collision et dans ce cas les positions et vecteurs vitesses des 2 billes impliquées dans le choc sont modifiées
     * si renvoie false, il n'y a pas de collision et les billes sont laissées intactes
     * */
    public static boolean gestionCollisionBilleBille(Bille cetteBille, Bille other) {
        //--- on cherche à présent la 1ère des autres billes avec laquelle cetteBille est en collision ---------------------
        //-------------- on suppose qu'il ne peut y avoir de collision qui implique plus de deux billes à la fois ---------------
        return Collisions.CollisionBilleBille(
            cetteBille.getPosition(),
            cetteBille.getRayon(),
            cetteBille.getVitesse(),
            cetteBille.masse(),
            other.getPosition(),
            other.getRayon(),
            other.getVitesse(),
            other.masse()
        );
    }
}
