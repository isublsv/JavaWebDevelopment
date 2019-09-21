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
     * The valid number of arguments.
     */
    private static final int ARGS_NUMBER = 2;

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
            LOGGER.debug("Incorrect parameters number!");
            return MessageManager
                    .getProperty("message.incorrect.args.number");
        } else {
            try {
                double budget = Double.parseDouble(args[0]);
                String path = args[1];
                String delimiter = args[2];

                playRoomService.createPlayRoom(budget, path, delimiter);
                response = MessageManager
                        .getProperty("message.playroom.create.correct");
            } catch (NumberFormatException e) {
                LOGGER.debug("Invalid parameter format passed!");
                response = MessageManager
                    .getProperty("message.incorrect.args.format");
            } catch (ServiceException e) {
                LOGGER.debug("Failed to create play room!");
                response = e.getMessage();
            }
            return response;
        }
    }
}
