package by.gartsmanovich.javawebdev.composite.service.impl;

import by.gartsmanovich.javawebdev.composite.repository.Repository;
import by.gartsmanovich.javawebdev.composite.repository.factory
        .RepositoryFactory;
import by.gartsmanovich.javawebdev.composite.service.CompositeService;
import by.gartsmanovich.javawebdev.composite.service.exception
        .ServiceException;
import by.gartsmanovich.javawebdev.composite.service.validator.Validator;

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
    private Repository repository;

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
    }

    /**
     * Sorts the paragraphs by the number of sentences.
     *
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public String sortParagraphsByNumberOfSentences() throws ServiceException {
        return null;
    }

    /**
     * Sorts words in the sentences by length.
     *
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public String sortWordsByLength() throws ServiceException {
        return null;
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
        return null;
    }

    /**
     * Saves the last result obtained after executing the any method.
     *
     * @param path the path to storage file.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void saveLastResult(final String path) throws ServiceException {

    }
}
