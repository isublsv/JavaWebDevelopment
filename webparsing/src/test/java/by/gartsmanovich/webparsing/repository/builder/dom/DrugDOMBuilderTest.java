package by.gartsmanovich.webparsing.repository.builder.dom;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.repository.builder.AbstractDrugBuilder;
import by.gartsmanovich.webparsing.repository.exception.RepositoryException;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.*;

public class DrugDOMBuilderTest {

    private AbstractDrugBuilder builder;
    private String path = "D:\\WORK\\Dropbox\\Java\\JavaWebDevelopment" 
                          + "\\webparsing\\src\\main\\resources\\data" 
                          + "\\Medicines.xml";
    
    @Test
    public void testBuildSetDrugs() throws RepositoryException {
        builder = new DrugDOMBuilder();
        builder.buildSetDrugs(path);
        Set<Drug> drugs = builder.getDrugs();
        System.out.println(drugs);
    }
}
