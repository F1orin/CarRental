/*
 * LogInCommand.java 2014/04/12
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.commands;

import com.bionic_university.carrental.util.Lgr;
import com.bionic_university.carrental.config.ConfigManager;
import com.bionic_university.carrental.daofactory.DAOFactory;
import com.bionic_university.carrental.entities.User;
import com.bionic_university.carrental.idao.IUserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class that represents command to log in.
 *
 * @author Florin
 */
public class LogInCommand implements ICommand {

    private static final int LOGIN_ERROR = -1;
    private static final int ACC_TYPE_ADMIN = 1;
    static final int ACC_TYPE_CLIENT = 2;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res,
            HttpSession session) throws ServletException, IOException {
        Lgr.LOGGER.info("Command called: " + this.getClass().getSimpleName());

        //get DAO and input data
        IUserDAO userDAO = DAOFactory.getUserDAO();
        String login = req.getParameter(REQ_PARAM_LOGIN);
        String password = req.getParameter(REQ_PARAM_PASSWORD);

        //declare variables
        String page;
        User user;
        int userID;

        //check the login information
        switch (checkLogin(login, password)) {
            case ACC_TYPE_CLIENT:
                session.setAttribute(SESS_PARAM_USER_NAME, login);
                session.setAttribute(SESS_PARAM_USERTYPE_ID, ACC_TYPE_CLIENT);
                user = userDAO.findByLogin(login);
                userID = user.getUserID();
                session.setAttribute(SESS_PARAM_USER_ID, userID);
                page = ConfigManager.getInstance()
                        .getProperty(ConfigManager.INDEX_PAGE_PATH);
                Lgr.LOGGER.info("User " + user.getLogin() + " logged in");
                break;
            case ACC_TYPE_ADMIN:
                session.setAttribute(SESS_PARAM_USER_NAME, login);
                session.setAttribute(SESS_PARAM_USERTYPE_ID, ACC_TYPE_ADMIN);
                user = userDAO.findByLogin(login);
                userID = user.getUserID();
                session.setAttribute(SESS_PARAM_USER_ID, userID);
                page = ConfigManager.getInstance()
                        .getProperty(ConfigManager.INDEX_PAGE_PATH);
                Lgr.LOGGER.info("Admin " + user.getLogin() + " logged in");
                break;
            case LOGIN_ERROR:
                req.setAttribute(SESS_PARAM_ERROR_MESSAGE, LOGIN_ERROR_MESSAGE);
                page = ConfigManager.getInstance()
                        .getProperty(ConfigManager.ERROR_PAGE_PATH);
                Lgr.LOGGER.error(login + " login tryout failed");
                break;
            default:
                page = null;
                break;
        }
        return page;
    }

    //auxiliary method for checking the login and password correspondance
    private int checkLogin(String login, String password) {
        Lgr.LOGGER.debug("checkLogin called");
        IUserDAO userDAO = DAOFactory.getUserDAO();
        User user = userDAO.findByLogin(login);
        if ((user == null) || !(user.getPassword().equals(password))) {
            return LOGIN_ERROR;
        } else {
            return user.getUserTypeID();
        }
    }
}
