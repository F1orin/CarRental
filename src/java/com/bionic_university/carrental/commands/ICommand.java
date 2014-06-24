/*
 * ICommand.java 2014/04/10
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Interface for Command classes. Contains the constants that represent
 * variables used on web pages
 *
 * @author Florin
 */
public interface ICommand {

    String SESS_PARAM_USER_NAME = "userName";
    String SESS_PARAM_USER_ID = "userID";
    String SESS_PARAM_USERTYPE_ID = "userTypeID";

    String SESS_PARAM_ERROR_MESSAGE = "errorMessage";
    String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    String SESSION_TIMEOUT_ERROR_MESSAGE = "SESSION_TIMEOUT_ERROR_MESSAGE";
    String ORDER_NOT_CREATED_ERROR_MESSAGE = "ORDER_NOT_CREATED_ERROR_MESSAGE";
    String ORDER_NOT_UPDATED_ERROR_MESSAGE = "ORDER_NOT_UPDATED_ERROR_MESSAGE";
    String REGISTRATION_ERROR_MESSAGE = "REGISTRATION_ERROR_MESSAGE";
    String PASSWORD_CONFIRMATION_ERROR_MESSAGE = "PASSWORD_CONFIRMATION_ERROR_MESSAGE";
    String USER_EXISTS_ERROR_MESSAGE = "USER_EXISTS_ERROR_MESSAGE";
    String UNKNOWN_ERROR_MESSAGE = "UNKNOWN_ERROR_MESSAGE";

    String REQ_PARAM_LOGIN = "login";
    String REQ_PARAM_PASSWORD = "password";
    String REQ_PARAM_PASSWORD_CONFIRM = "passwordConfirm";

    String REQ_PARAM_VEHICLE_LIST = "vehicleList";
    String REQ_PARAM_ORDER_LIST = "orderList";

    String REQ_PARAM_VEHICLE_CHOICE = "vehicleChoice";
    String REQ_PARAM_ORDER_CHOICE = "orderChoice";

    String REQ_PARAM_ORDER_ID = "orderID";
    String REQ_PARAM_VEHICLE_ID = "vehicleID";
    String REQ_PARAM_PICK_UP_DATE = "pickUpDate";
    String REQ_PARAM_DROP_OFF_DATE = "dropOffDate";
    String REQ_PARAM_RENT_COST = "rentCost";
    String REQ_PARAM_REJECT_DESC = "rejectDesc";
    String REQ_PARAM_DAMAGE_DESC = "damageDesc";
    String REQ_PARAM_DAMAGE_COST = "damageCost";

    String REQ_PARAM_LAST_NAME = "lastName";
    String REQ_PARAM_FIRST_NAME = "firstName";
    String REQ_PARAM_PATRONYMIC = "patronymic";
    String REQ_PARAM_BIRTHDAY = "birthday";
    String REQ_PARAM_P_SERIES = "pSeries";
    String REQ_PARAM_P_NUMBER = "pNumber";
    String REQ_PARAM_WHO_ISSUED = "whoIssued";
    String REQ_PARAM_WHEN_ISSUED = "whenIssued";

    public String execute(HttpServletRequest req, HttpServletResponse res,
            HttpSession session)
            throws ServletException, IOException;

}
