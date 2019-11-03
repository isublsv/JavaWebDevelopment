package by.gartsmanovich.webparsing.repository.builder.stax;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public class DrugStaXBuilderTest {

    @Test(description = "Runs the StAX builder with provided xml-document")
    public void testStAXBuildSetDrugs() throws RepositoryException,
            URISyntaxException {
        URI uri = ClassLoader.getSystemResource("data/Medicines.xml").toURI();
        String pathToRead = Paths.get(uri).toString();

        AbstractDrugBuilder builder = new DrugStaXBuilder();
        builder.buildSetDrugs(pathToRead);
        List<Drug> drugs = builder.getDrugs();
        System.out.println(drugs);
    }
}
