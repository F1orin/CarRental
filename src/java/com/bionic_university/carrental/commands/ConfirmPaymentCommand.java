/*
 * ConfirmPaymentCommand.java 2014/04/05
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.commands;

import com.bionic_university.carrental.util.CommandHelper;
import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.config.ConfigManager;
import com.bionic_university.carrental.dao.DAOHelper;
import com.bionic_university.carrental.daofactory.DAOFactory;
import com.bionic_university.carrental.entities.Order;
import com.bionic_university.carrental.exceptions.SessionTimeoutException;
import com.bionic_university.carrental.idao.IOrderDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class that represents command to confirm that payment for certain order has
 * been received.
 *
 * @author Florin
 */
public class ConfirmPaymentCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res,
            HttpSession session) throws ServletException, IOException {
        Lgr.LOGGER.info("Command called: " + this.getClass().getSimpleName());
        String page;
        try {
            CommandHelper.validateSession(session);

            IOrderDAO orderDAO = DAOFactory.getOrderDAO();
            Order order = orderDAO.findByID(Integer.valueOf(req.
                    getParameter(REQ_PARAM_ORDER_ID)));
            order.setPaid(true);
            int updateOrderCode = orderDAO.update(order);
            if (updateOrderCode == DAOHelper.EXECUTE_UPDATE_ERROR_CODE) {
                throw new IllegalArgumentException("Order entry in DB was not updated");
            }

            page = ConfigManager.getInstance()
                    .getProperty(ConfigManager.ADMIN_PAGE_PATH);
        } catch (SessionTimeoutException e) {
            Lgr.LOGGER.error("session timed out: " + e);
            req.setAttribute(SESS_PARAM_ERROR_MESSAGE, SESSION_TIMEOUT_ERROR_MESSAGE);
            page = ConfigManager.getInstance()
                    .getProperty(ConfigManager.ERROR_PAGE_PATH);
        } catch (IllegalArgumentException e) {
            Lgr.LOGGER.error("Error while updating order " + e);
            req.setAttribute(SESS_PARAM_ERROR_MESSAGE, ORDER_NOT_UPDATED_ERROR_MESSAGE);
            page = ConfigManager.getInstance()
                    .getProperty(ConfigManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
