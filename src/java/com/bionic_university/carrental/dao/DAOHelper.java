/*
 * DAOHelper.java 2014/04/14
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.dao;

import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.datasource.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary class for DAO classes
 *
 * @author Florin
 */
public class DAOHelper {

    //code representing that executeUpdate command ended with error
    public final static int EXECUTE_UPDATE_ERROR_CODE = -88;

    static Connection getConnection() throws SQLException {
        return ConnectionManager.getInstance().getConnection();
    }

    static void freeConnection(Connection connection) throws SQLException {
        ConnectionManager.getInstance().freeConnection(connection);
    }

    static void closeResources(Connection cn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                freeConnection(cn);
            }
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        }
    }
}
