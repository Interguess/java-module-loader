package de.interguess.javamoduleloader.exception;

/**
 * This class represents an exception that is thrown when a module fails to load.
 * It extends the Exception class, meaning it is a checked exception.
 * It provides two constructors: one that takes a message, and another that takes a message and a cause.
 */
public class ModuleLoadException extends Exception {

    /**
     * Constructs a new ModuleLoadException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public ModuleLoadException(String message) {
        super(message);
    }

    /**
     * Constructs a new ModuleLoadException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param cause   the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ModuleLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}