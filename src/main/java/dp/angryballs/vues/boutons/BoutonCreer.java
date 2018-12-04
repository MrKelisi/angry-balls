package dp.angryballs.vues.boutons;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.BilleNue;
import dp.angryballs.vues.Billard;
import dp.angryballs.vues.BoutonComportement;
import dp.angryballs.vues.PanneauAjoutBilles;
import mesmaths.geometrie.base.Vecteur;

public class BoutonCreer extends Bouton {
    private Billard billard;
    private PanneauAjoutBilles panneau;

    public BoutonCreer(String label, PanneauAjoutBilles panneau, Billard billard) {
        super(label);
        this.billard = billard;
        this.panneau = panneau;
    }

    @Override
    protected void onClick() {
        for(ObserverBouton ob : observeurs) {
            ob.buttonPressed(this, genererBille());
        }
    }

    private Bille genererBille() {
        Vecteur position;
        Vecteur vitesse;
        if(billard == null) {
            position = new Vecteur(panneau.getRayon(),panneau.getRayon());
            vitesse = new Vecteur(0,0);
        }
        else {
            position = Vecteur.créationAléatoire(panneau.getRayon(), panneau.getRayon(), billard.getWidth() - panneau.getRayon(), billard.getHeight() - panneau.getRayon());
            vitesse = Vecteur.créationAléatoire(-0.1, -0.1, 0.1, 0.1);
        }

        Bille bille = new BilleNue(position, panneau.getRayon(), vitesse, String.valueOf(panneau.getColor().getRGB()));

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
