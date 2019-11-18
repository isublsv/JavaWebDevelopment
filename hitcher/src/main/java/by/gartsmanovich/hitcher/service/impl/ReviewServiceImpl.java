package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.ReviewService;

/**
 * @author Dmitry Gartsmanovich
 */
public class ReviewServiceImpl implements ReviewService {

    /**
     * 
     */
    private Transaction transaction;

    /**
     * 
     * @param transactionValue
     */
    public ReviewServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
    }
}
