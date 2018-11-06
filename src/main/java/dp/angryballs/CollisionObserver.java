package dp.angryballs;

import dp.angryballs.modele.Bille;

public interface CollisionObserver {
    void collides(Bille b1, Bille b2);
}
