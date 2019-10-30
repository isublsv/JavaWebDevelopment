package by.gartsmanovich.webparsing.repository.specification.impl;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.builder.DrugBuilderFactory;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import by.gartsmanovich.webparsing.repository.specification.Specification;

import java.util.List;

/**
 * The specification interface realisation used to build the list of entities.
 *
 * @author Dmitry Gartsmanovich
 */
public class BuilderSpecification implements Specification<Drug> {

    /**
     * The key defines the builder invocation.
     */
    private String key;

    /**
     * The path to provided xml-document.
     */
    private String path;

    /**
     * The Drug Builder Factory class-method.
     */
    private DrugBuilderFactory factory;

    /**
     * Constructs the specification instance with provided key and path values.
     *
     * @param keyValue the value of the key.
     * @param pathValue the value of the path.
     */
    public BuilderSpecification(final String keyValue,
            final String pathValue) {
        this.key = keyValue;
        this.path = pathValue;
        factory = new DrugBuilderFactory();
    }

    /**
     * Applies specified criteria to the provided element.
     *
     * @return the list of elements that correspond to specified criteria.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public List<Drug> specified() throws  RepositoryException {
        AbstractDrugBuilder builder = factory.createDrugBuilder(key);
        builder.buildSetDrugs(path);
        return builder.getDrugs();
    }
}
