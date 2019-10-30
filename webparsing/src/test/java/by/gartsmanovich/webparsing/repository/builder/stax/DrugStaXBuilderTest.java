package by.gartsmanovich.webparsing.repository.builder.stax;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import static org.testng.Assert.*;

public class DrugStaXBuilderTest {

    @Test
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
