package by.gartsmanovich.javawebdev.composite.repository.impl;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.datahandler.DataReader;
import by.gartsmanovich.javawebdev.composite.datahandler.DataWriter;
import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .DataHandlerException;
import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .ParseException;
import by.gartsmanovich.javawebdev.composite.datahandler.factory
        .DataHandlerFactory;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.AbstractParser;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.impl
        .LexemeParser;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.impl
        .ParagraphParser;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.impl
        .SentenceParser;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.impl
        .SymbolParser;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.impl.WordParser;
import by.gartsmanovich.javawebdev.composite.repository.Repository;
import by.gartsmanovich.javawebdev.composite.repository.exception
        .RepositoryException;
import by.gartsmanovich.javawebdev.composite.repository.specification
        .Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * The repository interface implementation. Used to processing queries from
 * Services layer and applying to the composite instance.
 *
 * @author Dmitry Gartsmanovich
 */
public class CompositeRepository implements Repository<Component> {

    /**
     * The storage that contains the data.
     */
    private Component component;

    /**
     * Creates the composite instance.
     *
     * @param path      the path to storage file.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public void createComposite(final String path) throws RepositoryException {

        DataHandlerFactory factory = DataHandlerFactory.getInstance();
        DataReader reader = factory.getDataReader();

        AbstractParser paragraphParser = new ParagraphParser();
        AbstractParser sentenceParser = new SentenceParser();
        AbstractParser lexemeParser = new LexemeParser();
        AbstractParser wordParser = new WordParser();
        AbstractParser symbolParser = new SymbolParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        try {
            String data = reader.readFile(path);

            component = paragraphParser.parse(data);

        } catch (DataHandlerException | ParseException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    /**
     * Determines the business rules that applies to the matrix.
     *
     * @param specification the concrete specification that query different
     *                      types of actions.
     * @return the string that contains result of query execution.
     * @throws RepositoryException if error happens during execution.
     */
    @Override
    public String query(final Specification<Component> specification) throws
            RepositoryException {
        List<Component> components = new ArrayList<>();
        return specification.specified(components);
    }

    /**
     * Saves the last result obtained after executing the any method.
     *
     * @param path the path to storage file.
     * @throws RepositoryException if error happens during execution.
     */
    public void saveComposite(final String path) throws RepositoryException {
        try {
            if (component != null) {
                DataHandlerFactory factory = DataHandlerFactory.getInstance();
                DataWriter dataWriter = factory.getDataWriter();

                String fullText = component.collect();
                dataWriter.writeFile(fullText, path);
            } else {
                throw new RepositoryException("The composite is not exist!"
                                              + " Run any method first!");
            }
        } catch (DataHandlerException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
