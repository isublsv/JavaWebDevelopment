package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.ReviewDao;
import by.gartsmanovich.hitcher.dao.UserDao;
import by.gartsmanovich.hitcher.dao.exception.DaoException;
import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.ReviewService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

import static by.gartsmanovich.hitcher.service.exception.ServiceErrorCodes.SQL_ERROR;

/**
 * The realisation of Review Service interface is used to describe of
 * application functionality for the review entity.
 *
 * @author Dmitry Gartsmanovich
 */
public class ReviewServiceImpl implements ReviewService {

    /**
     * Represents the transaction entity.
     */
    private Transaction transaction;

    /**
     * Constructs the Review Service realisation instance with provided
     * transaction value.
     *
     * @param transactionValue the provided transaction.
     */
    public ReviewServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
    }

    /**
     * Finds all reviews about user by ID.
     *
     * @param id the provided user ID.
     * @return the review list.
     * @throws ServiceException if failed to find reviews by user ID.
     */
    @Override
    public List<Review> findReviewsByWhoID(final long id) throws
            ServiceException {
        ReviewDao reviewDao = transaction.getReviewDao();
        try {
            List<Review> reviews = reviewDao.findAllReviewsByWhoId(id);
            fillReviewUsers(reviews);
            transaction.commit();
            return reviews;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Finds all reviews about provided user ID.
     *
     * @param id the provided user ID.
     * @return the review list.
     * @throws ServiceException if failed to find reviews by user ID.
     */
    @Override
    public List<Review> findReviewsByAboutID(final long id) throws
            ServiceException {
        ReviewDao reviewDao = transaction.getReviewDao();
        try {
            List<Review> reviews = reviewDao.findAllReviewsByAboutId(id);
            fillReviewUsers(reviews);
            transaction.commit();
            return reviews;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e, SQL_ERROR);
        }
    }

    /**
     * Gets users data from data source.
     *
     * @param reviews the provided review list.
     * @throws ServiceException if failed to find users by user ID.
     */
    private void fillReviewUsers(final List<Review> reviews) throws
            ServiceException {
        UserDao userDao = transaction.getUserDao();
        try {
            for (Review review : reviews) {
                Optional<User> who = userDao.findById(review.getWho().getId());
                Optional<User> about = userDao.findById(
                        review.getAbout().getId());
                who.ifPresent(review::setWho);
                about.ifPresent(review::setAbout);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, SQL_ERROR);
        }
    }
}
