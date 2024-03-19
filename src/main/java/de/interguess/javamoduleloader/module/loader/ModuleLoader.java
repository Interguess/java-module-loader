package de.interguess.javamoduleloader.module.loader;

import de.interguess.javamoduleloader.exception.ModuleLoadException;
import de.interguess.javamoduleloader.exception.ModuleUnloadException;
import de.interguess.javamoduleloader.module.Module;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

/**
 * This interface represents a module loader in the Java module loader system.
 * A module loader is responsible for loading and unloading modules.
 * It provides methods to get loaded modules, load a module by file, and unload a module by file or by module.
 *
 * @param <T> the type of the main class of the module
 */
public interface ModuleLoader<T> {

    /**
     * Returns a list of loaded modules.
     *
     * @return a list of loaded modules
     */
    @NotNull List<Module<T>> getLoadedModules();

    /**
     * Loads a module by its file.
     *
     * @param file the file of the module to load
     * @return the loaded module
     * @throws ModuleLoadException if the module could not be loaded
     */
    @NotNull Module<T> loadModuleByFile(@NotNull File file) throws ModuleLoadException;

    /**
     * Unloads a module by its file.
     *
     * @param file the file of the module to unload
     * @throws ModuleUnloadException if the module could not be unloaded
     */
    void unloadModuleByFile(@NotNull File file) throws ModuleUnloadException;

    /**
     * Unloads a module.
     *
     * @param module the module to unload
     * @throws ModuleUnloadException if the module could not be unloaded
     */
    void unloadModuleByModule(@NotNull Module<T> module) throws ModuleUnloadException;
}