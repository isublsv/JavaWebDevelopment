package by.gartsmanovich.hitcher.service.factory;

import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.dao.transaction.factory.TransactionFactory;
import by.gartsmanovich.hitcher.service.ReviewService;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes;
import by.gartsmanovich.hitcher.service.impl.ReviewServiceImpl;
import by.gartsmanovich.hitcher.service.impl.TripServiceImpl;
import by.gartsmanovich.hitcher.service.impl.UserServiceImpl;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

/**
 * @author Dmitry Gartsmanovich
 */
public class ServiceFactoryImpl implements ServiceFactory {

    /**
     * Represents Transaction factory instance.
     */
    private TransactionFactory factory;

    /**
     * Constructs the factory instance with provided transaction factory.
     *
     * @param factoryValue the provided transaction factory.
     */
    public ServiceFactoryImpl(final TransactionFactory factoryValue) {
        this.factory = factoryValue;
    }

    /**
     * Creates the User Service instance that defines business logic over the
     * user entity.
     *
     * @return the user service realisation.
     */
    @Override
    public UserService getUserService() {
        Transaction transaction = factory.createTransaction();
        return new UserServiceImpl(transaction);
    }

    /**
     * Creates the Trip Service instance that defines business logic over the
     * trip entity.
     *
     * @return the trip service realisation.
     */
    @Override
    public TripService getTripService() {
        Transaction transaction = factory.createTransaction();
        return new TripServiceImpl(transaction);
    }

    /**
     * Creates the review Service instance that defines business logic over the
     * review entity.
     *
     * @return the review service realisation.
     */
    @Override
    public ReviewService getReviewService() {
        Transaction transaction = factory.createTransaction();
        return new ReviewServiceImpl(transaction);
    }

    /**
     * Closes the factory.
     *
     * @throws ServiceException if failed to close factory.
     */
    @Override
    public void close() throws ServiceException {
        try {
            factory.close();
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCodes.SQL_ERROR);
        }
    }
}
