package dp.angryballs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.BilleNue;
import dp.angryballs.vues.BoutonComportement;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EcouteurCreationBille implements ActionListener {
    private AnimationBilles animationBilles;
    private ArrayList<BoutonComportement> listeDecorateurs;
    private double rayon;

    public EcouteurCreationBille(AnimationBilles animationBilles, ArrayList<BoutonComportement> listeDecorateurs, double rayon) {
        if(animationBilles == null) {
            throw new NullPointerException("animationBilles null");
        }
        if(listeDecorateurs == null) {
            throw new NullPointerException("listeDecorateurs null");
        }

        this.animationBilles = animationBilles;
        this.listeDecorateurs = listeDecorateurs;
        this.rayon = rayon;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Vecteur p = Vecteur.créationAléatoire(0, 0, 100, 100); //TODO changer les valeurs
        Vecteur v = Vecteur.créationAléatoire(-0.1, -0.1, 0.1, 0.1); //TODO changer les valeurs
        Bille bille = new BilleNue(p, rayon, v, Color.BLACK); //TODO: changer les valeurs

        for(BoutonComportement bc : listeDecorateurs) {
            try {
                if(bc.getCheckbox().isSelected()) {
                    bille = bc.getDecorateur().getConstructor(Bille.class).newInstance(bille);
                }
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        animationBilles.ajouterForme(bille);
    }
}
