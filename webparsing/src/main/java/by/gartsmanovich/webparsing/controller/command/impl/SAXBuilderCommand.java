package by.gartsmanovich.webparsing.controller.command.impl;

import by.gartsmanovich.webparsing.controller.command.Command;
import by.gartsmanovich.webparsing.service.DrugService;
import by.gartsmanovich.webparsing.service.exception.ServiceException;
import by.gartsmanovich.webparsing.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes the SAX Builder command that invoke appropriate
 * method from Service layer of the application.
 *
 * @author Dmitry Gartsmanovich
 */
public class SAXBuilderCommand implements Command {

    /**
     * The logger for SAXBuilderCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SAXBuilderCommand.class);

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
        DrugService drugService = serviceFactory.getDrugService();

        try {
            response.append(drugService.executeDOMBuilder(request));
        } catch (ServiceException e) {
            response.append(e.getMessage());
            LOGGER.error(response);
        }

        return response.toString();
    }
}
