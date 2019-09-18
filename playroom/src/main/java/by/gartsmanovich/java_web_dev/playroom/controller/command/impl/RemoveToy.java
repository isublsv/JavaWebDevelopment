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

public class RemoveToy implements Command {

    /**
     * The logger for RemoveToy class.
     */
    private static final Logger LOGGER = LogManager.getLogger(RemoveToy.class);

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

        if (request.isEmpty()) {
            LOGGER.trace("Incorrect parameters number!");
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                long id = Long.parseLong(request.trim());
                playRoomService.removeEntity(id);
                response = MessageManager
                        .getProperty("message.remove.correct");
            } catch (NumberFormatException e) {
                LOGGER.trace("Invalid parameter format was passed!");
                response = MessageManager
                        .getProperty("message.incorrect.args.format");
            } catch (ServiceException e) {
                LOGGER.error("Failed to remove the toy!");
                response = MessageManager
                        .getProperty("message.remove.failed");
            }
            return response;
        }
    }
}
