package by.gartsmanovich.webparsing.service.impl;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.Repository;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import by.gartsmanovich.webparsing.repository.factory.RepositoryFactory;
import by.gartsmanovich.webparsing.repository.specification.impl
        .BuilderSpecification;
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
     * Creates the list of entities from provided xml-document.
     *
     * @param key the key.
     * @param path the path to xml-document.
     * @param xsd the path to schema.
     * @return the list of drugs.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public List<Drug> executeBuilder(final String key,
            final String path, final String xsd) throws ServiceException {
        try {
            if (validator.isValidValue(key)
                || !validator.isValidDocument(path, xsd)) {
                throw new ServiceException(
                        "The provided xml-document is not valid!");
            } else {
                return repository.query(
                        new BuilderSpecification(key, path));
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

