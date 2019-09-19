package by.gartsmanovich.java_web_dev.playroom.controller.command.impl;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.controller.command.Command;
import by.gartsmanovich.java_web_dev.playroom.controller.command.manager
        .MessageManager;
import by.gartsmanovich.java_web_dev.playroom.service.PlayRoomService;
import by.gartsmanovich.java_web_dev.playroom.service.exception.ServiceException;
import by.gartsmanovich.java_web_dev.playroom.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateToy implements Command {

    /**
     * The logger for UpdateToy class.
     */
    private static final Logger LOGGER = LogManager.getLogger(UpdateToy.class);

    /**
     * The valid number of arguments.
     */
    private static final int ARGS_NUMBER = 4;

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
        PlayRoomService<Toy> playRoomService = serviceFactory
                .getPlayRoomService();

        String[] args = request.split(" ");

        if (args.length < ARGS_NUMBER) {
            LOGGER.trace("Incorrect parameters number!");
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                if (playRoomService.updateEntity(args)) {
                    response = MessageManager
                            .getProperty("message.update.correct");
                } else {
                    return MessageManager
                            .getProperty("message.update.not.found");
                }
            } catch (ServiceException e) {
                LOGGER.error("Failed to update the toy data!");
                response = MessageManager
                        .getProperty("message.update.failed");
            }
            return response;
        }
    }
}
