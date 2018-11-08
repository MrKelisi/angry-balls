package dp.angryballs.reflexion;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ClassFinderCOR {
    private ClassFinderCOR next;

    public ClassFinderCOR(ClassFinderCOR next) {
        this.next = next;
    }

    public void setNext(ClassFinderCOR next) {
        this.next = next;
    }

    public <Parent> Collection<Class<? extends Parent>> findClasses(String packageName, Class<Parent> parentClass) throws Exception {
        if(!check()) {
            if(next != null) {
                return next.findClasses(packageName, parentClass);
            }
            return new ArrayList<>();
        }

        ArrayList<Class<? extends Parent>> classes = new ArrayList<>();
        for(String className : findClassesInternal(packageName)) {
            Class<?> c = Class.forName(className);

            if (parentClass.isAssignableFrom(c)) {
                classes.add((Class<? extends Parent>) c);
            }
        }

        return classes;
    }

    protected abstract boolean check();
    protected abstract Collection<String> findClassesInternal(String packageName) throws Exception;
}
