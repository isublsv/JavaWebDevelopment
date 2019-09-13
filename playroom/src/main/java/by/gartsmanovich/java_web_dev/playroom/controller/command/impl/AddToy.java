package by.gartsmanovich.java_web_dev.playroom.controller.command.impl;

import by.gartsmanovich.java_web_dev.playroom.controller.command.Command;
import by.gartsmanovich.java_web_dev.playroom.controller.command.manager
        .MessageManager;

public class AddToy implements Command {

    /**
     * Handles the request parameters and adds entity to the specific
     * repository.
     *
     * @param request the provided string for processing.
     * @return the result string of correct or incorrect execution of the
     * command.
     */
    public String execute(final String request) {
        return MessageManager.getProperty("message.addtoy");
    }
}
