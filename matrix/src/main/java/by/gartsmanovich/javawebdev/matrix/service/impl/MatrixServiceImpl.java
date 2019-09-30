package by.gartsmanovich.javawebdev.matrix.service.impl;

import by.gartsmanovich.javawebdev.matrix.bean.Matrix;
import by.gartsmanovich.javawebdev.matrix.repository.Repository;
import by.gartsmanovich.javawebdev.matrix.repository.exception.RepositoryException;
import by.gartsmanovich.javawebdev.matrix.repository.factory.RepositoryFactory;
import by.gartsmanovich.javawebdev.matrix.repository.specification.fill.LockSpecification;
import by.gartsmanovich.javawebdev.matrix.repository.specification.fill.ResetSpecification;
import by.gartsmanovich.javawebdev.matrix.repository.specification.fill.SynchSpecification;
import by.gartsmanovich.javawebdev.matrix.service.MatrixService;
import by.gartsmanovich.javawebdev.matrix.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.matrix.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixServiceImpl implements MatrixService {

    /**
     * The logger for Matrix Service class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            MatrixServiceImpl.class);

    /**
     * Provides the access to Matrix repository class methods.
     */
    private Repository matrixRepository;

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * Constructs an implementation of Service application layer class
     * instance.
     */
    public MatrixServiceImpl() {
        RepositoryFactory factory = RepositoryFactory.getInstance();
        matrixRepository = factory.getMatrixRepository();
        validator = new Validator();
    }

    /**
     * Creates the Matrix instance. Fills it from file by integers that divided
     * by provided delimiter.
     *
     * @param path      the path to storage file.
     * @param delimiter the delimiter to parse the data from file.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void createMatrix(final String path, final String delimiter) throws
            ServiceException {
        try {
            if (!validator.isValidValue(path)
                || !validator.isValidValue(delimiter)) {
                LOGGER.error("The parameters for creating matrix are"
                             + "not valid");
                throw new ServiceException("The parameters for creating matrix"
                                           + " are not valid");
            } else {
                matrixRepository.createMatrix(path, delimiter);
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the matrix that main diagonal was filled by using synchronised
     * construction.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public Matrix doOption1() throws ServiceException {
        try {
            return matrixRepository.query(new SynchSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the matrix that main diagonal was filled by using Lock classes.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public Matrix doOption2() throws ServiceException {
        try {
            return matrixRepository.query(new LockSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the matrix that main diagonal was filled by using synchronised
     * construction.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public Matrix doOption3() throws ServiceException {
        try {
            return matrixRepository.query(new SynchSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Returns the matrix that main diagonal was filled by using synchronised
     * construction.
     *
     * @return the matrix with a filled main diagonal.
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public Matrix doOption4() throws ServiceException {
        try {
            return matrixRepository.query(new SynchSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Sets all elements under the main diagonal of the matrix to zero.
     *
     * @throws ServiceException if error happens during execution.
     */
    @Override
    public void resetMatrix() throws ServiceException {
        try {
            matrixRepository.query(new ResetSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
