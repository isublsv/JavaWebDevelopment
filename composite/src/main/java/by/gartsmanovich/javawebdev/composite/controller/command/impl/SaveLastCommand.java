package by.gartsmanovich.javawebdev.composite.controller.command.impl;

import by.gartsmanovich.javawebdev.composite.controller.command.Command;
import by.gartsmanovich.javawebdev.composite.controller.command.manager.MessageManager;
import by.gartsmanovich.javawebdev.composite.service.CompositeService;
import by.gartsmanovich.javawebdev.composite.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.composite.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes the save last result command that proceed user request and
 * invoke appropriate method from Service layer of the application. The result
 * depends on input parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class SaveLastCommand implements Command {

    /**
     * The logger for CreateMatrixCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            SaveLastCommand.class);

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
                matrixService.saveLastResult(request);
                response = MessageManager.getProperty(
                        "message.save.last.result.correct");
            } catch (ServiceException e) {
                response = e.getMessage();
                LOGGER.error(response);
            }
        }
        return response;
    }
}
