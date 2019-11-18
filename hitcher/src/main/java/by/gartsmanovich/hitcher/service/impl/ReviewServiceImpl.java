package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The realisation of Review Service interface is used to describe of
 * application functionality for the review entity.
 *
 * @author Dmitry Gartsmanovich
 */
public class ReviewServiceImpl implements ReviewService {

    /**
     * The logger for ReviewServiceImpl class.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ReviewServiceImpl.class);

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
}
