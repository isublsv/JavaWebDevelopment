package by.gartsmanovich.webparsing.repository.builder.stax;

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
import by.gartsmanovich.webparsing.repository.builder.DrugEnum;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * The StAX parser realisation.
 *
 * @author Dmitry Gartsmanovich
 */
public class DrugStaXBuilder extends AbstractDrugBuilder {

    /**
     * Defines an abstract implementation of a factory for getting streams.
     */
    private XMLInputFactory factory;

    /**
     * The default constructor.
     */
    public DrugStaXBuilder() {
        factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                            false);
    }

    /**
     * Creates the list of the drug objects.
     *
     * @param filename the provided path to the xml-document.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public void buildSetDrugs(final String filename) throws
            RepositoryException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(
                new File(filename))) {
            reader = factory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (DrugEnum.valueOf(name.toUpperCase())
                        == DrugEnum.DRUG) {
                        Drug drug = buildDrug(reader);
                        getDrugs().add(drug);
                    }
                }
            }
        } catch (IOException e) {
            throw new RepositoryException("File error or I/O error!", e);
        } catch (XMLStreamException e) {
            throw new RepositoryException("Parser error!", e);
        }
    }

    /**
     * Creates the drug instance using data from the xml-document.
     *
     * @param reader an interface allows forward, read-only access to XML.
     * @return the drug instance.
     * @throws XMLStreamException if error happens during processing.
     */
    private Drug buildDrug(final XMLStreamReader reader) throws
            XMLStreamException {

        Drug drug = new Drug();
        String id = reader.getAttributeValue(null, DrugEnum.ID.getValue())
                          .replace("drug", "");
        drug.setId(Long.parseLong(id));

        String name;

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName().toUpperCase();
                    Form form;
                    Optional<String> value;
                    switch (DrugEnum.valueOf(name)) {
                        case NAME:
                            value = getXMLText(reader);
                            value.ifPresent(drug::setName);
                            break;
                        case GROUP:
                            value = getXMLText(reader);
                            value.ifPresent(s -> drug.setGroup(
                                    Group.valueOf(s.toUpperCase())));
                            break;
                        case ANALOG:
                            value = getXMLText(reader);
                            value.ifPresent(drug::addAnalog);
                            break;
                        case TABLETS:
                            form = getVersion(reader, FormType.TABLETS);
                            drug.addVersion(form);
                            break;
                        case DROPS:
                            form = getVersion(reader, FormType.DROPS);
                            drug.addVersion(form);
                            break;
                        case POWDER:
                            form = getVersion(reader, FormType.POWDER);
                            drug.addVersion(form);
                            break;
                        case OINTMENT:
                            form = getVersion(reader, FormType.OINTMENT);
                            drug.addVersion(form);
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().toUpperCase();
                    if (DrugEnum.valueOf(name) == DrugEnum.DRUG) {
                        return drug;
                    }
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element");
    }

    /**
     * Returns the form version value of the current drug.
     *
     * @param reader   an interface allows forward, read-only access to XML.
     * @param formType the type version of the drug.
     * @return the form version value.
     * @throws XMLStreamException if error happens during processing.
     */
    private Form getVersion(final XMLStreamReader reader,
            final FormType formType) throws XMLStreamException {

        Form form = new Form(formType);

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name) == DrugEnum.PHARMACY) {
                    Pharmacy pharmacy = getPharmacy(reader, formType);
                    form.addPharmacy(pharmacy);
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name).name().equals(formType.name())) {
                    return form;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag \"versions\"");
    }

    /**
     * Returns the pharmacy value of the current drug form type.
     *
     * @param reader   an interface allows forward, read-only access to XML.
     * @param formType the type version of the drug.
     * @return the pharmacy value.
     * @throws XMLStreamException if error happens during processing.
     */
    private Pharmacy getPharmacy(final XMLStreamReader reader,
            final FormType formType) throws XMLStreamException {

        Pharmacy pharmacy = new Pharmacy();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                Optional<String> value;
                switch (DrugEnum.valueOf(name)) {
                    case CERTIFICATE:
                        Certificate certificate = getCertificate(reader);
                        pharmacy.setCertificate(certificate);
                        break;
                    case DOSAGE:
                        value = getXMLText(reader);
                        value.ifPresent(d -> pharmacy.setDosage(
                                Double.parseDouble(d)));
                        break;
                    case PACKAGE:
                        PackageType packageType = getPackage(reader, formType);
                        pharmacy.setType(packageType);
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name) == DrugEnum.PHARMACY) {
                    return pharmacy;
                }
            }
        }

        throw new XMLStreamException("Unknown element in tag \"pharmacy\"");
    }

    /**
     * Returns the certificate value of the current drug form type.
     *
     * @param reader an interface allows forward, read-only access to XML.
     * @return the certificate value.
     * @throws XMLStreamException if error happens during processing.
     */
    private Certificate getCertificate(final XMLStreamReader reader) throws
            XMLStreamException {
        Certificate certificate = new Certificate();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                Optional<String> value;
                switch (DrugEnum.valueOf(name)) {
                    case NUMBER:
                        value = getXMLText(reader);
                        value.ifPresent(i -> certificate.setNumber(
                                Integer.parseInt(i)));
                        break;
                    case ISSUE_DATE:
                        value = getXMLText(reader);
                        value.ifPresent(d -> certificate.setIssueDate(
                                LocalDate.parse(d)));
                        break;
                    case EXPIRATION_DATE:
                        value = getXMLText(reader);
                        value.ifPresent(d -> certificate.setExpirationDate(
                                LocalDate.parse(d)));
                        break;
                    case REGISTRATION:
                        value = getXMLText(reader);
                        value.ifPresent(certificate::setRegistration);
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name) == DrugEnum.CERTIFICATE) {
                    return certificate;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag \"certificate\"");
    }

    /**
     * @param reader   an interface allows forward, read-only access to XML.
     * @param formType the type version of the drug.
     * @return the package type of the current drug value.
     * @throws XMLStreamException if error happens during processing.
     */
    private PackageType getPackage(final XMLStreamReader reader,
            final FormType formType) throws XMLStreamException {

        switch (formType) {
            case TABLETS:
                return getTabletPackage(reader);
            case DROPS:
                return getLiquidPackage(reader);
            case POWDER:
                return getPowderPackage(reader);
            default:
                return getOintmentPackage(reader);
        }
    }

    /**
     * Returns the tablet package type entity.
     *
     * @param reader an interface allows forward, read-only access to XML.
     * @return the tablet package type entity.
     * @throws XMLStreamException if error happens during processing.
     */
    private TabletsPackage getTabletPackage(
            final XMLStreamReader reader) throws XMLStreamException {
        TabletsPackage tabletsPackage = new TabletsPackage();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                Optional<String> value;
                switch (DrugEnum.valueOf(name)) {
                    case PRICE:
                        value = getXMLText(reader);
                        value.ifPresent(d -> tabletsPackage.setPrice(
                                Double.parseDouble(d)));
                        break;
                    case PACKAGE_TYPE:
                        value = getXMLText(reader);
                        value.ifPresent(p -> tabletsPackage.setType(
                                Pack.valueOf(p.toUpperCase()
                                              .replace(" ", "_"))));
                        break;
                    case QUANTITY:
                        value = getXMLText(reader);
                        value.ifPresent(i -> tabletsPackage.setQuantity(
                                Integer.parseInt(i)));
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name) == DrugEnum.PACKAGE) {
                    return tabletsPackage;
                }
            }
        }
        throw new XMLStreamException(
                "Unknown element in tag \"tablet package\"");
    }

    /**
     * Returns the liquid package type entity.
     *
     * @param reader an interface allows forward, read-only access to XML.
     * @return the liquid package type entity.
     * @throws XMLStreamException if error happens during processing.
     */
    private PackageType getLiquidPackage(final XMLStreamReader reader) throws
            XMLStreamException {
        LiquidPackage liquidPackage = new LiquidPackage();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                Optional<String> value;
                switch (DrugEnum.valueOf(name)) {
                    case PRICE:
                        value = getXMLText(reader);
                        value.ifPresent(d -> liquidPackage.setPrice(
                                Double.parseDouble(d)));
                        break;
                    case PACKAGE_TYPE:
                        value = getXMLText(reader);
                        value.ifPresent(p -> liquidPackage.setType(
                                Liquid.valueOf(p.toUpperCase()
                                                .replace(" ", "_"))));
                        break;
                    case VOLUME:
                        value = getXMLText(reader);
                        value.ifPresent(i -> liquidPackage.setVolume(
                                Integer.parseInt(i)));
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name) == DrugEnum.PACKAGE) {
                    return liquidPackage;
                }
            }
        }
        throw new XMLStreamException(
                "Unknown element in tag \"liquid package\"");
    }

    /**
     * Returns the powder package type entity.
     *
     * @param reader an interface allows forward, read-only access to XML.
     * @return the powder package type entity.
     * @throws XMLStreamException if error happens during processing.
     */
    private PackageType getPowderPackage(final XMLStreamReader reader) throws
            XMLStreamException {
        PowderPackage powderPackage = new PowderPackage();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                Optional<String> value;
                switch (DrugEnum.valueOf(name)) {
                    case PRICE:
                        value = getXMLText(reader);
                        value.ifPresent(d -> powderPackage.setPrice(
                                Double.parseDouble(d)));
                        break;
                    case PACKAGE_TYPE:
                        value = getXMLText(reader);
                        value.ifPresent(p -> powderPackage.setType(
                                Powder.valueOf(p.toUpperCase()
                                                .replace(" ", "_"))));
                        break;
                    case WEIGHT:
                        value = getXMLText(reader);
                        value.ifPresent(i -> powderPackage.setWeight(
                                Integer.parseInt(i)));
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name) == DrugEnum.PACKAGE) {
                    return powderPackage;
                }
            }
        }
        throw new XMLStreamException(
                "Unknown element in tag \"powder package\"");
    }

    /**
     * Returns the ointment package type entity.
     *
     * @param reader an interface allows forward, read-only access to XML.
     * @return the ointment package type entity.
     * @throws XMLStreamException if error happens during processing.
     */
    private PackageType getOintmentPackage(final XMLStreamReader reader) throws
            XMLStreamException {
        OintmentPackage ointmentPackage = new OintmentPackage();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                Optional<String> value;
                switch (DrugEnum.valueOf(name)) {
                    case PRICE:
                        value = getXMLText(reader);
                        value.ifPresent(d -> ointmentPackage.setPrice(
                                Double.parseDouble(d)));
                        break;
                    case PACKAGE_TYPE:
                        value = getXMLText(reader);
                        value.ifPresent(p -> ointmentPackage.setType(
                                Ointment.valueOf(p.toUpperCase()
                                                  .replace(" ", "_"))));
                        break;
                    case WEIGHT:
                        value = getXMLText(reader);
                        value.ifPresent(i -> ointmentPackage.setWeight(
                                Integer.parseInt(i)));
                        break;
                    case CONCENTRATION:
                        value = getXMLText(reader);
                        value.ifPresent(i -> ointmentPackage.setConcentration(
                                Integer.parseInt(i)));
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName().toUpperCase();
                if (DrugEnum.valueOf(name) == DrugEnum.PACKAGE) {
                    return ointmentPackage;
                }
            }
        }
        throw new XMLStreamException(
                "Unknown element in tag \"ointment package\"");
    }

    /**
     * Returns an optional text content of the element if present.
     *
     * @param reader an interface allows forward, read-only access to XML.
     * @return an optional text content of the element.
     * @throws XMLStreamException if error happens during processing.
     */
    private Optional<String> getXMLText(final XMLStreamReader reader) throws
            XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return Optional.ofNullable(text);
    }
}
