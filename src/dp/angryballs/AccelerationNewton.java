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
        int d = formes.size() - 1;

        double masses[] = new double[d];   // les masses des autres billes
        Vecteur C[] = new Vecteur[d];      // les positions des autres billes

        for (int i = 0; i < d; ++i) {
            Forme other = formes.get(i);

            if(forme.getClef() == other.getClef()) {
                formes.remove(i);
                i--;
                continue;
            }

            masses[i] = other.masse();
            C[i] = other.getPosition();
        }

        //------------------ à présent on calcule le champ de gravité exercé par les autres billes sur cette bille ------------------

        return  MecaniquePoint.champGravitéGlobal(this.forme.getPosition(), masses, C);
    }
}
