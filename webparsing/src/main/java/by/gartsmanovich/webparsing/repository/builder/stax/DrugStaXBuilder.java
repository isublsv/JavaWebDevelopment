package by.gartsmanovich.webparsing.repository.builder.stax;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.builder.DrugEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 *
 */
public class DrugStaXBuilder extends AbstractDrugBuilder {
    private XMLInputFactory factory;

    /**
     *
     */
    public DrugStaXBuilder() {
        factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                            false);
    }

    /**
     * @param filename
     */
    @Override
    public void buildSetDrugs(final String filename) {
        FileInputStream inputStream;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(filename));
            reader = factory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (DrugEnum.valueOf(name.toUpperCase())
                        == DrugEnum.DRUG) {
                        Drug account = buildDrug(reader);
                        getDrugs().add(account);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File: " + filename + " not found! " + e);
        } catch (XMLStreamException e) {
            System.err.println("Parser error! " + e);
        }
    }

    /**
     * 
     * @param reader
     * @return
     * @throws XMLStreamException
     */
    private Drug buildDrug(final XMLStreamReader reader) throws
            XMLStreamException {

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    break;
                case XMLStreamConstants.END_ELEMENT:

                    break;
            }
        }
        throw new XMLStreamException("Unknown element");
    }

    /**
     * 
     * @param reader
     * @return
     * @throws XMLStreamException
     */
    private Double getFromXMLToDouble(final XMLStreamReader reader) throws
            XMLStreamException {
        Double d = null;
        if (reader.hasNext()) {
            reader.next();
            d = Double.valueOf(reader.getText());
        }
        return d;
    }
}
