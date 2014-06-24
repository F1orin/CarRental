/*
 * IUserDAO.java 2014/03/30
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.idao;

import com.bionic_university.carrental.entities.User;
import java.util.List;

/**
 * An interface for User DAO
 *
 * @author Florin
 */
public interface IUserDAO {

    public int insert(User user);

    public int update(User user);

    public int delete(User user);

    public List<User> findAll();

    public User findByLogin(String loginParam);

    public User findByID(int userIDParam);
}
