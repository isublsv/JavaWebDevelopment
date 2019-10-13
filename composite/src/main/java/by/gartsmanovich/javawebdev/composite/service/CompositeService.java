package by.gartsmanovich.javawebdev.composite.service;

import by.gartsmanovich.javawebdev.composite.service.exception
        .ServiceException;

/**
 * The interface used to determine the common structure of Service Layer
 * classes.
 *
 * @author Dmitry Gartsmanovich
 */
public interface CompositeService {

    /**
     * Creates the Composite instance from provided data file.
     *
     * @param path      the path to data file.
     * @throws ServiceException if error happens during execution.
     */
    void createComposite(String path) throws ServiceException;

    /**
     * Sorts the paragraphs by the number of sentences.
     *
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    String sortParagraphsByNumberOfSentences() throws ServiceException;

    /**
     * Sorts words in the sentences by length.
     *
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    String sortWordsByLength() throws ServiceException;

    /**
     * Sorts lexemes by number of provided char, than by alphabet.
     *
     * @param c the provided char value.
     * @return the result string.
     * @throws ServiceException if error happens during execution.
     */
    String sortLexemesByCharNumber(char c) throws ServiceException;

    /**
     * Saves the last result obtained after executing the any method.
     *
     * @param path the path to storage file.
     * @throws ServiceException if error happens during execution.
     */
    void saveLastResult(String path) throws ServiceException;
}
