package by.gartsmanovich.javawebdev.matrix.service.factory;

import by.gartsmanovich.javawebdev.matrix.service.MatrixService;
import by.gartsmanovich.javawebdev.matrix.service.impl.MatrixServiceImpl;

/**
 * Class Service factory used to create the different kinds of services
 * depending on the entity type.
 *
 * @author Dmitry Gartsmanovich
 */
public final class ServiceFactory {

    /**
     * The factory instance will be created at the start of the execution of
     * ServiceFactory class.
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * The variable provides the access to Matrix Service methods.
     */
    private final MatrixService matrixService = new MatrixServiceImpl();

    /**
     * Private constructor. Forbids the explicit object creation.
     */
    private ServiceFactory() {
    }

    /**
     * Global point for access to factory methods.
     *
     * @return the instance of Service factory.
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the implementation of the Matrix Service interface.
     * An instance provide the access to Service application layer methods.
     *
     * @return an instance of Matrix Service
     */
    public MatrixService getMatrixService() {
        return matrixService;
    }
}
