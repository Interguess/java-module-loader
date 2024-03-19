package de.interguess.javamoduleloader.module.loader;

import de.interguess.javamoduleloader.module.Module;
import de.interguess.javamoduleloader.exception.ModuleLoadException;
import de.interguess.javamoduleloader.exception.ModuleUnloadException;
import de.interguess.javamoduleloader.module.SimpleModule;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleModuleLoader<T> implements ModuleLoader<T> {

    private final Map<File, Module<T>> modules;

    public SimpleModuleLoader() {
        this.modules = new HashMap<>();
    }

    @Override
    public @NotNull List<Module<T>> getLoadedModules() {
        return List.copyOf(modules.values());
    }

    @Override
    public @NotNull Module<T> loadModuleByFile(@NotNull File file) throws ModuleLoadException {
        return new SimpleModule<>(file);
    }

    @Override
    public void unloadModuleByFile(@NotNull File file) throws ModuleUnloadException {
        Module<T> module = modules.get(file);

        if (module == null) {
            throw new ModuleUnloadException("Module not found: " + file.getName());
        }

        unloadModuleByModule(module);
    }

    @Override
    public void unloadModuleByModule(@NotNull Module<T> module) throws ModuleUnloadException {
        modules.remove(module.getFile());

        try {
            ((URLClassLoader) module.getClassLoader()).close();
        } catch (Exception e) {
            throw new ModuleUnloadException("Failed to close classloader", e);
        }
    }
}
