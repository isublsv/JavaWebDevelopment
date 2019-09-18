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

import java.util.List;
import java.util.stream.Collectors;

public class FindToyByFirstLetter implements Command {

    /**
     * The logger for FindToyByFirstLetter class.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(FindToyByFirstLetter.class);

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
                List<Toy> toys = playRoomService
                        .findEntityByFirstTitleLetter(request
                        .trim().charAt(0));
                response.append(MessageManager
                        .getProperty("message.find.by.first.letter.correct"));
                response.append("\n");
                String s = toys.stream().map(Object::toString)
                        .collect(Collectors.joining("\n"));
                response.append(s);
            } catch (ServiceException e) {
                LOGGER.error("Failed to find the toys by first letter!");
                response.append(MessageManager
                        .getProperty("message.find.by.first.letter.failed"));
            }
            return response.toString();
        }
    }
}
