package dp.angryballs.modele;

public interface ObservableMouvement {
    /**
     * @param observeur Observeur à ajouter
     */
    void ajoutObserveur(ObserveurMouvement observeur);

    /**
     * @param observeur Observeur à supprimer
     */
    void supprimerObserveur(ObserveurMouvement observeur);
}
