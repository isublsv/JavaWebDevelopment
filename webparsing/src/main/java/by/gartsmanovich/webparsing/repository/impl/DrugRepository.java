package by.gartsmanovich.webparsing.repository.impl;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.Repository;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import by.gartsmanovich.webparsing.repository.specification.Specification;

import java.util.List;

/**
 * The repository interface implementation. Used to processing queries from
 * Services layer and applying to the Specification instance.
 *
 * @author Dmitry Gartsmanovich
 */
public class DrugRepository implements Repository<Drug> {

    /**
     * Determines the business rules that applies to the medicines list.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the list that contains result of query execution.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public List<Drug> query(final Specification<Drug> specification) throws
            RepositoryException {

        return specification.specified();
    }
}
