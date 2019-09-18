package by.gartsmanovich.java_web_dev.playroom.repository.factory;

import by.gartsmanovich.java_web_dev.playroom.repository.Repository;
import by.gartsmanovich.java_web_dev.playroom.repository.impl.ToyRepository;

public final class RepositoryFactory {

    /**
     * The factory instance will be created at the start of the execution of
     * Repository Factory class.
     */
    private static final RepositoryFactory INSTANCE = new RepositoryFactory();

    /**
     * Provides the access to Repository class methods.
     */
    private final Repository repository = new ToyRepository();

    private RepositoryFactory() {

    }

    /**
     * Global point for access to factory methods.
     *
     * @return the instance of Data Access Object factory.
     */
    public static RepositoryFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the implementation of the Repository interface.
     * An instance provide the access to Data Access Object application
     * layer methods.
     *
     * @return an instance of Toy Repository implementation.
     */
    public Repository getRepository() {
        return repository;
    }
}
