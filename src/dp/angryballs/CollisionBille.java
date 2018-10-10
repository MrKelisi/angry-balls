package dp.angryballs;

import dp.angryballs.modele.Bille;
import dp.angryballs.modele.OutilsBille;

public class CollisionBille extends Collision {
    public CollisionBille(Bille bille) {
        super(bille);
    }

    @Override
    public void visite(Bille other) {
        OutilsBille.gestionCollisionBilleBille((Bille) forme, other);
    }
}
