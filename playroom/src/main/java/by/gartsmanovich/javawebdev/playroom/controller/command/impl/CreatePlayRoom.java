package by.gartsmanovich.javawebdev.playroom.controller.command.impl;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.controller.command.Command;
import by.gartsmanovich.javawebdev.playroom.controller.command.manager
        .MessageManager;
import by.gartsmanovich.javawebdev.playroom.service.PlayRoomService;
import by.gartsmanovich.javawebdev.playroom.service.exception
        .ServiceException;
import by.gartsmanovich.javawebdev.playroom.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreatePlayRoom implements Command {

    /**
     * The logger for CreatePlayRoom class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(CreatePlayRoom.class);


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
            return MessageManager
                    .getProperty("message.incorrect.args.number");
        } else {
            try {
                double budget = Double.parseDouble(request.trim());
                playRoomService.createPlayRoom(budget);
                response = MessageManager
                        .getProperty("message.playroom.create.correct");
            } catch (NumberFormatException e) {
                LOGGER.trace("Invalid parameter format passed!");
                response = MessageManager
                .getProperty("message.incorrect.args.format");
            } catch (ServiceException e) {
                LOGGER.error("Failed to create play room!");
                response = MessageManager
                        .getProperty("message.playroom.create.failed");
            }
            return response;
        }
    }
}
