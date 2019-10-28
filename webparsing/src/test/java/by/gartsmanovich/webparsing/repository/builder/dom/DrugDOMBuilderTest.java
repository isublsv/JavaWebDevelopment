package by.gartsmanovich.webparsing.repository.builder.dom;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Set;

import static org.testng.Assert.*;

public class DrugDOMBuilderTest {

    private AbstractDrugBuilder builder;
/*    private String path = "D:\\Projects\\JavaWebDevelopment\\webparsing\\src" +
            "\\main\\resources\\data\\Medicines.xml";*/
    
    @Test
    public void testBuildSetDrugs() throws RepositoryException, URISyntaxException {
        URI uri = ClassLoader.getSystemResource("data/Medicines.xml").toURI();
        String pathToRead = Paths.get(uri).toString();

        builder = new DrugDOMBuilder();
        builder.buildSetDrugs(pathToRead);
        Set<Drug> drugs = builder.getDrugs();
        System.out.println(drugs);
    }
}
