package dp.angryballs.controleurs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.BilleNue;
import dp.angryballs.vues.BoutonComportement;
import dp.angryballs.vues.PanneauAjoutBilles;
import mesmaths.geometrie.base.Vecteur;

public class BoutonCreer extends Bouton {

    private PanneauAjoutBilles panneau;

    public BoutonCreer(String label, PanneauAjoutBilles panneau) {
        super(label);
        this.panneau = panneau;
    }

    @Override
    protected void onClick() {
        for(ObserverBouton ob : observeurs) {
            ob.buttonPressed(this, genererBille());
        }
    }

    private Bille genererBille() {
        Vecteur p = Vecteur.créationAléatoire(0, 0, 100, 100);  //TODO changer les valeurs
        Vecteur v = Vecteur.créationAléatoire(-0.1, -0.1, 0.1, 0.1);  //TODO changer les valeurs
        Bille bille = new BilleNue(p, panneau.getRayon(), v, panneau.getColor());

        for(BoutonComportement bc : panneau.getDecorators()) {
            try {
                if(bc.getCheckbox().isSelected())
                    bille = bc.getDecorateur().getConstructor(Bille.class).newInstance(bille);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return bille;
    }
}
