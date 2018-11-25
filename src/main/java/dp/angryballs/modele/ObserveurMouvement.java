package dp.angryballs.modele;

import mesmaths.geometrie.base.Vecteur;

public interface ObserveurMouvement {
    /**
     * Méthode appelée lorsqu'un mouvement a eu lieu
     * @param offset Quantité de mouvement
     */
    void onMove(Vecteur offset);
}
