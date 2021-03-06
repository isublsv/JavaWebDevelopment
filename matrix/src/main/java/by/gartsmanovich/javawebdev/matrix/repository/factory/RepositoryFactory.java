package by.gartsmanovich.javawebdev.matrix.repository.factory;

import by.gartsmanovich.javawebdev.matrix.repository.Repository;
import by.gartsmanovich.javawebdev.matrix.repository.impl.MatrixRepository;

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
     * Provides the access to Matrix Repository class methods.
     */
    private final Repository matrixRepository = new MatrixRepository();

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
     * @return an instance of Matrix Repository implementation.
     */
    public Repository getMatrixRepository() {
        return matrixRepository;
    }
}
