package by.gartsmanovich.composite.controller.command.impl;

import by.gartsmanovich.composite.controller.command.Command;
import by.gartsmanovich.composite.controller.command.manager.MessageManager;
import by.gartsmanovich.composite.service.CompositeService;
import by.gartsmanovich.composite.service.exception.ServiceException;
import by.gartsmanovich.composite.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes the create composite command that proceed user request and
 * invoke appropriate method from Service layer of the application. The result
 * depends on input parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class CreateCompositeCommand implements Command {

    /**
     * The logger for CreateCompositeCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            CreateCompositeCommand.class);

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
        CompositeService matrixService = serviceFactory.getCompositeService();

        if (request.isEmpty()) {
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                matrixService.createComposite(request);
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
