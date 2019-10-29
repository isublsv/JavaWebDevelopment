package by.gartsmanovich.webparsing.repository.builder.sax;

import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.builder.sax.handler.DrugHandler;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * The SAX parser realisation.
 *
 * @author Dmitry Gartsmanovich
 */
public class DrugSAXBuilder extends AbstractDrugBuilder {

    /**
     * The realisation of base class for SAX2 event handlers.
     */
    private DrugHandler drugHandler;

    /**
     * Interface for reading an XML document using callbacks.
     */
    private XMLReader reader;

    /**
     * The default constructor.
     *
     * @throws RepositoryException if error happens during execution.
     */
    public DrugSAXBuilder() throws RepositoryException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            SAXParser parser = factory.newSAXParser();
            drugHandler = new DrugHandler();

            reader = parser.getXMLReader();
            reader.setContentHandler(drugHandler);
        } catch (SAXException e) {
            throw new RepositoryException("SAX parser error!", e);
        } catch (ParserConfigurationException e) {
            throw new RepositoryException("Parser configuration error!", e);
        }
    }

    /**
     * Creates the list of the drug objects.
     *
     * @param filename the provided path to the xml-document.
     */
    @Override
    public void buildSetDrugs(final String filename) throws
            RepositoryException {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            throw new RepositoryException("SAX parser error!", e);
        } catch (IOException e) {
            throw new RepositoryException("File error or I/O error!", e);
        }
        setDrugs(drugHandler.getDrugs());
    }
}
