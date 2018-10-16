package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

public interface ObserveurMouvement {
    void onMove(Vecteur offset);
}
