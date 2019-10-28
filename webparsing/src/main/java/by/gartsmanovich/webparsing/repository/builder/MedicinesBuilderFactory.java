package by.gartsmanovich.webparsing.repository.builder;

import by.gartsmanovich.webparsing.repository.builder.dom.DrugDOMBuilder;
import by.gartsmanovich.webparsing.repository.builder.sax.DrugSAXBuilder;
import by.gartsmanovich.webparsing.repository.builder.stax.DrugStaXBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;

public class MedicinesBuilderFactory {
    public AbstractDrugBuilder createMedicinesBuilder(
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

    private enum TypeParser {
        SAX, STAX, DOM
    }
}
