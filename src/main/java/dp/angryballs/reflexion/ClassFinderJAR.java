package dp.angryballs.reflexion;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ClassFinderJAR extends ClassFinderCOR {
    private String jarPath;

    public ClassFinderJAR(ClassFinderCOR next) {
        super(next);

        try {
            jarPath = new File(ClassFinderJAR.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        }
        catch (Exception e) {
            jarPath = "";
            e.printStackTrace();
        }
    }

    @Override
    protected boolean check() {
        try {
            return jarPath.endsWith(".jar");
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Collection<String> findClassesInternal(String packageName) throws Exception {
        ArrayList<String> classes = new ArrayList<>();
        packageName = packageName.replace(".", "/");
        JarInputStream jarFile = new JarInputStream(new FileInputStream(jarPath));
        JarEntry jarEntry;

        while((jarEntry = jarFile.getNextJarEntry()) != null) {
            if ((jarEntry.getName().startsWith(packageName)) && (jarEntry.getName().endsWith(".class"))) {
                String name = jarEntry.getName().replaceAll("/", ".");
                classes.add(name.substring(0, name.length() - 6));
            }
        }

        jarFile.close();

        return classes;
    }
}
