package dp.angryballs.vues;

import dp.angryballs.modele.DecorateurBille;

import java.util.ArrayList;

public interface ListeDecorateurSelectionnes {
    void ajoutDecorateur(Class<? extends DecorateurBille> decorateur);
    ArrayList<Class<? extends DecorateurBille>> getDecorateurs();
}
