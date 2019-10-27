package by.gartsmanovich.webparsing.service.impl;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.Repository;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import by.gartsmanovich.webparsing.repository.factory.RepositoryFactory;
import by.gartsmanovich.webparsing.service.DrugService;
import by.gartsmanovich.webparsing.service.exception.ServiceException;
import by.gartsmanovich.webparsing.service.validator.ServiceValidator;

import java.util.List;

/**
 * The Drug service interface implementation. Used to processing queries
 * from Controller layer, validating incoming parameters and passing them to
 * the
 * Data Access layer.
 *
 * @author Dmitry Gartsmanovich
 */
public class DrugServiceImpl implements DrugService {

    /**
     * Provides the access to Drug repository class methods.
     */
    private Repository<Drug> repository;

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private ServiceValidator validator;

    /**
     * Constructs an implementation of Service application layer class
     * instance.
     */
    public DrugServiceImpl() {
        RepositoryFactory factory = RepositoryFactory.getInstance();
        repository = factory.getDrugRepository();
        validator = new ServiceValidator();
    }

    /**
     * Creates the Composite instance from provided data file.
     *
     * @param path the path to data file.
     * @return the list of drugs.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Drug> executeDOMBuilder(final String path) throws
            ServiceException {
        try {
            if (validator.isValidDocument(path)) {
                throw new ServiceException(
                        "The path parameter for creating composite is not "
                        + "valid");
            } else {
                return repository.query(null);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

