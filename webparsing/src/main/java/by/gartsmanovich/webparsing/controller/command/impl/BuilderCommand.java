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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
     * Sets the error message attribute.
     */
    private static final String ERROR_MESSAGE = "errorMessage";

    /**
     * Handles the request parameters and passes its to the Service application
     * layer.
     *
     * @param request the provide request information for HTTP servlets.
     * @return the result string of correct or incorrect execution of the
     * command.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     */
    @Override
    public String execute(final HttpServletRequest request) throws IOException,
            ServletException {

        String page;

        String uploadPath = request.getServletContext()
                                   .getResource("")
                                   .getPath() + request.getServletContext()
                                                       .getInitParameter(
                                                               "upload");

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        String propertyKey = "path.page.index";

        if (fileName.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty(
                    "message.empty.filename"));
            page = ConfigurationManager.getProperty(propertyKey);
            return page;
        } else if (!fileName.endsWith("xml")) {
            request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty(
                    "message.not.valid.file"));
            page = ConfigurationManager.getProperty(propertyKey);
            return page;
        }

        File file = new File(uploadDir, fileName);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(),
                       StandardCopyOption.REPLACE_EXISTING);
        }

        String key = request.getParameter("key");
        String path = file.getAbsolutePath();
        String xsd = request.getServletContext().getResource("").getPath();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DrugService drugService = serviceFactory.getDrugService();

        try {
            List<Drug> drugs = drugService.executeBuilder(key, path, xsd);
            request.setAttribute("drugs", drugs);
            page = ConfigurationManager.getProperty("path.page.result");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute(ERROR_MESSAGE, MessageManager.getProperty(
                    "message.null.page"));
            page = ConfigurationManager.getProperty(propertyKey);
        }

        return page;
    }
}
