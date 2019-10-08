package by.gartsmanovich.javawebdev.composite.controller.command.impl;

import by.gartsmanovich.javawebdev.composite.controller.command.Command;
import by.gartsmanovich.javawebdev.composite.controller.command.manager.MessageManager;
import by.gartsmanovich.javawebdev.composite.service.CompositeService;
import by.gartsmanovich.javawebdev.composite.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.composite.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class describes the create matrix command that proceed user request and
 * invoke appropriate method from Service layer of the application. The result
 * depends on input parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class CreateMatrixCommand implements Command {

    /**
     * The logger for CreateMatrixCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            CreateMatrixCommand.class);

    /**
     * The default delimiter.
     */
    private static final String DEL = " ";

    /**
     * The arguments limit.
     */
    private static final int LIMIT = 2;

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

        String[] args = request.split(DEL, LIMIT);

        if (args.length != LIMIT) {
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                matrixService.createMatrix(args[0], args[1]);
                response = MessageManager.getProperty(
                        "message.matrix.create.correct");
            } catch (ServiceException e) {
                response = e.getMessage();
                LOGGER.error(response);
            }
        }
        return response;
    }
}
