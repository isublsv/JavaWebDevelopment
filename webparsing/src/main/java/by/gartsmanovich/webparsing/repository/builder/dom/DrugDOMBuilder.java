package by.gartsmanovich.webparsing.repository.builder.dom;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.bean.Group;
import by.gartsmanovich.webparsing.bean.builder.DrugBuilder;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 *
 */
public class DrugDOMBuilder extends AbstractDrugBuilder {

    /**
     *
     */
    private DocumentBuilder documentBuilder;

    /**
     *
     */
    private DocumentBuilderFactory factory;

    /**
     *
     */
    public DrugDOMBuilder() {
    }

    /**
     * @param element
     * @param elementName
     * @return
     */
    private static String getElementTextContent(final Element element,
            final String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    /**
     * @param filename
     */
    @Override
    public void buildSetDrugs(final String filename) throws
            RepositoryException {
        Document document;
        try {
            factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();

            NodeList accountList = root.getElementsByTagName("drug");
            for (int i = 0; i < accountList.getLength(); i++) {
                Element drugElement = (Element) accountList.item(i);
                Drug drug = buildDrug(drugElement);
                getDrugs().add(drug);
            }
        } catch (SAXException e) {
            throw new RepositoryException("Parsing failure!", e);
        } catch (IOException e) {
            throw new RepositoryException("File error or I/O error!", e);
        } catch (ParserConfigurationException eValue) {
            eValue.printStackTrace();
        }
    }

    /**
     * @param element
     * @return
     */
    private Drug buildDrug(final Element element) {
        DrugBuilder builder = new DrugBuilder();
        String id = element.getAttribute("id").replace("drug", "");
        builder.setID(Long.parseLong(id));
        builder.setName(getElementTextContent(element, "name"));
        Group group = Group.valueOf(
                getElementTextContent(element, "group").toUpperCase());
        builder.setGroup(group);

        Drug drug = builder.build();
        NodeList analogs = element.getElementsByTagName("analogs");
        if (analogs != null) {
            NodeList childNodes = analogs.item(0).getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Element analog = (Element) analogs.item(i);
                String value = getElementTextContent(analog, "analog");
                drug.addAnalog(value);
            }
        }

        return drug;
    }
}
