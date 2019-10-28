package by.gartsmanovich.webparsing.repository.builder;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDrugBuilder {
    private Set<Drug> drugs;

    public AbstractDrugBuilder() {
        this.drugs = new HashSet<>();
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }

    public abstract void buildSetDrugs(String filename) throws
            RepositoryException;
}
