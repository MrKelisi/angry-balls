package dp.angryballs.vues;

import dp.angryballs.modele.DecorateurBille;

public interface SelectionDecorateur {
    void ajouterDecorateur(Class<? extends DecorateurBille> decorateur);
    Class<? extends DecorateurBille> getDecorateur();
}
