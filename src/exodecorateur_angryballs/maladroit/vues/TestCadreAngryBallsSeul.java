package exodecorateur_angryballs.maladroit.vues;

import java.util.Vector;

import exodecorateur_angryballs.maladroit.modele.Bille;


public class TestCadreAngryBallsSeul {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Vector<Bille> billes = new Vector<>();
        CadreAngryBalls cadre =new CadreAngryBalls("angry balls", "animation de billes marrantes", billes);
        cadre.montrer();
    }
}
