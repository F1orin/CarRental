/*
 * OrderDAOImpl.java 2014/03/30
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.dao;

import com.bionic_university.carrental.daofactory.DAOFactory;
import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.entities.Order;
import com.bionic_university.carrental.entities.Passport;
import com.bionic_university.carrental.entities.User;
import com.bionic_university.carrental.entities.Vehicle;
import com.bionic_university.carrental.idao.IOrderDAO;
import com.bionic_university.carrental.idao.IPassportDAO;
import com.bionic_university.carrental.idao.IUserDAO;
import com.bionic_university.carrental.idao.IVehicleDAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for "orders" table
 *
 * @author Florin
 */
public class OrderDAOImpl implements IOrderDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final String TABLE_NAME = "orders";
    private final String COL_1 = "order_id";
    private final String COL_2 = "vehicle_id";
    private final String COL_3 = "user_id";
    private final String COL_4 = "passport_id";
    private final String COL_5 = "pick_up_date";
    private final String COL_6 = "drop_off_date";
    private final String COL_7 = "rent_cost";
    private final String COL_8 = "processed";
    private final String COL_9 = "rejected";
    private final String COL_10 = "reject_desc";
    private final String COL_11 = "picked";
    private final String COL_12 = "returned";
    private final String COL_13 = "damaged";
    private final String COL_14 = "damage_desc";
    private final String COL_15 = "damage_cost";
    private final String COL_16 = "paid";

    private final String INSERT_QUERY;
    private final String UPDATE_QUERY;
    private final String DELETE_QUERY;
    private final String SELECT_QUERY;

    private final IVehicleDAO VEHICLE_DAO = DAOFactory.getVehicleDAO();
    private final IUserDAO USER_DAO = DAOFactory.getUserDAO();
    private final IPassportDAO PASSPORT_DAO = DAOFactory.getPassportDAO();

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
                .append(COL_9).append(",")
                .append(COL_10).append(",")
                .append(COL_11).append(",")
                .append(COL_12).append(",")
                .append(COL_13).append(",")
                .append(COL_14).append(",")
                .append(COL_15).append(",")
                .append(COL_16)
                .append(") VALUES ")
                .append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
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
                .append(COL_9).append("=?").append(",")
                .append(COL_10).append("=?").append(",")
                .append(COL_11).append("=?").append(",")
                .append(COL_12).append("=?").append(",")
                .append(COL_13).append("=?").append(",")
                .append(COL_14).append("=?").append(",")
                .append(COL_15).append("=?").append(",")
                .append(COL_16).append("=?")
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
    public int insert(Order order) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(INSERT_QUERY);
            ps.setInt(1, order.getVehicle().getVehicleID());
            ps.setInt(2, order.getUser().getUserID());
            ps.setInt(3, order.getPassport().getPassportID());
            ps.setTimestamp(4, order.getPickUpDate());
            ps.setTimestamp(5, order.getDropOffDate());
            ps.setBigDecimal(6, order.getRentCost());
            ps.setBoolean(7, order.isProcessed());
            ps.setBoolean(8, order.isRejected());
            ps.setString(9, order.getRejectDesc());
            ps.setBoolean(10, order.isPicked());
            ps.setBoolean(11, order.isReturned());
            ps.setBoolean(12, order.isDamaged());
            ps.setString(13, order.getDamageDesc());
            ps.setBigDecimal(14, order.getDamageCost());
            ps.setBoolean(15, order.isPaid());
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
    public int update(Order order) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(UPDATE_QUERY);
            ps.setInt(1, order.getVehicle().getVehicleID());
            ps.setInt(2, order.getUser().getUserID());
            ps.setInt(3, order.getPassport().getPassportID());
            ps.setTimestamp(4, order.getPickUpDate());
            ps.setTimestamp(5, order.getDropOffDate());
            ps.setBigDecimal(6, order.getRentCost());
            ps.setBoolean(7, order.isProcessed());
            ps.setBoolean(8, order.isRejected());
            ps.setString(9, order.getRejectDesc());
            ps.setBoolean(10, order.isPicked());
            ps.setBoolean(11, order.isReturned());
            ps.setBoolean(12, order.isDamaged());
            ps.setString(13, order.getDamageDesc());
            ps.setBigDecimal(14, order.getDamageCost());
            ps.setBoolean(15, order.isPaid());
            ps.setInt(16, order.getOrderID());
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
    public int delete(Order order) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(DELETE_QUERY);
            ps.setInt(1, order.getOrderID());
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
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();

        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                int vehicleID = rs.getInt(2);
                Vehicle vehicle = VEHICLE_DAO.findByID(vehicleID);
                int userID = rs.getInt(3);
                User user = USER_DAO.findByID(userID);
                int passportID = rs.getInt(4);
                Passport passport = PASSPORT_DAO.findByID(passportID);
                Timestamp pickUpDate = rs.getTimestamp(5);
                Timestamp dropOffDate = rs.getTimestamp(6);
                BigDecimal rentCost = rs.getBigDecimal(7);
                boolean processed = rs.getBoolean(8);
                boolean rejected = rs.getBoolean(9);
                String rejectDesc = rs.getString(10);
                boolean picked = rs.getBoolean(11);
                boolean returned = rs.getBoolean(12);
                boolean damaged = rs.getBoolean(13);
                String damageDesc = rs.getString(14);
                BigDecimal damageCost = rs.getBigDecimal(15);
                boolean paid = rs.getBoolean(16);
                Order orderObj = new Order(orderID, vehicle, user,
                        passport, pickUpDate, dropOffDate, rentCost,
                        processed, rejected, rejectDesc, picked, returned,
                        damaged, damageDesc, damageCost, paid);
                list.add(orderObj);
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
    public Order findByID(int orderIDParam) {
        Order orderObj = null;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_QUERY + " WHERE order_id=?");
            ps.setInt(1, orderIDParam);
            rs = ps.executeQuery();
            rs.next();
            int orderID = rs.getInt(1);
            int vehicleID = rs.getInt(2);
            Vehicle vehicle = VEHICLE_DAO.findByID(vehicleID);
            int userID = rs.getInt(3);
            User user = USER_DAO.findByID(userID);
            int passportID = rs.getInt(4);
            Passport passport = PASSPORT_DAO.findByID(passportID);
            Timestamp pickUpDate = rs.getTimestamp(5);
            Timestamp dropOffDate = rs.getTimestamp(6);
            BigDecimal rentCost = rs.getBigDecimal(7);
            boolean processed = rs.getBoolean(8);
            boolean rejected = rs.getBoolean(9);
            String rejectDesc = rs.getString(10);
            boolean picked = rs.getBoolean(11);
            boolean returned = rs.getBoolean(12);
            boolean damaged = rs.getBoolean(13);
            String damageDesc = rs.getString(14);
            BigDecimal damageCost = rs.getBigDecimal(15);
            boolean paid = rs.getBoolean(16);
            orderObj = new Order(orderID, vehicle, user,
                    passport, pickUpDate, dropOffDate, rentCost,
                    processed, rejected, rejectDesc, picked, returned,
                    damaged, damageDesc, damageCost, paid);
            Lgr.LOGGER.info("Data selected successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return orderObj;
    }
}
