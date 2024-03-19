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
    public @NotNull Class<T> getMainClassByName(@NotNull Class<T> requiredSuperClass, @NotNull String mainClass) throws ClassNotFoundException {
        Class<?> clazz = classLoader.loadClass(mainClass);

        if (clazz == null) {
            throw new ClassNotFoundException("Class not found: " + mainClass);
        } else if (!clazz.getSuperclass().equals(requiredSuperClass)) {
            throw new ClassNotFoundException("Class " + mainClass + " does not implement " + requiredSuperClass.getName());
        } else {
            return (Class<T>) clazz;
        }
    }
}
