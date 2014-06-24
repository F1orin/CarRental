/*
 * ConnectionPool.java 2014/04/09
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.datasource;

import com.bionic_university.carrental.util.Lgr;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Class that provides the ability to use Apache Tomcat
 * Connection Pool
 *
 * @author Florin
 */
public class ConnectionPool {

    InitialContext initialContext;
    DataSource dataSource;

    {
        try {
            initialContext = new InitialContext();
            dataSource = (javax.sql.DataSource) initialContext
                    .lookup("java:comp/env/jdbc/appname");
        } catch (NamingException e) {
            Lgr.LOGGER.error(e);
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        Lgr.LOGGER.debug("getConnection called" + connection);
        return connection;
    }

    public void freeConnection(Connection connection) throws SQLException {
        Lgr.LOGGER.debug("freeConnection called" + connection);
        connection.close();
    }
}
