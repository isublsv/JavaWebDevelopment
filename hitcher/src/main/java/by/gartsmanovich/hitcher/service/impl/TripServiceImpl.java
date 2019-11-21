package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.dao.transaction.Transaction;
import by.gartsmanovich.hitcher.service.TripService;

/**
 * The realisation of Trip Service interface is used to describe of application
 * functionality for the trip entity.
 *
 * @author Dmitry Gartsmanovich
 */
public class TripServiceImpl implements TripService {

    /**
     * Represents the transaction entity.
     */
    private Transaction transaction;

    /**
     * Constructs the Trip Service realisation instance with provided
     * transaction value.
     *
     * @param transactionValue the provided transaction.
     */
    public TripServiceImpl(final Transaction transactionValue) {
        this.transaction = transactionValue;
    }
}
