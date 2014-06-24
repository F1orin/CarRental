/*
 * CarRentalServlet.java 2014/04/10
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.servlet;

import com.bionic_university.carrental.util.CommandFactory;
import com.bionic_university.carrental.commands.ICommand;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application's main servlet
 *
 * @author Florin
 */
public class CarRentalServlet extends HttpServlet {

//    private static final int SESSION_TIMEOUT = 5; //in seconds
    private final CommandFactory COMMAND_FACTORY = CommandFactory.getInstance();
    private boolean flag = true;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session;
        if (flag) {
            session = req.getSession();
//            session.setMaxInactiveInterval(SESSION_TIMEOUT);
            flag = false;
        } else {
            session = req.getSession(false);
        }
        ICommand command = COMMAND_FACTORY.getCommand(req);
        String page = command.execute(req, res, session);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(page);
        dispatcher.forward(req, res);
    }

    /**
     * Overriden method for loading Log4j logger properties during servlet
     * initialisation
     */
    @Override
    public void init() {
        ServletContext context = getServletContext();
        String realPath = context.getRealPath("/");
        System.setProperty("rootPath", realPath);
        String file = getInitParameter("log4j-init-file");
        if (file != null) {
            PropertyConfigurator.configure(realPath + file);
            System.out.println("Log4J logging started: " + realPath + file);
        } else {
            System.out.println("Log4J init error: " + realPath + file);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
