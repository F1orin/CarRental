/*
 * IPassportDAO.java 2014/03/30
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.idao;

import com.bionic_university.carrental.entities.Passport;
import java.util.List;

/**
 * An interface for Passport DAO
 *
 * @author Florin
 */
public interface IPassportDAO {

    public int insert(Passport passport);

    public int update(Passport passport);

    public int delete(Passport passport);

    public List<Passport> findAll();

    public Passport findByID(int passportIDParam);
}
