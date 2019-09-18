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

public class FindToyByID implements Command {

    /**
     * The logger for FindToyByID class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(FindToyByID.class);

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
        PlayRoomService<Toy> playRoomService = serviceFactory
                .getPlayRoomService();

        if (request.isEmpty()) {
            LOGGER.trace("Incorrect parameters number!");
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                long id = Long.parseLong(request.trim());
                Toy toy = playRoomService.findEntityByID(id);
                response.append(MessageManager
                        .getProperty("message.find.by.id.correct"));
                response.append("\n");
                response.append(toy.toString());
            } catch (NumberFormatException e) {
                LOGGER.trace("Invalid parameter format passed!");
                response.append(MessageManager
                        .getProperty("message.incorrect.args.format"));
            } catch (ServiceException e) {
                LOGGER.error("Failed to find the toy by ID!");
                response.append(MessageManager
                        .getProperty("message.find.by.id.failed"));
            }
            return response.toString();
        }
    }
}
