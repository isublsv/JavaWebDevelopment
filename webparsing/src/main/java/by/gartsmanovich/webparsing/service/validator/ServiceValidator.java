package by.gartsmanovich.webparsing.service.validator;

import by.gartsmanovich.webparsing.service.exception.ServiceException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Utility class used to validate provided data.
 *
 * @author Dmitry Gartsmanovich
 */
public class ServiceValidator {

    /**
     * The path to xml-schema.
     */
    private static final String SCHEMA_NAME = "WEB-INF/classes/data"
                                              + "/Medicines.xsd";

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Validates the provided xml-document matches to the
     * {@link ServiceValidator#SCHEMA_NAME}.
     *
     * @param fileName the path to the xml-document.
     * @param xsdValue the path to schema-document.
     * @return true if xml-document is valid, false - otherwise.
     * @throws ServiceException if error happens during execution.
     */
    public boolean isValidDocument(final String fileName,
            final String xsdValue) throws
            ServiceException {
        if (!isValidValue(fileName) || !isValidValue(xsdValue)) {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;

            SchemaFactory factory = SchemaFactory.newInstance(language);
            File schemaLocation = new File(xsdValue + SCHEMA_NAME);
            try {
                Schema schema = factory.newSchema(schemaLocation);
                Validator validator = schema.newValidator();

                validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
                validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

                Source source = new StreamSource(fileName);
                validator.validate(source);
                return true;
            } catch (SAXException | IOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        return false;
    }
}
