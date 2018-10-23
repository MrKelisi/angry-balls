package dp.angryballs.vues.controles;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.BilleNue;
import dp.angryballs.vues.BoutonComportement;
import dp.angryballs.vues.PanneauAjoutBilles;
import mesmaths.geometrie.base.Vecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonControleCreer extends BoutonControle {

    public BoutonControleCreer(String nom, PanneauAjoutBilles panel) {
        super(nom);

        if(panel == null) {
            throw new NullPointerException("panel null");
        }

        bouton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Vecteur p = Vecteur.créationAléatoire(0, 0, 100, 100); //TODO changer les valeurs
                Vecteur v = Vecteur.créationAléatoire(-0.1, -0.1, 0.1, 0.1); //TODO changer les valeurs
                Bille bille = new BilleNue(p, panel.getRayon(), v, panel.getColor());

                for(BoutonComportement bc : panel.getDecorators()) {
                    try {
                        if(bc.getCheckbox().isSelected()) {
                            bille = bc.getDecorateur().getConstructor(Bille.class).newInstance(bille);
                        }
                    }
                    catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }

                setChanged();
                notifyObservers(bille);
            }
        });
    }
}
