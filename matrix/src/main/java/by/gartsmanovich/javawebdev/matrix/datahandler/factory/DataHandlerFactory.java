package by.gartsmanovich.javawebdev.matrix.datahandler.factory;

import by.gartsmanovich.javawebdev.matrix.datahandler.DataReader;
import by.gartsmanovich.javawebdev.matrix.datahandler.DataWriter;
import by.gartsmanovich.javawebdev.matrix.datahandler.impl.DataReaderImpl;
import by.gartsmanovich.javawebdev.matrix.datahandler.impl.DataWriterImpl;

/**
 * Class Data Handler factory used to proceed the data from file. Depends on
 * type of data handler can read or write to the selected file.
 *
 * @author Dmitry Gartsmanovich
 */
public final class DataHandlerFactory {

    /**
     * The factory instance will be created at the start of the execution of
     * DataHandlerFactory class.
     */
    private static DataHandlerFactory instance = new DataHandlerFactory();

    /**
     * Provides the access to DataReader class methods.
     */
    private final DataReader dataReader = new DataReaderImpl();

    /**
     * Provides the access to DataWriter class methods.
     */
    private final DataWriter dataWriter = new DataWriterImpl();

    /**
     * Private constructor. Forbids the explicit object creation.
     */
    private DataHandlerFactory() {
    }

    /**
     * Global point for access to factory methods.
     *
     * @return the instance of Data Handler factory.
     */
    public static DataHandlerFactory getInstance() {
        return instance;
    }

    /**
     * Returns the implementation of the Data Reader interface and provides
     * the access to its methods.
     *
     * @return an instance of Data Reader implementation.
     */
    public DataReader getDataReader() {
        return dataReader;
    }

    /**
     * Returns the implementation of the Data Writer interface and provides
     * the access to its methods.
     *
     * @return an instance of Data Writer implementation.
     */
    public DataWriter getDataWriter() {
        return dataWriter;
    }
}
