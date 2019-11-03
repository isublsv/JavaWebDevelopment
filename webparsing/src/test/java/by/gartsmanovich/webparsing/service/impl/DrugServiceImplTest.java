package by.gartsmanovich.webparsing.service.impl;

import by.gartsmanovich.webparsing.service.DrugService;
import by.gartsmanovich.webparsing.service.exception.ServiceException;
import by.gartsmanovich.webparsing.service.factory.ServiceFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class DrugServiceImplTest {

    private DrugService drugService;
    private String pathToXML;
    private String pathToXSD;

    @BeforeClass
    public void setUp() throws URISyntaxException {
        ServiceFactory factory = ServiceFactory.getInstance();
        drugService = factory.getDrugService();

        URI xml = ClassLoader.getSystemResource("data/Medicines.xml").toURI();
        pathToXML = Paths.get(xml).toString();

        URI xsd = ClassLoader.getSystemResource("data/Medicines.xsd").toURI();
        pathToXSD = Paths.get(xsd).toString();
    }

    @DataProvider(name = "DataForExecuteBuilderException")
    public Object[][] getDataForExecuteBuilderException() {
        return new Object[][]{
                {"", pathToXML, pathToXSD},
                {"D", pathToXML, pathToXSD},
                {"DOM", "", pathToXSD},
                {"DOM", pathToXML, ""},
        };
    }

    @Test(dataProvider = "DataForExecuteBuilderException", description = 
            "Tests a method to throw an ServiceException",
            expectedExceptions = ServiceException.class)
    public void testExecuteBuilderException(String key, String path,
            String xsd) throws ServiceException {
        drugService.executeBuilder(key, path, xsd);
    }
}
