package dp.angryballs;

import dp.angryballs.modele.Forme;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.util.List;

public class AccelerationNewton extends Acceleration {
    public AccelerationNewton(Forme forme) {
        super(forme);
    }

    @Override
    public Vecteur tester(List<Forme> formes) {
        int i = 0;
        int d = formes.size() - 1;

        double masses[] = new double[d];   // les masses des autres billes
        Vecteur C[] = new Vecteur[d];      // les positions des autres billes

        for (Forme other : formes) {
            if(forme.getClef() == other.getClef()) {
                continue;
            }

            masses[i] = other.masse();
            C[i] = other.getPosition();
            i++;
        }

        //------------------ à présent on calcule le champ de gravité exercé par les autres billes sur cette bille ------------------

        return  MecaniquePoint.champGravitéGlobal(this.forme.getPosition(), masses, C);
    }
}
