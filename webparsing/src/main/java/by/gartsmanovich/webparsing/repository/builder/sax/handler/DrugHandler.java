package by.gartsmanovich.webparsing.repository.builder.sax.handler;

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
import by.gartsmanovich.webparsing.repository.builder.DrugEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * The realisation of default base class for SAX2 event handlers.
 *
 * @author Dmitry Gartsmanovich
 */
public class DrugHandler extends DefaultHandler {

    /**
     * The set of drug entities.
     */
    private List<Drug> drugs;

    /**
     * The current value of the drug entity.
     */
    private Drug currentDrug;

    /**
     * The current value of the form entity.
     */
    private Form currentForm;

    /**
     * The current value of the pharmacy entity.
     */
    private Pharmacy currentPharmacy;

    /**
     * The current value of the certificate entity.
     */
    private Certificate currentCertificate;

    /**
     * The current value of the package entity.
     */
    private PackageType currentPackage;

    /**
     * The set of elements with text content.
     */
    private EnumSet<DrugEnum> withText;

    /**
     * The enum of element type that the xml-document contains.
     */
    private DrugEnum currentEnum;

    /**
     * The default constructor.
     */
    public DrugHandler() {
        this.drugs = new ArrayList<>();
        this.withText = EnumSet.range(DrugEnum.NAME, DrugEnum.CONCENTRATION);
    }

    /**
     * Gets the set of drugs.
     *
     * @return the set of drugs.
     */
    public List<Drug> getDrugs() {
        return drugs;
    }

    /**
     * Receive notification of the start of an element.
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qname     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * @param attrs     The attributes attached to the element.  If
     *                  there are no attributes, it shall be an empty
     *                  Attributes object.
     */
    @Override
    public void startElement(final String uri, final String localName,
            final String qname, final Attributes attrs) {

        switch (qname) {
            case "drug":
                currentDrug = new Drug();
                String id = attrs.getValue(0).replace("drug", "");
                currentDrug.setId(Long.parseLong(id));
                break;
            case "tablets":
                currentForm = new Form(FormType.TABLETS);
                break;
            case "drops":
                currentForm = new Form(FormType.DROPS);
                break;
            case "powder":
                currentForm = new Form(FormType.POWDER);
                break;
            case "ointment":
                currentForm = new Form(FormType.OINTMENT);
                break;
            case "pharmacy":
                currentPharmacy = new Pharmacy();
                break;
            case "certificate":
                currentCertificate = new Certificate();
                break;
            case "package":
                currentPackage = getCurrentPackageType();
                break;
            default:
                DrugEnum temp = DrugEnum.valueOf(qname.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
                break;
        }
    }

    /**
     * Receive notification of the end of an element.
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     */
    @Override
    public void endElement(final String uri, final String localName,
            final String qName) {
        switch (qName) {
            case "drug":
                drugs.add(currentDrug);
                break;
            case "tablets":
            case "drops":
            case "powder":
            case "ointment":
                currentDrug.addVersion(currentForm);
                break;
            case "pharmacy":
                currentForm.addPharmacy(currentPharmacy);
                break;
            case "certificate":
                currentPharmacy.setCertificate(currentCertificate);
                break;
            case "package":
                currentPharmacy.setType(currentPackage);
                break;
            default:
                break;
        }
    }

    /**
     * Receive notification of character data inside an element.
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    @Override
    public void characters(final char[] ch, final int start,
            final int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    currentDrug.setName(s);
                    break;
                case GROUP:
                    currentDrug.setGroup(Group.valueOf(s.toUpperCase()));
                    break;
                case ANALOG:
                    currentDrug.addAnalog(s);
                    break;
                case NUMBER:
                    currentCertificate.setNumber(Integer.parseInt(s));
                    break;
                case ISSUE_DATE:
                    currentCertificate.setIssueDate(LocalDate.parse(s));
                    break;
                case EXPIRATION_DATE:
                    currentCertificate.setExpirationDate(LocalDate.parse(s));
                    break;
                case REGISTRATION:
                    currentCertificate.setRegistration(s);
                    break;
                case DOSAGE:
                    currentPharmacy.setDosage(Double.parseDouble(s));
                    break;
                case PRICE:
                    currentPackage.setPrice(Double.parseDouble(s));
                    break;
                case PACKAGE_TYPE:
                    setPackageType(s);
                    break;
                case QUANTITY:
                    TabletsPackage tabletsPackage =
                            (TabletsPackage) currentPackage;
                    tabletsPackage.setQuantity(Integer.parseInt(s));
                    break;
                case VOLUME:
                    LiquidPackage liquidPackage =
                            (LiquidPackage) currentPackage;
                    liquidPackage.setVolume(Integer.parseInt(s));
                    break;
                case WEIGHT:
                    if (currentForm.getType() == FormType.POWDER) {
                        PowderPackage powderPackage =
                                (PowderPackage) currentPackage;
                        powderPackage.setWeight(Integer.parseInt(s));
                    } else {
                        OintmentPackage ointmentPackage =
                                (OintmentPackage) currentPackage;
                        ointmentPackage.setWeight(Integer.parseInt(s));
                    }
                    break;
                case CONCENTRATION:
                    OintmentPackage ointmentPackage =
                            (OintmentPackage) currentPackage;
                    ointmentPackage.setConcentration(Integer.parseInt(s));
                    break;
                default:
                    break;
            }
        }
        currentEnum = null;
    }

    /**
     * Defines and sets the form type of the current package.
     *
     * @param value the value of the package form.
     */
    private void setPackageType(final String value) {
        String current = value.toUpperCase().replace(" ", "_");
        switch (currentForm.getType()) {
            case TABLETS:
                TabletsPackage tabletsPackage =
                        (TabletsPackage) currentPackage;
                tabletsPackage.setType(Pack.valueOf(current));
                break;
            case DROPS:
                LiquidPackage liquidPackage = (LiquidPackage) currentPackage;
                liquidPackage.setType(Liquid.valueOf(current));
                break;
            case POWDER:
                PowderPackage powderPackage = (PowderPackage) currentPackage;
                powderPackage.setType(Powder.valueOf(current));
                break;
            default:
                OintmentPackage ointmentPackage =
                        (OintmentPackage) currentPackage;
                ointmentPackage.setType(Ointment.valueOf(current));
        }
    }

    /**
     * Returns the type of current "package" element.
     *
     * @return the package type value.
     */
    private PackageType getCurrentPackageType() {
        switch (currentForm.getType()) {
            case TABLETS:
                return new TabletsPackage();
            case DROPS:
                return new LiquidPackage();
            case POWDER:
                return new PowderPackage();
            default:
                return new OintmentPackage();
        }
    }
}
