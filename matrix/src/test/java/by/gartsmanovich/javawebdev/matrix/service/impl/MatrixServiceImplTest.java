package by.gartsmanovich.javawebdev.matrix.service.impl;

import by.gartsmanovich.javawebdev.matrix.service.MatrixService;
import by.gartsmanovich.javawebdev.matrix.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.matrix.service.factory.ServiceFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixServiceImplTest {

    private MatrixService matrixService;

    @BeforeMethod
    public void setUp() {
        ServiceFactory factory = ServiceFactory.getInstance();
        matrixService = factory.getMatrixService();
    }

    @DataProvider(name = "inputDataCreateMatrixException")
    public Object[][] getInputDataForCreateMatrix() {
        return new Object[][]{{null, ","}, {"", ","}};
    }

    @Test(dataProvider = "inputDataCreateMatrixException",
            expectedExceptions = ServiceException.class)
    public void testCreateMatrixException(String path, String del) throws
            ServiceException {
        matrixService.createMatrix(path, del);
    }

    @DataProvider(name = "inputInvalidDataCreateMatrixException")
    public Object[][] getInvalidInputDataForCreateMatrix() {

        Object[][] data = null;
        try(Stream<Path> paths = Files.walk(Paths.get("test")) ) {

            List<String> fileList = paths.map(
                    Path::toString).filter(x -> x.endsWith(".txt"))
                                         .collect(Collectors.toList());
            
            data = new Object[fileList.size()][2];
            
            for (int i = 0; i < fileList.size(); i++) {
                data[i][0] = fileList.get(i);
                data[i][1] = " ";
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return data;
    }
    
    @Test(dataProvider = "inputInvalidDataCreateMatrixException",
            expectedExceptions = ServiceException.class)
    public void testCreateMatrixInvalidDataException(String path, String del) throws
            ServiceException {
        matrixService.createMatrix(path, del);
    }
    
    @DataProvider(name = "inputDataSaveMatrixException")
    public Object[][] getInputDataForSaveMatrix() {
        return new Object[][]{{null}, {""}};
    }

    @Test(dataProvider = "inputDataSaveMatrixException", expectedExceptions 
            = ServiceException.class)
    public void testSaveLastResultException(String path) throws
            ServiceException {
        matrixService.saveLastResult(path);
    }
}
