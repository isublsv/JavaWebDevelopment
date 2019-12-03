package by.gartsmanovich.hitcher.dao;

import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.dao.exception.DaoException;

import java.util.List;

/**
 * The interface describes the common structure for review type DAO.
 *
 * @author Dmitry Gartsmanovich
 */
public interface ReviewDao extends AbstractDao<Review> {

    /**
     * Finds all reviews by author ID in the database.
     *
     * @param id the of the author of the review.
     * @return the list of reviews.
     * @throws DaoException if failed to find all reviews entity in the
     *                      database.
     */
    List<Review> findAllReviewsByWhoId(long id) throws DaoException;

    /**
     * Finds all reviews by subject ID in the database.
     *
     * @param id the of the subject of the review.
     * @return the list of reviews.
     * @throws DaoException if failed to find all reviews entity in the
     *                      database.
     */
    List<Review> findAllReviewsByAboutId(long id) throws DaoException;
}
