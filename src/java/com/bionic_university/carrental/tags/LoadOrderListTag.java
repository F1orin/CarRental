/*
 * LoadOrderListTag.java 2014/04/21
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.tags;

import com.bionic_university.carrental.commands.ICommand;
import com.bionic_university.carrental.daofactory.DAOFactory;
import com.bionic_university.carrental.entities.Order;
import com.bionic_university.carrental.idao.IOrderDAO;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Defines custom tag that loads order list from database to JSP.
 *
 * @author Florin
 * @see TagSupport
 */
public class LoadOrderListTag extends TagSupport {

    /**
     * Loads order list from database to JSP.
     *
     * @return SKIP_BODY
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        IOrderDAO orderDAO = DAOFactory.getOrderDAO();
        List<Order> orders = orderDAO.findAll();
        pageContext.setAttribute(ICommand.REQ_PARAM_ORDER_LIST, orders);
        return SKIP_BODY;
    }

}
