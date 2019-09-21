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

import java.util.List;
import java.util.stream.Collectors;

public class SortByAge implements Command {

    /**
     * The logger for SortByAge class.
     */
    private static final Logger LOGGER = LogManager.getLogger(SortByAge.class);

    /**
     * The delimiter for result string.
     */
    private static final String DEL = "\n";

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provided string for processing.
     * @return the result string of correct or incorrect execution of the
     * command.
     */
    public String execute(final String request) {

        StringBuilder response = new StringBuilder();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PlayRoomService<Toy> playRoomService = serviceFactory
                .getPlayRoomService();

        try {
            List<Toy> toys = playRoomService.sortByAge();

            if (!toys.isEmpty()) {
                response.append(MessageManager
                                   .getProperty("message.sort.by.age.correct"));
                response.append(DEL);
                String s = toys.stream()
                               .map(Object::toString)
                               .collect(Collectors.joining(DEL));
                response.append(s);
            } else {
                return MessageManager
                        .getProperty("message.entities.not.found");
            }
        } catch (ServiceException e) {
            LOGGER.debug("Failed to sort play room storage by age!");
            response.append(e.getMessage());
        }
        return response.toString();

    }
}
