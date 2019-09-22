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

public class UpdateToy implements Command {

    /**
     * The logger for UpdateToy class.
     */
    private static final Logger LOGGER = LogManager.getLogger(UpdateToy.class);

    /**
     * The splitter for request string.
     */
    private static final String SPLIT = " ";

    /**
     * The valid number of arguments.
     */
    private static final int ARGS_NUMBER = 4;

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

        String[] args = request.split(SPLIT);

        if (args.length < ARGS_NUMBER) {
            LOGGER.debug("Incorrect parameters number!");
            return MessageManager.getProperty("message.incorrect.args.number");
        } else {
            try {
                long id = Long.parseLong(args[0]);
                args = request.substring(request.indexOf(SPLIT))
                              .trim()
                              .split(SPLIT);

                if (playRoomService.updateEntity(id, args)) {
                    response = MessageManager.getProperty(
                            "message.update.correct");
                } else {
                    return MessageManager.getProperty(
                            "message.update.not.found");
                }
            } catch (NumberFormatException e) {
                LOGGER.debug("Invalid parameter format was passed!");
                response = MessageManager
                        .getProperty("message.incorrect.args.format");
            } catch (ServiceException e) {
                response = e.getMessage();
                LOGGER.error(response);
            }
            return response;
        }
    }
}
