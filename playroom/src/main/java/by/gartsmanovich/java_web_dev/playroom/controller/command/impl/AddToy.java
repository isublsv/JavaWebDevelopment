package by.gartsmanovich.java_web_dev.playroom.controller.command.impl;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.controller.command.Command;
import by.gartsmanovich.java_web_dev.playroom.controller.command.manager
        .MessageManager;
import by.gartsmanovich.java_web_dev.playroom.service.PlayRoomService;
import by.gartsmanovich.java_web_dev.playroom.service.exception
        .ServiceException;
import by.gartsmanovich.java_web_dev.playroom.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AddToy implements Command {

    /**
     * The logger for AddToy class.
     */
    private static final Logger LOGGER = LogManager.getLogger(AddToy.class);

    /**
     * Handles the request parameters and adds entity to the specific
     * repository.
     *
     * @param request the provided string for processing.
     * @return the result string of correct or incorrect execution of the
     * command.
     */
    public String execute(final String request) {

        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PlayRoomService<Toy> playRoomService = serviceFactory
                .getPlayRoomService();

        try {
            playRoomService.addNewEntity(new Toy());
            response = MessageManager.getProperty("message.addnewtoy.correct");
        } catch (ServiceException e) {
            LOGGER.error("The toy cannot be added!");
            response = MessageManager.getProperty("message.addnewtoy"
                    + ".incorrect");
        }

        return response;
    }
}
