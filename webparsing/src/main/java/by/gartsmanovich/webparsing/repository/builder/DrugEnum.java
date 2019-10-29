package by.gartsmanovich.webparsing.repository.builder;

/**
 * The enum of all elements that xml-documents can contain.
 *
 * @author Dmitry Gartsmanovich
 */
public enum DrugEnum {

    /**
     * The singleton instance for the medicines instance.
     * This has the numeric value of {@code 0}.
     */
    MEDICINES("medicines"),

    /**
     * The singleton instance for the drug instance.
     * This has the numeric value of {@code 1}.
     */
    DRUG("drug"),

    /**
     * The singleton instance for the id instance.
     * This has the numeric value of {@code 2}.
     */
    ID("id"),

    /**
     * The singleton instance for the analogs instance.
     * This has the numeric value of {@code 3}.
     */
    ANALOGS("analogs"),

    /**
     * The singleton instance for the versions instance.
     * This has the numeric value of {@code 4}.
     */
    VERSIONS("versions"),

    /**
     * The singleton instance for the tablets instance.
     * This has the numeric value of {@code 5}.
     */
    TABLETS("tablets"),

    /**
     * The singleton instance for the drops instance.
     * This has the numeric value of {@code 6}.
     */
    DROPS("drops"),

    /**
     * The singleton instance for the powder instance.
     * This has the numeric value of {@code 7}.
     */
    POWDER("powder"),

    /**
     * The singleton instance for the ointment instance.
     * This has the numeric value of {@code 8}.
     */
    OINTMENT("ointment"),

    /**
     * The singleton instance for the pharmacy instance.
     * This has the numeric value of {@code 9}.
     */
    PHARMACY("pharmacy"),

    /**
     * The singleton instance for the certificate instance.
     * This has the numeric value of {@code 10}.
     */
    CERTIFICATE("certificate"),

    /**
     * The singleton instance for the package instance.
     * This has the numeric value of {@code 11}.
     */
    PACKAGE("package"),

    /**
     * The singleton instance for the name instance.
     * This has the numeric value of {@code 12}.
     */
    NAME("name"),

    /**
     * The singleton instance for the group instance.
     * This has the numeric value of {@code 13}.
     */
    GROUP("group"),

    /**
     * The singleton instance for the analog instance.
     * This has the numeric value of {@code 14}.
     */
    ANALOG("analog"),

    /**
     * The singleton instance for the number instance.
     * This has the numeric value of {@code 15}.
     */
    NUMBER("number"),

    /**
     * The singleton instance for the issue date instance.
     * This has the numeric value of {@code 16}.
     */
    ISSUE_DATE("issue_date"),

    /**
     * The singleton instance for the expiration date instance.
     * This has the numeric value of {@code 17}.
     */
    EXPIRATION_DATE("expiration_date"),

    /**
     * The singleton instance for the registration instance.
     * This has the numeric value of {@code 18}.
     */
    REGISTRATION("registration"),

    /**
     * The singleton instance for the dosage instance.
     * This has the numeric value of {@code 19}.
     */
    DOSAGE("dosage"),

    /**
     * The singleton instance for the price instance.
     * This has the numeric value of {@code 20}.
     */
    PRICE("price"),

    /**
     * The singleton instance for the package type instance.
     * This has the numeric value of {@code 21}.
     */
    PACKAGE_TYPE("package_type"),

    /**
     * The singleton instance for the quantity instance.
     * This has the numeric value of {@code 22}.
     */
    QUANTITY("quantity"),

    /**
     * The singleton instance for the volume instance.
     * This has the numeric value of {@code 23}.
     */
    VOLUME("volume"),

    /**
     * The singleton instance for the weight instance.
     * This has the numeric value of {@code 24}.
     */
    WEIGHT("weight"),

    /**
     * The singleton instance for the concentration instance.
     * This has the numeric value of {@code 25}.
     */
    CONCENTRATION("concentration");

    /**
     * The value of the element.
     */
    private String value;

    /**
     * Constructs an instance with specific value.
     *
     * @param val the value of the element.
     */
    DrugEnum(final String val) {
        this.value = val;
    }

    /**
     * Gets the value.
     *
     * @return the value of the element.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
