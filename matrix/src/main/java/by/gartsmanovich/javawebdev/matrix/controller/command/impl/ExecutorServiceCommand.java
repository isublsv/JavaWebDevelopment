package by.gartsmanovich.javawebdev.matrix.controller.command.impl;

import by.gartsmanovich.javawebdev.matrix.controller.command.Command;
import by.gartsmanovich.javawebdev.matrix.service.MatrixService;
import by.gartsmanovich.javawebdev.matrix.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.matrix.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ExecutorServiceCommand implements Command {

    /**
     * The logger for Option3 class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ExecutorServiceCommand.class);

    /**
     * The default delimiter.
     */
    private static final String DEL = " ";

    /**
     * The appender for response string.
     */
    private static final String APPENDER = "\n";

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
        MatrixService matrixService = serviceFactory.getMatrixService();

        try {
            int[][] array = matrixService.doOption3();

            response.append(APPENDER);
            response.append(Arrays.stream(array)
                                  .map(s -> Arrays.stream(s)
                                                  .mapToObj(String::valueOf)
                                                  .collect(Collectors
                                                                 .joining(DEL)))
                                  .collect(Collectors.joining(
                                          System.lineSeparator())));
        } catch (ServiceException e) {
            response.append(e.getMessage());
            LOGGER.error(response);
        }

        return response.toString();
    }
}
