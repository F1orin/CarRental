/*
 * ConnectionManager.java 2014/04/09
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.datasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for managing the connections got from ConnectionPool
 *
 * @author Florin
 */
public class ConnectionManager {

    private static ConnectionManager instance;
    private final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectionManager() {
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public void freeConnection(Connection connection) throws SQLException {
        connectionPool.freeConnection(connection);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singletone");
    }
}
