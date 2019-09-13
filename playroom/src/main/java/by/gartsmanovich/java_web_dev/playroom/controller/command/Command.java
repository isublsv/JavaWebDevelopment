package by.gartsmanovich.java_web_dev.playroom.controller.command;

/**
 * Interface for Commands.
 */
public interface Command {

    /**
     * The main method that changes its behavior depends on request parameter.
     *
     * @param request the provided string for processing.
     * @return a result string value of the provided request.
     */
    String execute(String request);
}
