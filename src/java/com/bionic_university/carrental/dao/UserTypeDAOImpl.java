/*
 * UserTypeDAOImpl.java 2014/03/30
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.dao;

import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.entities.UserType;
import com.bionic_university.carrental.idao.IUserTypeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for "usertypes" table
 *
 * @author Florin
 */
public class UserTypeDAOImpl implements IUserTypeDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final String TABLE_NAME = "usertypes";
    private final String COL_1 = "usertype_id";
    private final String COL_2 = "usertype";

    private final String INSERT_QUERY;
    private final String UPDATE_QUERY;
    private final String DELETE_QUERY;
    private final String SELECT_QUERY;

    {
        INSERT_QUERY = new StringBuffer()
                .append("INSERT INTO ")
                .append(TABLE_NAME)
                .append(" (")
                .append(COL_2)
                .append(") VALUES ")
                .append("(?)")
                .toString();

        UPDATE_QUERY = new StringBuffer()
                .append("UPDATE ")
                .append(TABLE_NAME)
                .append(" SET ")
                .append(COL_2).append("=?")
                .append(" WHERE ")
                .append(COL_1).append("=?")
                .toString();

        DELETE_QUERY = new StringBuffer()
                .append("DELETE FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(COL_1).append("=?")
                .toString();

        SELECT_QUERY = new StringBuffer()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .toString();
    }

    @Override
    public int insert(UserType userType) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(INSERT_QUERY);
            ps.setString(1, userType.getUsertype());
            result = ps.executeUpdate();
            Lgr.LOGGER.info("Data inserted successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return result;
    }

    @Override
    public int update(UserType userType) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(UPDATE_QUERY);
            ps.setString(1, userType.getUsertype());
            ps.setInt(2, userType.getUsertypeID());
            result = ps.executeUpdate();
            Lgr.LOGGER.info("Data updated successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return result;
    }

    @Override
    public int delete(UserType userType) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(DELETE_QUERY);
            ps.setInt(1, userType.getUsertypeID());
            result = ps.executeUpdate();
            Lgr.LOGGER.info("Data deleted successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return result;
    }

    @Override
    public List<UserType> findAll() {
        List<UserType> list = new ArrayList<>();
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userTypeID = rs.getInt(1);
                String userType = rs.getString(2);
                UserType userTypeObj = new UserType(userTypeID, userType);
                list.add(userTypeObj);
            }
            Lgr.LOGGER.info("Data selected successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return list;
    }

}
