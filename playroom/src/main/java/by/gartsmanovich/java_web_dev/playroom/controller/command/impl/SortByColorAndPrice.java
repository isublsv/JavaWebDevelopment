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

public class SortByColorAndPrice implements Command {

    /**
     * The logger for SortByColorAndPrice class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(SortByColorAndPrice.class);

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
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
            playRoomService.sortByColorAndPrice();
            response = MessageManager
                    .getProperty("message.sort.by.color.and.price.correct");
        } catch (ServiceException e) {
            LOGGER.error("The play room storage was sorted by"
                    + " color and price! ");
            response = MessageManager
                    .getProperty("message.sort.by.color.and.price.failed");
        }
        return response;
    }
}
