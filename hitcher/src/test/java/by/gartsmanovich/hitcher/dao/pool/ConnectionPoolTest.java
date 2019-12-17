package by.gartsmanovich.hitcher.dao.pool;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    private ConnectionPool connectionPool;

    @Test
    @Ignore
    public void testGetInstance() {
        connectionPool = ConnectionPool.getInstance();
    }
}
