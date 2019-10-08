package by.gartsmanovich.javawebdev.composite.controller.command.impl;

import by.gartsmanovich.javawebdev.composite.controller.command.Command;
import by.gartsmanovich.javawebdev.composite.service.CompositeService;
import by.gartsmanovich.javawebdev.composite.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.composite.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class describes the thread distribution command that invoke appropriate
 * method from Service layer of the application.
 *
 * @author Dmitry Gartsmanovich
 */
public class ThreadDistributionCommand implements Command {

    /**
     * The logger for ThreadDistributionCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ThreadDistributionCommand.class);

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
        CompositeService matrixService = serviceFactory.getCompositeService();

        try {
            int[][] array = matrixService.fillBySeparateThreads();

            response.append(APPENDER);
            response.append(Arrays.stream(array)
                                  .map(s -> Arrays.stream(s)
                                                  .mapToObj(String::valueOf)
                                                  .collect(Collectors.joining(
                                                          DEL)))
                                  .collect(Collectors.joining(
                                          System.lineSeparator())));
        } catch (ServiceException e) {
            response.append(e.getMessage());
            LOGGER.error(response);
        }

        return response.toString();
    }
}
