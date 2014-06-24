/*
 * NoCommand.java 2014/04/10
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.commands;

import com.bionic_university.carrental.config.ConfigManager;
import com.bionic_university.carrental.util.Lgr;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class that represents a command with no parameter that forwards user to index
 * page.
 *
 * @author Florin
 */
public class NoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res,
            HttpSession session) throws ServletException, IOException {
        Lgr.LOGGER.info("Command called: " + this.getClass().getSimpleName());
        String page = ConfigManager.getInstance()
                .getProperty(ConfigManager.INDEX_PAGE_PATH);
        return page;
    }
}
