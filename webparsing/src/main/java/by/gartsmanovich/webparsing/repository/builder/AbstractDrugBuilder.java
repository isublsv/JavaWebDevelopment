package by.gartsmanovich.webparsing.repository.builder;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class defines common interface for all builders in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public abstract class AbstractDrugBuilder {

    /**
     * List of drug entities.
     */
    private List<Drug> drugs;

    /**
     * The default constructor.
     */
    protected AbstractDrugBuilder() {
        this.drugs = new ArrayList<>();
    }

    /**
     * Sets drugs.
     *
     * @param drugsValue value of drugs.
     */
    protected void setDrugs(final List<Drug> drugsValue) {
        drugs = drugsValue;
    }

    /**
     * Gets drugs.
     *
     * @return value of drugs.
     */
    public List<Drug> getDrugs() {
        return drugs;
    }

    /**
     * Builds the set of drug entities using DOM, SAX or StAX parsers.
     *
     * @param filename the provided xml-document.
     * @throws RepositoryException if error happens during execution.
     */
    public abstract void buildSetDrugs(String filename) throws
            RepositoryException;
}
