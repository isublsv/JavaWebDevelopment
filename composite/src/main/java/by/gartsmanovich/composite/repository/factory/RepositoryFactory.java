package by.gartsmanovich.composite.repository.factory;

import by.gartsmanovich.composite.repository.Repository;
import by.gartsmanovich.composite.repository.impl.CompositeRepository;
import by.gartsmanovich.composite.bean.Component;

/**
 * Class Repository factory used to create the different kinds of repositories
 * depending on the entity type.
 *
 * @author Dmitry Gartsmanovich
 */
public final class RepositoryFactory {

    /**
     * The factory instance will be created at the start of the execution of
     * Repository Factory class.
     */
    private static final RepositoryFactory INSTANCE = new RepositoryFactory();

    /**
     * Provides the access to Composite Repository class methods.
     */
    private final Repository<Component> compositeRepository =
            new CompositeRepository();

    /**
     * Private constructor. Forbids the explicit object creation.
     */
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
     * @return an instance of Composite Repository implementation.
     */
    public Repository<Component> getCompositeRepository() {
        return compositeRepository;
    }
}
