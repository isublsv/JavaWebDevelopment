package by.gartsmanovich.webparsing.service.factory;

import by.gartsmanovich.webparsing.service.DrugService;
import by.gartsmanovich.webparsing.service.impl.DrugServiceImpl;

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
     * The variable provides the access to Medicine Service methods.
     */
    private final DrugService drugService = new DrugServiceImpl();

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
     * Returns the implementation of the Medicine Service interface.
     * An instance provide the access to Service application layer methods.
     *
     * @return an instance of Medicine Service.
     */
    public DrugService getDrugService() {
        return drugService;
    }
}
