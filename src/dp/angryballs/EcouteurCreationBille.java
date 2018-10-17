package dp.angryballs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.BilleNue;
import dp.angryballs.vues.BoutonComportement;
import dp.angryballs.vues.PanneauAjoutBilles;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EcouteurCreationBille implements ActionListener {
    private AnimationBilles animationBilles;
    private PanneauAjoutBilles panel;
    private double rayon;

    public EcouteurCreationBille(AnimationBilles animationBilles, PanneauAjoutBilles panel, double rayon) {
        if(animationBilles == null) {
            throw new NullPointerException("animationBilles null");
        }
        if(panel == null || panel.getDecorators() == null) {
            throw new NullPointerException("listeDecorateurs null");
        }

        this.animationBilles = animationBilles;
        this.panel = panel;
        this.rayon = rayon;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Vecteur p = Vecteur.créationAléatoire(0, 0, 100, 100); //TODO changer les valeurs
        Vecteur v = Vecteur.créationAléatoire(-0.1, -0.1, 0.1, 0.1); //TODO changer les valeurs
        Bille bille = new BilleNue(p, panel.getRayon(), v, panel.getColor()); //TODO: changer les valeurs

        for(BoutonComportement bc : panel.getDecorators()) {
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
