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

public class Exit implements Command {

    /**
     * The logger for Exit class.
     */
    private static final Logger LOGGER = LogManager.getLogger(Exit.class);

    /**
     * The main method that changes its behavior depends on request parameter.
     *
     * @param request the provided string for processing.
     * @return a result string value of the provided request.
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
                playRoomService.saveAll(request);
                response = MessageManager.getProperty("message.save.correct");
            } catch (ServiceException e) {
                response = e.getMessage();
                LOGGER.error(response);
            }
            return response;
        }
    }
}
