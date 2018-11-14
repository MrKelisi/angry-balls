package dp.angryballs.reflexion;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class ClassFinderFolder extends ClassFinderCOR {
    public ClassFinderFolder(ClassFinderCOR next) {
        super(next);
    }

    @Override
    protected boolean check() {
        try {
            return new File(ClassFinderFolder.class.getProtectionDomain().getCodeSource().getLocation().toURI()).isDirectory();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Collection<String> findClassesInternal(String packageName) throws Exception {
        ArrayList<String> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration resources = classLoader.getResources(packageName.replace(".", "/"));

        List dirs = new ArrayList();

        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            File directory = new File(resource.getFile());
            for (File f : directory.listFiles()) {
                String name = f.getName();
                if (!name.endsWith(".class")) {
                    continue;
                }

                classes.add(packageName + "." + name.substring(0, name.length() - 6));
            }
        }

        return classes;
    }
}
