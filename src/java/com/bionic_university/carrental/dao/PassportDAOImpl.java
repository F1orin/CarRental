/*
 * PassportDAOImpl.java 2014/03/30
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.dao;

import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.entities.Passport;
import com.bionic_university.carrental.idao.IPassportDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for "passports" table
 *
 * @author Florin
 */
public class PassportDAOImpl implements IPassportDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final String TABLE_NAME = "passports";
    private final String COL_1 = "passport_id";
    private final String COL_2 = "last_name";
    private final String COL_3 = "first_name";
    private final String COL_4 = "patronymic";
    private final String COL_5 = "birthday";
    private final String COL_6 = "p_series";
    private final String COL_7 = "p_number";
    private final String COL_8 = "who_issued";
    private final String COL_9 = "when_issued";

    private final String INSERT_QUERY;
    private final String UPDATE_QUERY;
    private final String DELETE_QUERY;
    private final String SELECT_QUERY;

    {
        INSERT_QUERY = new StringBuffer()
                .append("INSERT INTO ")
                .append(TABLE_NAME)
                .append(" (")
                .append(COL_2).append(",")
                .append(COL_3).append(",")
                .append(COL_4).append(",")
                .append(COL_5).append(",")
                .append(COL_6).append(",")
                .append(COL_7).append(",")
                .append(COL_8).append(",")
                .append(COL_9)
                .append(") VALUES ")
                .append("(?,?,?,?,?,?,?,?)")
                .toString();

        UPDATE_QUERY = new StringBuffer()
                .append("UPDATE ")
                .append(TABLE_NAME)
                .append(" SET ")
                .append(COL_2).append("=?").append(",")
                .append(COL_3).append("=?").append(",")
                .append(COL_4).append("=?").append(",")
                .append(COL_5).append("=?").append(",")
                .append(COL_6).append("=?").append(",")
                .append(COL_7).append("=?").append(",")
                .append(COL_8).append("=?").append(",")
                .append(COL_9).append("=?")
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
    public int insert(Passport passport) {
        int autoIncID = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, passport.getLastName());
            ps.setString(2, passport.getFirstName());
            ps.setString(3, passport.getPatronymic());
            ps.setDate(4, passport.getBirthday());
            ps.setString(5, passport.getpSeries());
            ps.setString(6, passport.getpNumber());
            ps.setString(7, passport.getWhoIssued());
            ps.setDate(8, passport.getWhenIssued());
            ps.executeUpdate();
            ResultSet keysSet = ps.getGeneratedKeys();
            keysSet.next();
            autoIncID = keysSet.getInt(1);
            Lgr.LOGGER.info("Data inserted successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return autoIncID;
    }

    @Override
    public int update(Passport passport) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(UPDATE_QUERY);
            ps.setString(1, passport.getLastName());
            ps.setString(2, passport.getFirstName());
            ps.setString(3, passport.getPatronymic());
            ps.setDate(4, passport.getBirthday());
            ps.setString(5, passport.getpSeries());
            ps.setString(6, passport.getpNumber());
            ps.setString(7, passport.getWhoIssued());
            ps.setDate(8, passport.getWhenIssued());
            ps.setInt(9, passport.getPassportID());
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
    public int delete(Passport passport) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(DELETE_QUERY);
            ps.setInt(1, passport.getPassportID());
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
    public List<Passport> findAll() {
        List<Passport> list = new ArrayList<>();
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                int passportID = rs.getInt(1);
                String lastName = rs.getString(2);
                String firstName = rs.getString(3);
                String patronymic = rs.getString(4);
                Date birthday = rs.getDate(5);
                String pSeries = rs.getString(6);
                String pNumber = rs.getString(7);
                String whoIssued = rs.getString(8);
                Date whenIssued = rs.getDate(9);
                Passport passportObj = new Passport(passportID, lastName,
                        firstName, patronymic, birthday, pSeries, pNumber,
                        whoIssued, whenIssued);
                list.add(passportObj);
            }
            Lgr.LOGGER.info("Data selected successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return list;
    }

    @Override
    public Passport findByID(int passportIDParam) {
        Passport passportObj = null;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_QUERY + " WHERE passport_id=?");
            ps.setInt(1, passportIDParam);
            rs = ps.executeQuery();
            rs.next();
            int passportID = rs.getInt(1);
            String lastName = rs.getString(2);
            String firstName = rs.getString(3);
            String patronymic = rs.getString(4);
            Date birthday = rs.getDate(5);
            String pSeries = rs.getString(6);
            String pNumber = rs.getString(7);
            String whoIssued = rs.getString(8);
            Date whenIssued = rs.getDate(9);
            passportObj = new Passport(passportID, lastName, firstName,
                    patronymic, birthday, pSeries, pNumber,
                    whoIssued, whenIssued);
            Lgr.LOGGER.info("Data selected successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return passportObj;
    }

}
