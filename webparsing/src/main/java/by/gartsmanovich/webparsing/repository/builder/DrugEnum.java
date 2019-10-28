package by.gartsmanovich.webparsing.repository.builder;

public enum DrugEnum {
    DRUG("drug"),
    NAME("name"),
    GROUP("group"),
    ANALOGS("analogs"),
    VERSIONS("versions"),
    ID("id"),
    TABLETS("tablets"),
    DROPS("drops"),
    POWDER("powder"),
    OINTMENT("ointment"),
    PHARMACY("pharmacy"),
    CERTIFICATE("certificate"),
    NUMBER("number"),
    ISSUE_DATE("issue_date"),
    EXPIRATION_DATE("expiration_date"),
    REGISTRATION("registration"),
    DOSAGE("dosage"),
    PACKAGE("package"),
    PRICE("price"),
    PACKAGE_TYPE("package_type"),
    QUANTITY("quantity"),
    VOLUME("volume"),
    WEIGHT("weight"),
    CONCENTRATION("concentration");

    private String value;

    DrugEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
