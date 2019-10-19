package by.gartsmanovich.composite.controller.command.impl;

import by.gartsmanovich.composite.controller.command.Command;
import by.gartsmanovich.composite.service.CompositeService;
import by.gartsmanovich.composite.service.exception.ServiceException;
import by.gartsmanovich.composite.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes the sort paragraphs command that invoke appropriate
 * method from Service layer of the application.
 *
 * @author Dmitry Gartsmanovich
 */
public class SortParagraphsCommand implements Command {

    /**
     * The logger for SortParagraphsCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SortParagraphsCommand.class);

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provided string for processing.
     * @return the result string of correct or incorrect execution of the
     * command.
     */
    @Override
    public String execute(final String request) {

        StringBuilder response = new StringBuilder();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CompositeService compositeService = serviceFactory
                .getCompositeService();

        try {
            response.append(compositeService
                                    .sortParagraphsByNumberOfSentences());
        } catch (ServiceException e) {
            response.append(e.getMessage());
            LOGGER.error(response);
        }

        return response.toString();
    }
}
