package dp.angryballs;

import dp.angryballs.modele.Bille;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.util.List;

public class AccelerationNewton extends Acceleration {
    public AccelerationNewton(Bille bille) {
        super(bille);
    }

    @Override
    public Vecteur tester(List<Bille> billes) {
        int i = 0;
        int d = billes.size() - 1;

        double masses[] = new double[d];   // les masses des autres billes
        Vecteur C[] = new Vecteur[d];      // les positions des autres billes

        for (Bille other : billes) {
            if(bille.getClef() == other.getClef()) {
                continue;
            }

            masses[i] = other.masse();
            C[i] = other.getPosition();
            i++;
        }

        //------------------ à présent on calcule le champ de gravité exercé par les autres billes sur cette bille ------------------

        return  MecaniquePoint.champGravitéGlobal(this.bille.getPosition(), masses, C);
    }
}
