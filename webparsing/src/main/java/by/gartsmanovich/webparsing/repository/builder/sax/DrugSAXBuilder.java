package by.gartsmanovich.webparsing.repository.builder.sax;

import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.builder.sax.handler.DrugHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 *
 */
public class DrugSAXBuilder extends AbstractDrugBuilder {

    /**
     *
     */
    private DrugHandler drugHandler;

    /**
     *
     */
    private XMLReader reader;

    /**
     *
     */
    public DrugSAXBuilder() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            SAXParser parser = factory.newSAXParser();
            drugHandler = new DrugHandler();

            reader = parser.getXMLReader();
            reader.setContentHandler(drugHandler);
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildSetDrugs(final String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            System.err.println("SAX parser error: " + e);
        } catch (IOException e) {
            System.err.println("I/O error: " + e);
        }
        //drugs = drugHandler.getDrugs();
    }
}
