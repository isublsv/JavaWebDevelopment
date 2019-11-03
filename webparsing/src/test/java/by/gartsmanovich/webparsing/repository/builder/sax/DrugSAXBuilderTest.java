package by.gartsmanovich.webparsing.repository.builder.sax;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public class DrugSAXBuilderTest {

    @Test(description = "Runs the SAX builder with provided xml-document")
    public void testSAXBuildSetDrugs() throws RepositoryException,
            URISyntaxException {
        URI uri = ClassLoader.getSystemResource("data/Medicines.xml").toURI();
        String pathToRead = Paths.get(uri).toString();

        AbstractDrugBuilder builder = new DrugSAXBuilder();
        builder.buildSetDrugs(pathToRead);
        List<Drug> drugs = builder.getDrugs();
        System.out.println(drugs);
    }
}
