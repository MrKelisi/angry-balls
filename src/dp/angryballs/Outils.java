package dp.angryballs;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class Outils {
    public static <Parent> Collection<Class<? extends Parent>> getClasses(String packageName, Class<Parent> parentClass) throws Exception {
        ArrayList<Class<? extends Parent>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if(classLoader == null) {
            return classes;
        }

        Enumeration resources = classLoader.getResources(packageName.replace(".", "/"));

        List dirs = new ArrayList();

        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            File directory = new File(resource.getFile());
            for(File f : directory.listFiles()) {
                String name = f.getName();
                if(!name.endsWith(".class")) {
                    continue;
                }

                Class<?> c = Class.forName(packageName + "." + name.substring(0, name.length() - 6));

                if(parentClass.isAssignableFrom(c)) {
                    classes.add((Class<? extends Parent>) c);
                }
            }
        }
        return classes;
    }
}
