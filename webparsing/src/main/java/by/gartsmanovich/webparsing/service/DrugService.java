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
     * Creates the list of entities from provided xml-document.
     *
     * @param path the path to xml-document.
     * @param key the key.
     * @return the list of drugs.
     * @throws ServiceException if error happens during execution.
     */
    List<Drug> executeBuilder(String key, String path) throws ServiceException;
}

