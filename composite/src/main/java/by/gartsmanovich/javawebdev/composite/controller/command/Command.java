package by.gartsmanovich.javawebdev.composite.controller.command;

/**
 * Interface for Commands.
 *
 * @author Dmitry Gartsmanovich
 */
public interface Command {

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provided string for processing.
     * @return the result string of correct or incorrect execution of the
     * command.
     */
    String execute(String request);
}
