package by.gartsmanovich.hitcher.dao.pool;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    ConnectionPool connectionPool;

    @Test
    public void testGetInstance() {
        connectionPool = ConnectionPool.getInstance();
    }
}