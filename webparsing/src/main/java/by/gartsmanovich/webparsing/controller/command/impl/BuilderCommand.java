package by.gartsmanovich.webparsing.controller.command.impl;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.controller.command.Command;
import by.gartsmanovich.webparsing.controller.command.manager.ConfigurationManager;
import by.gartsmanovich.webparsing.controller.command.manager.MessageManager;
import by.gartsmanovich.webparsing.service.DrugService;
import by.gartsmanovich.webparsing.service.exception.ServiceException;
import by.gartsmanovich.webparsing.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class describes Builder command that proceed user request and
 * invoke appropriate method from Service layer of the application. The result
 * depends on input parameters.
 *
 * @author Dmitry Gartsmanovich
 */
public class BuilderCommand implements Command {

    /**
     * The logger for BuilderCommand class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            BuilderCommand.class);

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     * @return the result string of correct or incorrect execution of the
     * command.
     */
    @Override
    public String execute(final HttpServletRequest request) {

        String page;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DrugService drugService = serviceFactory.getDrugService();

        String key = request.getParameter("key");
        String path = request.getParameter("path");

        try {
            List<Drug> drugs = drugService.executeBuilder(key, path);
            request.setAttribute("drugs", drugs);
            page = ConfigurationManager.getProperty("path.page.result");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("errorMessage", MessageManager.getProperty(
                    "message.null.page"));
            page = ConfigurationManager.getProperty("path.page.error");
        }

        return page;
    }
}
