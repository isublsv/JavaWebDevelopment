package by.gartsmanovich.composite.service.impl;

import by.gartsmanovich.composite.repository.specification.sort
        .SortSentencesByWordLengthSpecification;
import by.gartsmanovich.composite.repository.specification.sort
        .SortParagraphByNumberOfSentencesSpecification;
import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.repository.Repository;
import by.gartsmanovich.composite.repository.exception.RepositoryException;
import by.gartsmanovich.composite.repository.factory.RepositoryFactory;
import by.gartsmanovich.composite.repository.specification.sort
        .SortLexemesByCharNumberSpecification;
import by.gartsmanovich.composite.service.CompositeService;
import by.gartsmanovich.composite.service.exception.ServiceException;
import by.gartsmanovich.composite.service.validator.Validator;

/**
 * The Composite service interface implementation. Used to processing queries
 * from Controller layer, validating incoming parameters and passing them to the
 * Data Access layer.
 *
 * @author Dmitry Gartsmanovich
 */
public class CompositeServiceImpl implements CompositeService {

    /**
     * Provides the access to Composite repository class methods.
     */
    private Repository<Component> repository;

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * Constructs an implementation of Service application layer class
     * instance.
     */
    public CompositeServiceImpl() {
        RepositoryFactory factory = RepositoryFactory.getInstance();
        repository = factory.getCompositeRepository();
        validator = new Validator();
    }

    /**
     * Creates the Composite instance from provided data file.
     *
     * @param path      the path to data file.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void createComposite(final String path) throws ServiceException {
        try {
            if (validator.isValidValue(path)) {
                throw new ServiceException(
                        "The path parameter for creating composite is not "
                        + "valid");
            } else {
                repository.createComposite(path);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Sorts the paragraphs by the number of sentences.
     *
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public String sortParagraphsByNumberOfSentences() throws ServiceException {
        try {
            return repository.query(
                    new SortParagraphByNumberOfSentencesSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Sorts words in the sentences by length.
     *
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public String sortWordsByLength() throws ServiceException {
        try {
            return repository.query(
                    new SortSentencesByWordLengthSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Sorts lexemes by number of provided char, than by alphabet.
     *
     * @param c the provided char value.
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public String sortLexemesByCharNumber(final char c)
            throws ServiceException {
        try {
            return repository
                    .query(new SortLexemesByCharNumberSpecification(c));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Saves the last result obtained after executing the any method.
     *
     * @param path the path to storage file.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void saveComposite(final String path) throws ServiceException {
        try {
            if (validator.isValidValue(path)) {
                throw new ServiceException("The path parameter for saving"
                                           + " composite is not valid");
            } else {
                repository.saveComposite(path);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
