package dp.angryballs.vues;

import java.util.Vector;

import dp.angryballs.modele.Forme;


public class TestCadreAngryBallsSeul {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Vector<Forme> billes = new Vector<>();
        CadreAngryBalls cadre = new CadreAngryBalls("angry balls", "animation de billes marrantes", billes);
        cadre.montrer();
    }
}
