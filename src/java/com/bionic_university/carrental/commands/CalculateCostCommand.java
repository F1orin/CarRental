/*
 * CalculateCostCommand.java 2014/04/05
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.commands;

import com.bionic_university.carrental.util.CommandHelper;
import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.config.ConfigManager;
import com.bionic_university.carrental.daofactory.DAOFactory;
import com.bionic_university.carrental.exceptions.SessionTimeoutException;
import com.bionic_university.carrental.idao.IVehicleDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Class that represents command to calculate the rent cost for specified
 * vehicle and dates.
 *
 * @author Florin
 */
public class CalculateCostCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res,
            HttpSession session) throws ServletException, IOException {
        Lgr.LOGGER.info("Command called: " + this.getClass().getSimpleName());
        String page;
        try {
            CommandHelper.validateSession(session);

            int vehicleID = Integer.valueOf(req.getParameter(REQ_PARAM_VEHICLE_CHOICE));
            req.setAttribute(REQ_PARAM_VEHICLE_ID, vehicleID);

            String tmpPick = req.getParameter(REQ_PARAM_PICK_UP_DATE);
            String tmpDrop = req.getParameter(REQ_PARAM_DROP_OFF_DATE);
            Timestamp pick = Timestamp.valueOf(convertDateFormat(tmpPick));
            Timestamp drop = Timestamp.valueOf(convertDateFormat(tmpDrop));
            req.setAttribute(REQ_PARAM_PICK_UP_DATE, tmpPick);
            req.setAttribute(REQ_PARAM_DROP_OFF_DATE, tmpDrop);

            IVehicleDAO vehicleDAO = DAOFactory.getVehicleDAO();
            int rentInterval = daysBetween(pick, drop);
            BigDecimal dailyPrice = vehicleDAO.findDailyPriceByVehicleID(vehicleID);
            BigDecimal rentCost = calcRentCost(dailyPrice, rentInterval);
            req.setAttribute(REQ_PARAM_RENT_COST, rentCost);

            page = ConfigManager.getInstance()
                    .getProperty(ConfigManager.ORDER_PAGE_PATH);
        } catch (SessionTimeoutException e) {
            Lgr.LOGGER.error("session timed out: " + e);
            req.setAttribute(SESS_PARAM_ERROR_MESSAGE, SESSION_TIMEOUT_ERROR_MESSAGE);
            page = ConfigManager.getInstance()
                    .getProperty(ConfigManager.ERROR_PAGE_PATH);
        }
        return page;
    }

    //auxiliary method for counting number of days between two Timestamp objects
    private int daysBetween(Timestamp ts1, Timestamp ts2) {
        DateTime firstDateTime;
        DateTime secondDateTime;
        if (ts2.after(ts1)) {
            firstDateTime = new DateTime(ts1.getTime());
            secondDateTime = new DateTime(ts2.getTime());
        } else {
            Lgr.LOGGER.warn("Second parameter date is before first");
            firstDateTime = new DateTime(ts2.getTime());
            secondDateTime = new DateTime(ts1.getTime());
        }
        int dayDifference = Days.daysBetween(firstDateTime.withTimeAtStartOfDay(),
                secondDateTime.withTimeAtStartOfDay()).getDays();
        return dayDifference;
    }

    //auxiliary method for calculating rent cost based on daily price and number
    //of days
    private BigDecimal calcRentCost(BigDecimal dailyPrice, int days) {
        return dailyPrice.multiply(new BigDecimal(days));
    }

    //auxiliary method for converting date format from HTML to Java
    static String convertDateFormat(String htmlDate) {
        String[] separateDateTime = htmlDate.split("T");
        StringBuilder sb = new StringBuilder();
        sb.append(separateDateTime[0]);
        sb.append(" ");
        sb.append(separateDateTime[1]);
        sb.append(":00");
        return sb.toString();
    }
}
