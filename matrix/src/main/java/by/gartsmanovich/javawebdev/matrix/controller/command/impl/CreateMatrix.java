package by.gartsmanovich.javawebdev.matrix.controller.command.impl;

import by.gartsmanovich.javawebdev.matrix.controller.command.Command;
import by.gartsmanovich.javawebdev.matrix.controller.command.manager.MessageManager;
import by.gartsmanovich.javawebdev.matrix.service.MatrixService;
import by.gartsmanovich.javawebdev.matrix.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.matrix.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateMatrix implements Command {

    /**
     * The logger for CreatePlayRoom class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            CreateMatrix.class);

    /**
     * The request delimiter.
     */
    private static final String REQUEST_DEL = " ";

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
        MatrixService matrixService = serviceFactory.getMatrixService();

        String[] args = request.split(REQUEST_DEL);

        if (args.length != 2) {
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
