/*
 * VehicleDAOImpl.java 2014/03/30
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.dao;

import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.entities.Vehicle;
import com.bionic_university.carrental.idao.IVehicleDAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for "vehicles" table
 *
 * @author Florin
 */
public class VehicleDAOImpl implements IVehicleDAO {

    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    private final String TABLE_NAME = "vehicles";
    private final String COL_1 = "vehicle_id";
    private final String COL_2 = "make";
    private final String COL_3 = "model";
    private final String COL_4 = "auto_gearbox";
    private final String COL_5 = "air_conditioner";
    private final String COL_6 = "seats";
    private final String COL_7 = "daily_price";

    private final String INSERT_QUERY;
    private final String UPDATE_QUERY;
    private final String DELETE_QUERY;
    private final String SELECT_QUERY;
    private final String SELECT_DAILY_PRICE_QUERY;

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
                .append(COL_7)
                .append(") VALUES ")
                .append("(?,?,?,?,?,?)")
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
                .append(COL_7).append("=?")
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
                .append("SELECT ")
                .append("*")
                .append(" FROM ")
                .append(TABLE_NAME)
                .toString();
        SELECT_DAILY_PRICE_QUERY = new StringBuffer()
                .append("SELECT ")
                .append(COL_7)
                .append(" FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(COL_1).append("=?")
                .toString();
    }

    @Override
    public int insert(Vehicle vehicle) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(INSERT_QUERY);
            ps.setString(1, vehicle.getMake());
            ps.setString(2, vehicle.getModel());
            ps.setBoolean(3, vehicle.isAutoGearbox());
            ps.setBoolean(4, vehicle.isAirConditioner());
            ps.setInt(5, vehicle.getSeats());
            ps.setBigDecimal(6, vehicle.getDailyPrice());
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
    public int update(Vehicle vehicle) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(UPDATE_QUERY);
            ps.setString(1, vehicle.getMake());
            ps.setString(2, vehicle.getModel());
            ps.setBoolean(3, vehicle.isAutoGearbox());
            ps.setBoolean(4, vehicle.isAirConditioner());
            ps.setInt(5, vehicle.getSeats());
            ps.setBigDecimal(6, vehicle.getDailyPrice());
            ps.setInt(7, vehicle.getVehicleID());
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
    public int delete(Vehicle vehicle) {
        int result = DAOHelper.EXECUTE_UPDATE_ERROR_CODE;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(DELETE_QUERY);
            ps.setInt(1, vehicle.getVehicleID());
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
    public List<Vehicle> findAll() {
        List<Vehicle> list = new ArrayList<>();
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                int vehicleID = rs.getInt(1);
                String make = rs.getString(2);
                String model = rs.getString(3);
                boolean autoGearbox = rs.getBoolean(4);
                boolean airConditioner = rs.getBoolean(5);
                int seats = rs.getInt(6);
                BigDecimal dailyPrice = rs.getBigDecimal(7);
                Vehicle vehicleObj = new Vehicle(vehicleID, make, model,
                        autoGearbox, airConditioner, seats, dailyPrice);
                list.add(vehicleObj);
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
    public Vehicle findByID(int vehicleIDParam) {
        Vehicle vehicleObj = null;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_QUERY + " WHERE vehicle_id=?");
            ps.setInt(1, vehicleIDParam);
            rs = ps.executeQuery();
            rs.next();
            int vehicleID = rs.getInt(1);
            String make = rs.getString(2);
            String model = rs.getString(3);
            boolean autoGearbox = rs.getBoolean(4);
            boolean airConditioner = rs.getBoolean(5);
            int seats = rs.getInt(6);
            BigDecimal dailyPrice = rs.getBigDecimal(7);
            vehicleObj = new Vehicle(vehicleID, make, model, autoGearbox,
                    airConditioner, seats, dailyPrice);
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return vehicleObj;
    }

    @Override
    public BigDecimal findDailyPriceByVehicleID(int vehicleID) {
        BigDecimal dailyPrice = null;
        try {
            cn = DAOHelper.getConnection();
            ps = cn.prepareStatement(SELECT_DAILY_PRICE_QUERY);
            ps.setInt(1, vehicleID);
            rs = ps.executeQuery();
            rs.next();
            dailyPrice = rs.getBigDecimal(1);
            Lgr.LOGGER.info("Data selected successfully");
        } catch (SQLException e) {
            Lgr.LOGGER.error(e);
        } finally {
            DAOHelper.closeResources(cn, ps, rs);
        }
        return dailyPrice;
    }

}
