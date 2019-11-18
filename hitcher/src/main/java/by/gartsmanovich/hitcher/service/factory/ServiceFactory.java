package by.gartsmanovich.hitcher.service.factory;

import by.gartsmanovich.hitcher.service.ReviewService;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

/**
 * The interface defines common methods that produce different type of
 * services.
 *
 * @author Dmitry Gartsmanovich
 */
public interface ServiceFactory {

    /**
     * Creates the User Service instance that defines business logic over the
     * user entity.
     *
     * @return the user service realisation.
     */
    UserService getUserService();

    /**
     * Creates the Trip Service instance that defines business logic over the
     * trip entity.
     *
     * @return the trip service realisation.
     */
    TripService getTripService();

    /**
     * Creates the review Service instance that defines business logic over the
     * review entity.
     *
     * @return the review service realisation.
     */
    ReviewService getReviewService();

    /**
     * Closes the factory.
     *
     * @throws ServiceException if failed to close factory.
     */
    void close() throws ServiceException;
}
