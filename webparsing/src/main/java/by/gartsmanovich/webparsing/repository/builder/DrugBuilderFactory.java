package by.gartsmanovich.webparsing.repository.builder;

import by.gartsmanovich.webparsing.repository.builder.dom.DrugDOMBuilder;
import by.gartsmanovich.webparsing.repository.builder.sax.DrugSAXBuilder;
import by.gartsmanovich.webparsing.repository.builder.stax.DrugStaXBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;

/**
 * Class factory-method. Used to create different type of builders.
 *
 * @author Dmitry Gartsmanovich
 */
public class DrugBuilderFactory {

    /**
     * Factory method returns the xml-parser realisation.
     *
     * @param typeParser the type of the parser.
     * @return the builder instance.
     * @throws RepositoryException if error happens during execution.
     */
    public AbstractDrugBuilder createDrugBuilder(
            final String typeParser) throws RepositoryException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new DrugDOMBuilder();
            case SAX:
                return new DrugSAXBuilder();
            case STAX:
                return new DrugStaXBuilder();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
    }

    /**
     * Enum-class represents the form types of builder that application
     * supports.
     */
    private enum TypeParser {

        /**
         * The singleton instance for the SAX type builder instance.
         * This has the numeric value of {@code 0}.
         */
        SAX,

        /**
         * The singleton instance for the StAX type builder instance.
         * This has the numeric value of {@code 1}.
         */
        STAX,

        /**
         * The singleton instance for the DOM type builder instance.
         * This has the numeric value of {@code 2}.
         */
        DOM
    }
}
