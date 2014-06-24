/*
 * LoadVehicleListTag.java 2014/04/21
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.tags;

import com.bionic_university.carrental.commands.ICommand;
import com.bionic_university.carrental.daofactory.DAOFactory;
import com.bionic_university.carrental.entities.Vehicle;
import com.bionic_university.carrental.idao.IVehicleDAO;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Defines custom tag that loads vehicle list from database to JSP.
 *
 * @author Florin
 * @see TagSupport
 */
public class LoadVehicleListTag extends TagSupport {

    /**
     * Loads vehicle list from database to JSP.
     *
     * @return SKIP_BODY
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        IVehicleDAO vehicleDAO = DAOFactory.getVehicleDAO();
        List<Vehicle> vehicleList = vehicleDAO.findAll();
        pageContext.setAttribute(ICommand.REQ_PARAM_VEHICLE_LIST, vehicleList);
        return SKIP_BODY;
    }
}
