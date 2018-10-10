package dp.angryballs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.BilleNue;
import dp.angryballs.modele.DecorateurBille;
import dp.angryballs.vues.ListeDecorateurSelectionnes;
import mesmaths.geometrie.base.Vecteur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurCreationBille implements ActionListener {
    private AnimationBilles animationBilles;
    private ListeDecorateurSelectionnes listeDecorateurSelectionnes;
    private double rayon;

    public EcouteurCreationBille(AnimationBilles animationBilles, ListeDecorateurSelectionnes decorateurSelectionnes, double rayon) {
        if(animationBilles == null) {
            throw new NullPointerException("AnimationBilles null");
        }
        if(decorateurSelectionnes == null) {
            throw new NullPointerException("ListeDecorateurSelectionnes null");
        }

        this.animationBilles = animationBilles;
        this.listeDecorateurSelectionnes = decorateurSelectionnes;
        this.rayon = rayon;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Vecteur p = Vecteur.créationAléatoire(0, 0, 100, 100); //TODO changer les valeurs
        Vecteur v = Vecteur.créationAléatoire(-0.1, -0.1, 0.1, 0.1); //TODO changer les valeurs
        Bille bille = new BilleNue(p, rayon, v, Color.BLACK); //TODO: changer les valeurs

        for(Class<? extends DecorateurBille> decorateur : listeDecorateurSelectionnes.getDecorateurs()) {
            try {
                bille = decorateur.getConstructor(Bille.class).newInstance(bille);
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        animationBilles.ajouterForme(bille);
    }
}
