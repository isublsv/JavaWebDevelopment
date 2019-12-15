package by.gartsmanovich.hitcher.service;

import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

import java.util.List;

/**
 * The interface is used to determine the common structure of Review Service
 * Layer class.
 *
 * @author Dmitry Gartsmanovich
 */
public interface ReviewService {

    /**
     * Finds all reviews by author ID.
     *
     * @param id the provided author ID.
     * @return the review list.
     * @throws ServiceException if failed to find reviews by author ID.
     */
    List<Review> findReviewsByWhoID(long id) throws ServiceException;

    /**
     * Finds all reviews about provided user ID.
     *
     * @param id the provided user ID.
     * @return the review list.
     * @throws ServiceException if failed to find reviews by user ID.
     */
    List<Review> findReviewsByAboutID(long id) throws ServiceException;
}
