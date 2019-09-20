package by.gartsmanovich.javawebdev.playroom.controller.command.impl;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.controller.command.Command;
import by.gartsmanovich.javawebdev.playroom.controller.command.manager
        .MessageManager;
import by.gartsmanovich.javawebdev.playroom.service.PlayRoomService;
import by.gartsmanovich.javawebdev.playroom.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.playroom.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class FindToyByRangeID implements Command {

    /**
     * The logger for FindToyByRangeID class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(FindToyByRangeID.class);

    /**
     * The valid number of arguments.
     */
    private static final int ARGS_NUMBER = 1;

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

        String[] args = request.split(" ");

        if (args.length <= ARGS_NUMBER) {
            LOGGER.trace("Incorrect parameters number!");
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                long start = Long.parseLong(args[0]);
                long end = Long.parseLong(args[1]);
                List<Toy> toys = playRoomService
                        .findEntityByRangeId(start, end);

                if (!toys.isEmpty()) {
                    response.append(MessageManager
                            .getProperty("message.find.by.range.id.correct"));
                    response.append("\n");
                    String s = toys.stream().map(Object::toString)
                                   .collect(Collectors.joining("\n"));
                    response.append(s);
                } else {
                    return MessageManager
                            .getProperty("message.entities.not.found");
                }
            } catch (NumberFormatException e) {
                LOGGER.trace("Invalid parameter format passed!");
                response.append(MessageManager
                        .getProperty("message.incorrect.args.format"));
            } catch (ServiceException e) {
                LOGGER.error("Failed to find the toys in the provided"
                        + " ID range!");
                response.append(MessageManager
                        .getProperty("message.find.by.range.id.failed"));
            }
            return response.toString();
        }
    }
}
