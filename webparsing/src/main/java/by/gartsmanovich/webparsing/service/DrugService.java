package by.gartsmanovich.webparsing.service;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.service.exception.ServiceException;

import java.util.List;

/**
 * The interface used to determine the common structure of Service Layer
 * classes.
 *
 * @author Dmitry Gartsmanovich
 */
public interface DrugService {

    /**
     * Creates the Composite instance from provided data file.
     *
     * @param path the path to data file.
     * @return the list of drugs.
     * @throws ServiceException if error happens during execution.
     */
    List<Drug> executeDOMBuilder(String path) throws ServiceException;
}

