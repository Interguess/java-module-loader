package de.interguess.javamoduleloader.module;

import de.interguess.javamoduleloader.exception.ModuleLoadException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class SimpleModule<T> implements Module<T> {

    private final File file;

    private final ClassLoader classLoader;

    public SimpleModule(File file) throws ModuleLoadException {
        this.file = file;

        if (!file.exists()) {
            throw new ModuleLoadException("File does not exist: " + file);
        }

        if (!file.getName().endsWith(".jar")) {
            throw new ModuleLoadException("File is not a jar file: " + file);
        }

        try {
            this.classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
        } catch (MalformedURLException e) {
            throw new ModuleLoadException("Failed to create classloader", e);
        }
    }

    @Override
    public @NotNull File getFile() {
        return file;
    }

    @Override
    public @NotNull ClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public @Nullable InputStream getResourceAsStream(@NotNull String name) {
        return classLoader.getResourceAsStream(name);
    }

    @Override
    public @NotNull Class<T> getMainClassByName(@NotNull Class<T> requiredSuperClass, @NotNull String mainClass) throws ModuleLoadException {
        try {
            Class<?> clazz = classLoader.loadClass(mainClass);

            if (!requiredSuperClass.isAssignableFrom(clazz)) {
                throw new ModuleLoadException("Class " + mainClass + " does not extend or implement " + requiredSuperClass.getName());
            }

            return (Class<T>) clazz;
        } catch (ClassNotFoundException e) {
            throw new ModuleLoadException("Class not found: " + mainClass, e);
        }
    }
}
