package by.gartsmanovich.webparsing.controller.command.impl;

import by.gartsmanovich.webparsing.controller.command.Command;
import by.gartsmanovich.webparsing.controller.command.manager.MessageManager;
import by.gartsmanovich.webparsing.service.DrugService;
import by.gartsmanovich.webparsing.service.exception.ServiceException;
import by.gartsmanovich.webparsing.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes DOM Builder command that proceed user request and
 * invoke appropriate method from Service layer of the application. The result
 * depends on input parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class DOMBuilderCommand implements Command {

    /**
     * The logger for DOMBuilderCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            DOMBuilderCommand.class);

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

        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DrugService drugService = serviceFactory.getDrugService();

        if (request.isEmpty()) {
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                drugService.executeDOMBuilder(request);
                response = MessageManager.getProperty(
                        "message.composite.create.correct");
            } catch (ServiceException e) {
                response = e.getMessage();
                LOGGER.error(response);
            }
        }
        return response;
    }
}
