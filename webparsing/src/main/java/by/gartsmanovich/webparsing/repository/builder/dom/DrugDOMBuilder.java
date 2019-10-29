package by.gartsmanovich.webparsing.repository.builder.dom;

import by.gartsmanovich.webparsing.bean.Certificate;
import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.bean.Form;
import by.gartsmanovich.webparsing.bean.FormType;
import by.gartsmanovich.webparsing.bean.Group;
import by.gartsmanovich.webparsing.bean.PackageType;
import by.gartsmanovich.webparsing.bean.Pharmacy;
import by.gartsmanovich.webparsing.bean.impl.LiquidPackage;
import by.gartsmanovich.webparsing.bean.impl.OintmentPackage;
import by.gartsmanovich.webparsing.bean.impl.PowderPackage;
import by.gartsmanovich.webparsing.bean.impl.TabletsPackage;
import by.gartsmanovich.webparsing.bean.param.Liquid;
import by.gartsmanovich.webparsing.bean.param.Ointment;
import by.gartsmanovich.webparsing.bean.param.Pack;
import by.gartsmanovich.webparsing.bean.param.Powder;
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
import java.time.LocalDate;

/**
 * The DOM parser realisation.
 *
 * @author Dmitry Gartsmanovich
 */
public class DrugDOMBuilder extends AbstractDrugBuilder {

    /**
     * Creates the list of the drug objects.
     *
     * @param filename the provided path to the xml-document.
     */
    @Override
    public void buildSetDrugs(final String filename) throws
            RepositoryException {
        Document document;
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
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
        } catch (ParserConfigurationException e) {
            throw new RepositoryException("Parser configuration error!", e);
        }
    }

    /**
     * Creates the drug instance using data from the xml-document.
     *
     * @param element the provided element.
     * @return the drug instance.
     */
    private Drug buildDrug(final Element element) {
        Drug drug = new Drug();

        String id = element.getAttribute("id").replace("drug", "");
        drug.setId(Long.parseLong(id));
        drug.setName(getElementTextContent(element, "name"));
        Group group = Group.valueOf(
                getElementTextContent(element, "group").toUpperCase());
        drug.setGroup(group);

        getAnalogs(element, drug);
        FormType[] formTypes = FormType.values();
        for (FormType formType : formTypes) {
            String formName = formType.name().toLowerCase();

            Element forms = (Element) element.getElementsByTagName(formName)
                                             .item(0);

            Form drugForm = new Form(formType);
            if (forms != null) {
            NodeList pharmacies = forms.getElementsByTagName("pharmacy");

                for (int i = 0; i < pharmacies.getLength(); i++) {
                    Element pharmacyElement = (Element) pharmacies.item(i);

                    Pharmacy pharmacy = new Pharmacy();

                    Certificate certificate = getCertificate(pharmacyElement);
                    String dosage = getElementTextContent(pharmacyElement,
                                                          "dosage");
                    PackageType packageType = getPackage(drugForm,
                                                         pharmacyElement);

                    pharmacy.setCertificate(certificate);
                    pharmacy.setDosage(Double.parseDouble(dosage));
                    pharmacy.setType(packageType);

                    drugForm.addPharmacy(pharmacy);
                }
            }
        }

        return drug;
    }

    /**
     * Gets text content of the provided element.
     *
     * @param element     the provided element.
     * @param elementName the name of the element.
     * @return the text content of the element.
     */
    private String getElementTextContent(final Element element,
            final String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    /**
     * Returns the package type for provided drug form.
     *
     * @param form the value of form.
     * @param e    provided pharmacy element.
     * @return the package type entity.
     */
    private PackageType getPackage(final Form form, final Element e) {
        PackageType packageType;
        String packageValue =
                getElementTextContent(e, "package_type")
                        .toUpperCase().replace(" ", "_");
        switch (form.getType()) {
            case TABLETS:
                packageType = getTabletPackage(e, packageValue);
                break;
            case DROPS:
                packageType = getLiquidPackage(e, packageValue);
                break;
            case POWDER:
                packageType = getPowderPackage(e, packageValue);
                break;
            case OINTMENT:
                packageType = getOintmentPackage(e, packageValue);
                break;
            default:
                throw new IllegalStateException(
                        "Unexpected value: " + form.getType());
        }
        double price = Double.parseDouble(getElementTextContent(e, "price"));
        packageType.setPrice(price);
        return packageType;
    }

    /**
     * Returns the tablet package type entity.
     *
     * @param e     the provided element.
     * @param value the package type value.
     * @return the package type entity
     */
    private PackageType getTabletPackage(final Element e, final String value) {

        TabletsPackage tabletsPackage = new TabletsPackage();

        Pack pack = Pack.valueOf(value);
        int quantity = Integer.parseInt(getElementTextContent(e, "quantity"));

        tabletsPackage.setType(pack);
        tabletsPackage.setQuantity(quantity);

        return tabletsPackage;
    }

    /**
     * Returns the liquid package type entity.
     *
     * @param e     the provided element.
     * @param value the package type value.
     * @return the package type entity
     */
    private PackageType getLiquidPackage(final Element e, final String value) {
        LiquidPackage liquidPackage = new LiquidPackage();

        Liquid liquid = Liquid.valueOf(value);
        int volume = Integer.parseInt(getElementTextContent(e, "volume"));

        liquidPackage.setType(liquid);
        liquidPackage.setVolume(volume);

        return liquidPackage;
    }

    /**
     * Returns the powder package type entity.
     *
     * @param e     the provided element.
     * @param value the package type value.
     * @return the package type entity
     */
    private PackageType getPowderPackage(final Element e, final String value) {
        PowderPackage powderPackage = new PowderPackage();

        Powder powder = Powder.valueOf(value);
        int weight = Integer.parseInt(getElementTextContent(e, "weight"));

        powderPackage.setType(powder);
        powderPackage.setWeight(weight);

        return powderPackage;
    }

    /**
     * Returns the tablet ointment type entity.
     *
     * @param e     the provided element.
     * @param value the package type value.
     * @return the package type entity
     */
    private PackageType getOintmentPackage(final Element e,
            final String value) {
        OintmentPackage ointmentPackage = new OintmentPackage();

        Ointment ointment = Ointment.valueOf(value);
        int weight = Integer.parseInt(getElementTextContent(e, "weight"));
        int concentration = Integer.parseInt(
                getElementTextContent(e, "concentration"));

        ointmentPackage.setType(ointment);
        ointmentPackage.setWeight(weight);
        ointmentPackage.setConcentration(concentration);

        return ointmentPackage;
    }

    /**
     * Gets certificate data from current pharmacy element.
     *
     * @param e the provided pharmacy element.
     * @return the certificate entity.
     */
    private Certificate getCertificate(final Element e) {
        Certificate certificate = new Certificate();

        String number = getElementTextContent(e, "number");
        String issueDate = getElementTextContent(e, "issue_date");
        String expirationDate = getElementTextContent(e, "expiration_date");
        String registration = getElementTextContent(e, "registration");

        certificate.setNumber(Long.parseLong(number));
        certificate.setIssueDate(LocalDate.parse(issueDate));
        certificate.setExpirationDate(LocalDate.parse(expirationDate));
        certificate.setRegistration(registration);

        return certificate;
    }

    /**
     * Gets analogs of the current drug element.
     *
     * @param element the provided element.
     * @param drug    the current drug value.
     */
    private void getAnalogs(final Element element, final Drug drug) {
        NodeList analogs = element.getElementsByTagName("analog");

        for (int i = 0; i < analogs.getLength(); i++) {
            Element analog = (Element) analogs.item(i);
            String value = analog.getTextContent();
            drug.addAnalog(value);
        }
    }
}
