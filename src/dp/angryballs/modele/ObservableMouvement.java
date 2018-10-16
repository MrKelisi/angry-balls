package dp.angryballs.modele;

public interface ObservableMouvement {
    void ajoutObserveur(ObserveurMouvement observeur);
    void supprimerObserveur(ObserveurMouvement observeur);
}
