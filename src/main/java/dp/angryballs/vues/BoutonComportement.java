package dp.angryballs.vues;

import dp.angryballs.modele.DecorateurBille;

import javax.swing.*;

/**
 * Bouton de choix d'un comportement
 */
public class BoutonComportement {

    private Class<? extends DecorateurBille> decorateur;
    private JCheckBox checkbox;

    public BoutonComportement(Class<? extends DecorateurBille> decorateur) {
        this.decorateur = decorateur;
        this.checkbox = new JCheckBox(getNom());
    }

    public String getNom() {
        return decorateur.getSimpleName();
    }
    public Class<? extends DecorateurBille> getDecorateur() {
        return decorateur;
    }
    public JCheckBox getCheckbox() {
        return checkbox;
    }
}
