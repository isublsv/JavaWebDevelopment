package by.gartsmanovich.java_web_dev.playroom.controller.command.impl;

import by.gartsmanovich.java_web_dev.playroom.controller.command.Command;
import by.gartsmanovich.java_web_dev.playroom.controller.command.manager
        .MessageManager;

public class WrongRequest implements Command {

    /**
     * Indicates that there was received wrong request and command was not
     * found.
     *
     * @param request the provided string for processing.
     * @return the string value for the given request.
     */
    @Override
    public String execute(final String request) {
        return MessageManager.getProperty("message.wrongrequest");
    }
}
