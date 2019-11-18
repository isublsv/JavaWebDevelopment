package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.TripService;

/**
 * @author Dmitry Gartsmanovich
 */
public class TripServiceImpl implements TripService {

    /**
     * 
     */
    private Transaction transaction;

    /**
     * 
     * @param transactionValue
     */
    public TripServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
    }
}
