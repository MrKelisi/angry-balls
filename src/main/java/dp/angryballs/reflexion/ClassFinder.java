package dp.angryballs.reflexion;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe permettant de lister les classes présentes dans un package.
 */
public class ClassFinder {
    private static volatile ClassFinder instance;
    private ClassFinderCOR cor;

    private ClassFinder() {
        cor = null;
        add(new ClassFinderJAR(null));
        add(new ClassFinderFolder(null));
    }

    public void add(ClassFinderCOR classFinderCOR) {
        if(classFinderCOR == null) {
            throw new NullPointerException("Element null");
        }

        classFinderCOR.setNext(cor);
        cor = classFinderCOR;
    }

    /**
     * Recherche des classes
     * @param packageName Nom du package
     * @param parentClass Classe parent des classes retournées
     * @return Classes trouvées
     */
    public <Parent> Collection<Class<? extends Parent>> findClasses(String packageName, Class<Parent> parentClass) throws Exception {
        if(cor == null) {
            return new ArrayList<>();
        }

        return cor.findClasses(packageName, parentClass);
    }

    public static ClassFinder getInstance() {
        if (instance == null) {
            instance = new ClassFinder();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }
}
