package by.gartsmanovich.hitcher.dao.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Dmitry Gartsmanovich
 */
public class WrapperConnection implements Connection {

    /**
     * A connection with a specific database.
     */
    private Connection connection;

    /**
     * Constructs the wrapper instance of connection.
     *
     * @param connectionValue the provided connection to specific DB.
     */
    public WrapperConnection(final Connection connectionValue) {
        connection = connectionValue;
    }

    /**
     * Releases this <code>Connection</code> object's database and JDBC
     * resources
     * immediately instead of waiting for them to be automatically released.
     *
     * @throws SQLException if a database access error occurs
     */
    void realClose() throws SQLException {
        connection.close();
    }

    /**
     * Creates a <code>Statement</code> object for sending
     * SQL statements to the database.
     *
     * @return a new default <code>Statement</code> object
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#createStatement() for more information
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Creates a <code>PreparedStatement</code> object for sending
     * parameterized SQL statements to the database.
     *
     * @param sql an SQL statement that may contain one or more '?' IN
     *            parameter placeholders
     * @return a new default <code>PreparedStatement</code> object
     * containing the pre-compiled SQL statement
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#prepareStatement(String) for more information
     */
    @Override
    public PreparedStatement prepareStatement(final String sql) throws
            SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * Creates a <code>CallableStatement</code> object for calling
     * database stored procedures.
     *
     * @param sql an SQL statement that may contain one or more '?'
     *            parameter placeholders. Typically this statement is
     *            specified using JDBC call escape syntax.
     * @return a new default <code>CallableStatement</code> object
     * containing the pre-compiled SQL statement
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#prepareCall(String) for more information
     */
    @Override
    public CallableStatement prepareCall(final String sql) throws
            SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * Converts the given SQL statement into the system's native SQL grammar.
     *
     * @param sql an SQL statement that may contain one or more '?'
     *            parameter placeholders
     * @return the native form of this statement
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#nativeSQL(String) for more information
     */
    @Override
    public String nativeSQL(final String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * Returns an object that implements the given interface to allow access to
     * non-standard methods, or standard methods not exposed by the proxy.
     *
     * @param iface A Class defining an interface that the result must
     *              implement.
     * @return an object that implements the interface. May be a proxy for
     * the actual implementing object.
     * @throws SQLException If no object found that implements the interface
     * @see Connection#unwrap(Class) for more information
     */
    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    /**
     * Returns true if this either implements the interface argument or is
     * directly or indirectly a wrapper
     * for an object that does. Returns false otherwise.
     *
     * @param iface a Class defining an interface.
     * @return true if this implements the interface or directly or
     * indirectly wraps an object that does.
     * @throws SQLException if an error occurs while determining whether
     *                      this is a wrapper
     *                      for an object with the given interface.
     * @see Connection#isWrapperFor(Class) for more information
     */
    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }

    /**
     * Returns this connection to the connection pool.
     *
     * @throws SQLException if a database access error occurs,
     *                      setAutoCommit(true) is called while
     *                      participating in a distributed transaction,
     *                      or this method is called on a closed connection
     */
    @Override
    public void close() throws SQLException {
        ConnectionPool.getInstance().releaseConnection(this);
    }


    /**
     * Sets this connection's auto-commit mode to the given state.
     *
     * @param autoCommit <code>true</code> to enable auto-commit mode;
     *                   <code>false</code> to disable it
     * @throws SQLException if a database access error occurs,
     *                      setAutoCommit(true) is called while
     *                      participating in a distributed transaction,
     *                      or this method is called on a closed connection
     * @see Connection#setAutoCommit(boolean) for more information
     */
    @Override
    public void setAutoCommit(final boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * Retrieves the current auto-commit mode for this <code>Connection</code>
     * object.
     *
     * @return the current state of this <code>Connection</code> object's
     * auto-commit mode
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#getAutoCommit() for more information
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * Makes all changes made since the previous
     * commit/rollback permanent and releases any database locks
     * currently held by this <code>Connection</code> object.
     *
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a
     *                      distributed transaction,
     *                      if this method is called on a closed connection
     *                      or this <code>Connection</code> object is in
     *                      auto-commit mode
     * @see Connection#commit() for more information
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Undoes all changes made in the current transaction
     * and releases any database locks currently held
     * by this <code>Connection</code> object.
     *
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a
     *                      distributed transaction,
     *                      this method is called on a closed connection or
     *                      this <code>Connection</code> object is in
     *                      auto-commit mode
     * @see Connection#rollback() for more information
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Retrieves whether this <code>Connection</code> object has been
     * closed.
     *
     * @return <code>true</code> if this <code>Connection</code> object
     * is closed; <code>false</code> if it is still open
     * @throws SQLException if a database access error occurs
     * @see Connection#isClosed() for more information
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * Retrieves a <code>DatabaseMetaData</code> object that contains
     * metadata about the database to which this
     * <code>Connection</code> object represents a connection.
     *
     * @return a <code>DatabaseMetaData</code> object for this
     * <code>Connection</code> object
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#getMetaData() for more information
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * Puts this connection in read-only mode as a hint to the driver to enable
     * database optimizations.
     *
     * @param readOnly <code>true</code> enables read-only mode;
     *                 <code>false</code> disables it
     * @throws SQLException if a database access error occurs, this
     *                      method is called on a closed connection or this
     *                      method is called during a transaction
     * @see Connection#setReadOnly(boolean) for more information
     */
    @Override
    public void setReadOnly(final boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     * Retrieves whether this <code>Connection</code>
     * object is in read-only mode.
     *
     * @return <code>true</code> if this <code>Connection</code> object
     * is read-only; <code>false</code> otherwise
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#isReadOnly() for more information
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * Sets the given catalog name in order to select
     * a subspace of this <code>Connection</code> object's database
     * in which to work.
     *
     * @param catalog the name of a catalog (subspace in this
     *                <code>Connection</code> object's database) in which to
     *                work
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#setCatalog(String) for more information
     */
    @Override
    public void setCatalog(final String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     * Retrieves this <code>Connection</code> object's current catalog name.
     *
     * @return the current catalog name or <code>null</code> if there is none
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#getCatalog() for more information
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * Attempts to change the transaction isolation level for this
     * <code>Connection</code> object to the one given.
     *
     * @param level one of the following <code>Connection</code> constants:
     *              <code>Connection.TRANSACTION_READ_UNCOMMITTED</code>,
     *              <code>Connection.TRANSACTION_READ_COMMITTED</code>,
     *              <code>Connection.TRANSACTION_REPEATABLE_READ</code>, or
     *              <code>Connection.TRANSACTION_SERIALIZABLE</code>.
     *              (Note that <code>Connection.TRANSACTION_NONE</code>
     *              cannot be used because it specifies that transactions are
     *              not supported.)
     * @throws SQLException if a database access error occurs, this
     *                      method is called on a closed connection
     *                      or the given parameter is not one of the
     *                      <code>Connection</code> constants
     * @see Connection#setTransactionIsolation(int) for more information
     */
    @Override
    public void setTransactionIsolation(final int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     * Retrieves this <code>Connection</code> object's current
     * transaction isolation level.
     *
     * @return the current transaction isolation level, which will be one
     * of the following constants:
     * <code>Connection.TRANSACTION_READ_UNCOMMITTED</code>,
     * <code>Connection.TRANSACTION_READ_COMMITTED</code>,
     * <code>Connection.TRANSACTION_REPEATABLE_READ</code>,
     * <code>Connection.TRANSACTION_SERIALIZABLE</code>, or
     * <code>Connection.TRANSACTION_NONE</code>.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#getTransactionIsolation() for more information
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     * Retrieves the first warning reported by calls on this
     * <code>Connection</code> object.
     *
     * @return the first <code>SQLWarning</code> object or <code>null</code>
     * if there are none
     * @throws SQLException if a database access error occurs or
     *                      this method is called on a closed connection
     * @see Connection#getWarnings() for more information
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     * Clears all warnings reported for this <code>Connection</code> object.
     *
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#clearWarnings() for more information
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * Creates a <code>Statement</code> object that will generate
     * <code>ResultSet</code> objects with the given type and concurrency.
     *
     * @param resultSetType        a result set type; one of
     *                             <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *                             <code>ResultSet
     *                             .TYPE_SCROLL_INSENSITIVE</code>, or
     *                             <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @param resultSetConcurrency a concurrency type; one of
     *                             <code>ResultSet.CONCUR_READ_ONLY</code> or
     *                             <code>ResultSet.CONCUR_UPDATABLE</code>
     * @return a new <code>Statement</code> object that will generate
     * <code>ResultSet</code> objects with the given type and concurrency
     * @throws SQLException if a database access error occurs, this method
     *                      is called on a closed connection
     *                      or the given parameters are
     *                      not <code>ResultSet</code>
     *                      constants indicating type and concurrency
     * @see Connection#createStatement(int, int) for more information
     */
    @Override
    public Statement createStatement(final int resultSetType,
            final int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    /**
     * Creates a <code>PreparedStatement</code> object that will generate
     * <code>ResultSet</code> objects with the given type and concurrency.
     *
     * @param sql                  a <code>String</code> object that is the
     *                             SQL statement to
     *                             be sent to the database; may contain one
     *                             or more '?' IN parameters
     * @param resultSetType        a result set type; one of
     *                             <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *                             <code>ResultSet
     *                             .TYPE_SCROLL_INSENSITIVE</code>, or
     *                             <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @param resultSetConcurrency a concurrency type; one of
     *                             <code>ResultSet.CONCUR_READ_ONLY</code> or
     *                             <code>ResultSet.CONCUR_UPDATABLE</code>
     * @return a new PreparedStatement object containing the
     * pre-compiled SQL statement that will produce <code>ResultSet</code>
     * objects with the given type and concurrency
     * @throws SQLException if a database access error occurs, this method
     *                      is called on a closed connection
     *                      or the given parameters are
     *                      not <code>ResultSet</code>
     *                      constants indicating type and concurrency
     * @see Connection#prepareStatement(String, int, int) for more information
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
            final int resultSetType, final int resultSetConcurrency) throws
            SQLException {
        return connection.prepareStatement(sql, resultSetType,
                                           resultSetConcurrency);
    }

    /**
     * Creates a <code>CallableStatement</code> object that will generate
     * <code>ResultSet</code> objects with the given type and concurrency.
     *
     * @param sql                  a <code>String</code> object that is the
     *                             SQL statement to
     *                             be sent to the database; may contain on
     *                             or more '?' parameters
     * @param resultSetType        a result set type; one of
     *                             <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *                             <code>ResultSet
     *                             .TYPE_SCROLL_INSENSITIVE</code>, or
     *                             <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @param resultSetConcurrency a concurrency type; one of
     *                             <code>ResultSet.CONCUR_READ_ONLY</code> or
     *                             <code>ResultSet.CONCUR_UPDATABLE</code>
     * @return a new <code>CallableStatement</code> object containing the
     * pre-compiled SQL statement that will produce <code>ResultSet</code>
     * objects with the given type and concurrency
     * @throws SQLException if a database access error occurs, this method
     *                      is called on a closed connection
     *                      or the given parameters are
     *                      not <code>ResultSet</code>
     *                      constants indicating type and concurrency
     * @see Connection#prepareCall(String, int, int) for more information
     */
    @Override
    public CallableStatement prepareCall(final String sql,
            final int resultSetType, final int resultSetConcurrency) throws
            SQLException {
        return connection.prepareCall(sql, resultSetType,
                                      resultSetConcurrency);
    }

    /**
     * Retrieves the <code>Map</code> object associated with this
     * <code>Connection</code> object.
     *
     * @return the <code>java.util.Map</code> object associated
     * with this <code>Connection</code> object
     * @throws SQLException if a database access error occurs or this method
     *                      is called on a closed connection
     * @see Connection#getTypeMap() for more information
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * Installs the given <code>TypeMap</code> object as the type map for
     * this <code>Connection</code> object.
     *
     * @param map the <code>java.util.Map</code> object to install
     *            as the replacement for this <code>Connection</code>
     *            object's default type map
     * @throws SQLException if a database access error occurs, this
     *                      method is called on a closed connection or
     *                      the given parameter is not a
     *                      <code>java.util.Map</code> object
     * @see Connection#setTypeMap(Map) for more information
     */
    @Override
    public void setTypeMap(final Map<String, Class<?>> map) throws
            SQLException {
        connection.setTypeMap(map);
    }

    /**
     * Changes the default holdability of <code>ResultSet</code> objects
     * created using this <code>Connection</code> object to the given
     * holdability.
     *
     * @param holdability a <code>ResultSet</code> holdability constant; one of
     *                    <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *                    <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @throws SQLException if a database access occurs, this method is called
     *                      on a closed connection, or the given parameter
     *                      is not a <code>ResultSet</code>
     *                      constant indicating holdability
     * @see Connection#setHoldability(int) for more information
     */
    @Override
    public void setHoldability(final int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     * Retrieves the current holdability of <code>ResultSet</code> objects
     * created using this <code>Connection</code> object.
     *
     * @return the holdability, one of
     * <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     * <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#getHoldability() for more information
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     * Creates an unnamed savepoint in the current transaction and
     * returns the new <code>Savepoint</code> object that represents it.
     *
     * @return the new <code>Savepoint</code> object
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a
     *                      distributed transaction, this method is called on a
     *                      closed connection or this <code>Connection</code>
     *                      object is currently in auto-commit mode
     * @see Connection#setSavepoint() for more information
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * Creates a savepoint with the given name in the current transaction
     * and returns the new <code>Savepoint</code> object that represents it.
     *
     * @param name a <code>String</code> containing the name of the savepoint
     * @return the new <code>Savepoint</code> object
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a
     *                      distributed transaction, this method is called on a
     *                      closed connection or this
     *                      <code>Connection</code> object is currently in
     *                      auto-commit mode
     * @see Connection#setSavepoint(String) for more information
     */
    @Override
    public Savepoint setSavepoint(final String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * Undoes all changes made after the given <code>Savepoint</code> object
     * was set.
     *
     * @param savepoint the <code>Savepoint</code> object to roll back to
     * @throws SQLException if a database access error occurs,
     *                      this method is called while participating in a
     *                      distributed transaction, this method is called on a
     *                      closed connection, the <code>Savepoint</code>
     *                      object is no longer valid, or this
     *                      <code>Connection</code> object is currently in
     *                      auto-commit mode
     * @see Connection#rollback(Savepoint) for more information
     */
    @Override
    public void rollback(final Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * Removes the specified <code>Savepoint</code>  and subsequent
     * <code>Savepoint</code> objects from the current
     * transaction.
     *
     * @param savepoint the <code>Savepoint</code> object to be removed
     * @throws SQLException if a database access error occurs, this
     *                      method is called on a closed connection or
     *                      the given <code>Savepoint</code> object
     *                      is not a valid savepoint in the current transaction
     * @see Connection#releaseSavepoint(Savepoint) for more information
     */
    @Override
    public void releaseSavepoint(final Savepoint savepoint) throws
            SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * Creates a <code>Statement</code> object that will generate
     * <code>ResultSet</code> objects with the given type, concurrency,
     * and holdability.
     *
     * @param resultSetType        one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *                             <code>ResultSet
     *                             .TYPE_SCROLL_INSENSITIVE</code>, or
     *                             <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @param resultSetConcurrency one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet.CONCUR_READ_ONLY</code> or
     *                             <code>ResultSet.CONCUR_UPDATABLE</code>
     * @param resultSetHoldability one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet
     *                             .HOLD_CURSORS_OVER_COMMIT</code> or
     *                             <code>ResultSet
     *                             .CLOSE_CURSORS_AT_COMMIT</code>
     * @return a new <code>Statement</code> object that will generate
     * <code>ResultSet</code> objects with the given type,
     * concurrency, and holdability
     * @throws SQLException if a database access error occurs, this
     *                      method is called on a closed connection
     *                      or the given parameters are
     *                      not <code>ResultSet</code>
     *                      constants indicating type,
     *                      concurrency, and holdability
     * @see Connection#createStatement(int, int, int) for more information
     */
    @Override
    public Statement createStatement(final int resultSetType,
            final int resultSetConcurrency,
            final int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency,
                                          resultSetHoldability);
    }

    /**
     * Creates a <code>PreparedStatement</code> object that will generate
     * <code>ResultSet</code> objects with the given type, concurrency,
     * and holdability.
     *
     * @param sql                  a <code>String</code> object that is the
     *                             SQL statement to
     *                             be sent to the database; may contain one
     *                             or more '?' IN parameters
     * @param resultSetType        one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *                             <code>ResultSet
     *                             .TYPE_SCROLL_INSENSITIVE</code>, or
     *                             <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @param resultSetConcurrency one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet.CONCUR_READ_ONLY</code> or
     *                             <code>ResultSet.CONCUR_UPDATABLE</code>
     * @param resultSetHoldability one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet
     *                             .HOLD_CURSORS_OVER_COMMIT</code> or
     *                             <code>ResultSet
     *                             .CLOSE_CURSORS_AT_COMMIT</code>
     * @return a new <code>PreparedStatement</code> object, containing the
     * pre-compiled SQL statement, that will generate
     * <code>ResultSet</code> objects with the given type,
     * concurrency, and holdability
     * @throws SQLException if a database access error occurs, this
     *                      method is called on a closed connection
     *                      or the given parameters are
     *                      not <code>ResultSet</code>
     *                      constants indicating type,
     *                      concurrency, and holdability
     * @see Connection#prepareStatement(String, int, int, int) for more
     * information
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
            final int resultSetType, final int resultSetConcurrency,
            final int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType,
                                           resultSetConcurrency,
                                           resultSetHoldability);
    }

    /**
     * Creates a <code>CallableStatement</code> object that will generate
     * <code>ResultSet</code> objects with the given type and concurrency.
     * This method is the same as the <code>prepareCall</code> method
     * above, but it allows the default result set
     * type, result set concurrency type and holdability to be overridden.
     *
     * @param sql                  a <code>String</code> object that is the
     *                             SQL statement to
     *                             be sent to the database; may contain on
     *                             or more '?' parameters
     * @param resultSetType        one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *                             <code>ResultSet
     *                             .TYPE_SCROLL_INSENSITIVE</code>, or
     *                             <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @param resultSetConcurrency one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet.CONCUR_READ_ONLY</code> or
     *                             <code>ResultSet.CONCUR_UPDATABLE</code>
     * @param resultSetHoldability one of the following <code>ResultSet</code>
     *                             constants:
     *                             <code>ResultSet
     *                             .HOLD_CURSORS_OVER_COMMIT</code> or
     *                             <code>ResultSet
     *                             .CLOSE_CURSORS_AT_COMMIT</code>
     * @return a new <code>CallableStatement</code> object, containing the
     * pre-compiled SQL statement, that will generate
     * <code>ResultSet</code> objects with the given type,
     * concurrency, and holdability
     * @throws SQLException if a database access error occurs, this
     *                      method is called on a closed connection
     *                      or the given parameters are not
     *                      <code>ResultSet</code> constants indicating type,
     *                      concurrency, and holdability
     * @see Connection#prepareCall(String, int, int, int) for more information
     */
    @Override
    public CallableStatement prepareCall(final String sql,
            final int resultSetType, final int resultSetConcurrency,
            final int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency,
                                      resultSetHoldability);
    }

    /**
     * Creates a default <code>PreparedStatement</code> object that has
     * the capability to retrieve auto-generated keys.
     *
     * @param sql               an SQL statement that may contain one or
     *                          more '?' IN parameter placeholders
     * @param autoGeneratedKeys a flag indicating whether auto-generated keys
     *                          should be returned; one of
     *                          <code>Statement.RETURN_GENERATED_KEYS</code> or
     *                          <code>Statement.NO_GENERATED_KEYS</code>
     * @return a new <code>PreparedStatement</code> object, containing the
     * pre-compiled SQL statement, that will have the capability of
     * returning auto-generated keys
     * @throws SQLException if a database access error  occurs, this
     *                      method is called on a closed connection
     *                      or the given parameter is not
     *                      a <code>Statement</code> constant indicating
     *                      whether auto-generated keys should be returned
     * @see Connection#prepareStatement(String, int) for more information
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
            final int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * Creates a default <code>PreparedStatement</code> object capable
     * of returning the auto-generated keys designated by the given array.
     *
     * @param sql           an SQL statement that may contain one or more
     *                      '?' IN parameter placeholders
     * @param columnIndexes an array of column indexes indicating the columns
     *                      that should be returned from the inserted row or
     *                      rows
     * @return a new <code>PreparedStatement</code> object, containing the
     * pre-compiled statement, that is capable of returning the
     * auto-generated keys designated by the given array of column indexes
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#prepareStatement(String, int[]) for more information
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
            final int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * Creates a default <code>PreparedStatement</code> object capable
     * of returning the auto-generated keys designated by the given array.
     *
     * @param sql         an SQL statement that may contain one or more '?' IN
     *                    parameter placeholders
     * @param columnNames an array of column names indicating the columns
     *                    that should be returned from the inserted row or rows
     * @return a new <code>PreparedStatement</code> object, containing the
     * pre-compiled statement, that is capable of returning the
     * auto-generated keys designated by the given array of column
     * names
     * @throws SQLException if a database access error occurs or this method
     *                      is called on a closed connection
     * @see Connection#prepareStatement(String, String[]) for more information
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
            final String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     * Constructs an object that implements the <code>Clob</code> interface.
     *
     * @return An object that implements the <code>Clob</code> interface
     * @throws SQLException if an object that implements the <code>Clob</code>
     *                      interface can not be constructed, this method is
     *                      called on a closed connection or a database access
     *                      error occurs.
     * @see Connection#createClob() for more information
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    /**
     * Constructs an object that implements the <code>Blob</code> interface.
     *
     * @return An object that implements the <code>Blob</code> interface
     * @throws SQLException if an object that implements the <code>Blob</code>
     *                      interface can not be constructed, this method is
     *                      called on a closed connection or a database access
     *                      error occurs.
     * @see Connection#createBlob() for more information
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    /**
     * Constructs an object that implements the <code>NClob</code> interface.
     *
     * @return An object that implements the <code>NClob</code> interface
     * @throws SQLException if an object that implements the <code>NClob</code>
     *                      interface can not be constructed, this method is
     *                      called on a closed connection or a database access
     *                      error occurs.
     * @see Connection#createNClob() for more information
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     * Constructs an object that implements the <code>SQLXML</code>
     * interface.
     *
     * @return An object that implements the <code>SQLXML</code> interface
     * @throws SQLException if an object that implements the
     *                      <code>SQLXML</code>
     *                      interface can not be constructed, this method is
     *                      called on a closed connection or a database
     *                      access error occurs.
     * @see Connection#createSQLXML() for more information
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * Returns true if the connection has not been closed and is still valid.
     *
     * @param timeout The time in seconds to wait for the
     *                database operation used to validate the
     *                connection to complete.  If the timeout period
     *                expires before the operation completes, this method
     *                returns false.  A value of 0 indicates a timeout is
     *                not applied to the database operation.
     * @return true if the connection is valid, false otherwise
     * @throws SQLException if the value supplied for <code>timeout</code>
     *                      is less than 0
     * @see Connection#isValid(int) for more information
     */
    @Override
    public boolean isValid(final int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * Sets the value of the client info property specified by name to the
     * value specified by value.
     *
     * @param name  The name of the client info property to set
     * @param value The value to set the client info property to.
     *              If the value is null, the current value of the specified
     *              property is cleared.
     * @throws SQLClientInfoException if the database server returns an
     *                                error while setting the client info value
     *                                on the database server or this method
     *                                is called on a closed connection
     * @see Connection#setClientInfo(String, String) for more information
     */
    @Override
    public void setClientInfo(final String name, final String value) throws
            SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * Sets the value of the connection's client info properties.
     *
     * @param properties the list of client info properties
     *                   to set
     * @throws SQLClientInfoException if the database server returns an
     *                                error while setting the clientInfo values
     *                                on the database server or this method
     *                                is called on a closed connection
     * @see Connection#setClientInfo(Properties) for more information
     */
    @Override
    public void setClientInfo(final Properties properties) throws
            SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * Returns the value of the client info property specified by name.
     *
     * @param name The name of the client info property to retrieve
     * @return The value of the client info property specified
     * @throws SQLException if the database server returns an error
     *                      when fetching the client info value from the
     *                      database or this method is called on a closed
     *                      connection
     * @see Connection#getClientInfo(String) for more information
     */
    @Override
    public String getClientInfo(final String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     * Returns a list containing the name and current value of each client info
     * property supported by the driver.
     *
     * @return A <code>Properties</code> object that contains the name and
     * current value of each of the client info properties supported by
     * the driver.
     * @throws SQLException if the database server returns an error when
     *                      fetching the client info values from the database
     *                      or this method is called on a closed connection
     * @see Connection#getClientInfo() for more information
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * Factory method for creating Array objects.
     *
     * @param typeName the SQL name of the type the elements of the array
     *                 map to.
     * @param elements the elements that populate the returned object
     * @return an Array object whose elements map to the specified SQL type
     * @throws SQLException if a database error occurs, the JDBC type is not
     *                      appropriate for the typeName and the conversion is
     *                      not supported, the typeName is null or this
     *                      method is called on a closed connection
     * @see Connection#createArrayOf(String, Object[]) for more information
     */
    @Override
    public Array createArrayOf(final String typeName,
            final Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * Factory method for creating Struct objects.
     *
     * @param typeName   the SQL type name of the SQL structured type that
     *                   this <code>Struct</code>
     *                   object maps to.
     * @param attributes the attributes that populate the returned object
     * @return a Struct object that maps to the given SQL type and is
     * populated with the given attributes
     * @throws SQLException if a database error occurs, the typeName is null
     *                      or this method is called on a closed connection
     * @see Connection#createStruct(String, Object[]) for more information
     */
    @Override
    public Struct createStruct(final String typeName,
            final Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * Sets the given schema name to access.
     *
     * @param schema the name of a schema  in which to work
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#setSchema(String) for more information
     */
    @Override
    public void setSchema(final String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     * Retrieves this <code>Connection</code> object's current schema name.
     *
     * @return the current schema name or <code>null</code> if there is none
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     * @see Connection#getSchema() for more information
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * Terminates an open connection.
     *
     * @param executor The <code>Executor</code>  implementation which will
     *                 be used by <code>abort</code>.
     * @throws SQLException      if a database access error occurs or
     *                           the {@code executor} is {@code null},
     * @throws SecurityException if a security manager exists and its
     *                           <code>checkPermission</code> method denies
     *                           calling <code>abort</code>
     * @see Connection#abort(Executor) for more information
     */
    @Override
    public void abort(final Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * Sets the maximum period a <code>Connection</code> or
     * objects created from the <code>Connection</code>
     * will wait for the database to reply to any one request.
     *
     * @param executor     The <code>Executor</code>  implementation which will
     *                     be used by <code>setNetworkTimeout</code>.
     * @param milliseconds The time in milliseconds to wait for the database
     *                     operation
     *                     to complete.
     * @throws SQLException      if a database access error
     *                           occurs, this method is called on a closed
     *                           connection, the {@code executor} is
     *                           {@code null}, or the value specified for
     *                           <code>seconds</code> is less than 0.
     * @throws SecurityException if a security manager exists and its
     *                           <code>checkPermission</code>
     *                           method denies calling
     *                           <code>setNetworkTimeout</code>.
     * @see Connection#setNetworkTimeout(Executor, int)  for more information
     */
    @Override
    public void setNetworkTimeout(final Executor executor,
            final int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     * Retrieves the number of milliseconds the driver will
     * wait for a database request to complete.
     * If the limit is exceeded, a <code>SQLException</code> is thrown.
     *
     * @return the current timeout limit in milliseconds; zero means there is
     * no limit
     * @throws SQLException if a database access error occurs or this method
     *                      is called on a closed <code>Connection</code>
     * @see Connection#getNetworkTimeout() for more information
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }


}
