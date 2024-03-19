package de.interguess.javamoduleloader.module;

import de.interguess.javamoduleloader.exception.ModuleLoadException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;

/**
 * This interface represents a module in the Java module loader system.
 * A module is defined by its file, class loader, and main class.
 * It also provides a method to retrieve resources as streams.
 *
 * @param <T> the type of the main class of the module
 */
public interface Module<T> {

    /**
     * Returns the file of the module.
     *
     * @return the file of the module
     */
    @NotNull File getFile();

    /**
     * Returns the class loader of the module.
     *
     * @return the class loader of the module
     */
    @NotNull ClassLoader getClassLoader();

    /**
     * Returns an input stream for reading the specified resource, or null if the resource could not be found.
     *
     * @param name the resource name
     * @return an input stream for reading the resource, or null if the resource could not be found
     */
    @Nullable InputStream getResourceAsStream(@NotNull String name);

    /**
     * Returns the main class of the module by its name.
     * The returned class is guaranteed to be a subclass of the specified required superclass.
     *
     * @param requiredSuperClass the required superclass of the main class
     * @param mainClass          the name of the main class
     * @return the main class of the module
     * @throws ModuleLoadException if there was an error loading or verifying the main class
     */
    @NotNull Class<T> getMainClassByName(@NotNull Class<T> requiredSuperClass, @NotNull String mainClass) throws ModuleLoadException;
}