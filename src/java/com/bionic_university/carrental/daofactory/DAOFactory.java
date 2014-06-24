/*
 * DAOFactory.java 2014/03/31
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.daofactory;

import com.bionic_university.carrental.dao.*;
import com.bionic_university.carrental.idao.*;

/**
 * Factory class for creating DAOs
 *
 * @author Florin
 */
public class DAOFactory {

    public static IUserTypeDAO getUserTypeDAO() {
        return new UserTypeDAOImpl();
    }

    public static IUserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    public static IVehicleDAO getVehicleDAO() {
        return new VehicleDAOImpl();
    }

    public static IPassportDAO getPassportDAO() {
        return new PassportDAOImpl();
    }

    public static IOrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }
}
