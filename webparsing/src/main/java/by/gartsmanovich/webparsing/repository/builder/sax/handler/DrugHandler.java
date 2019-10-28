package by.gartsmanovich.webparsing.repository.builder.sax.handler;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.DrugEnum;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DrugHandler extends DefaultHandler {
    private Set<Drug> drugs;
    private Drug current = null;
    private EnumSet<DrugEnum> withText;
    private DrugEnum currentEnum = null;

    public DrugHandler() {
        this.drugs = new HashSet<>();
        this.withText = EnumSet.range(DrugEnum.CERTIFICATE,
                                      DrugEnum.CONCENTRATION);
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }

    @Override
    public void startElement(String uri, String localName, String qname,
            Attributes attrs) {
        if ("Account".equals(localName) && attrs != null) {

        } else {
            DrugEnum temp = DrugEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws
            SAXException {
        if ("Account".equals(localName)) {
            drugs.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws
            SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {

        }
        currentEnum = null;
    }
}

