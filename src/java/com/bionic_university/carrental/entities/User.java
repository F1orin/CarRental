/*
 * User.java 2014/03/29
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.entities;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A bean for "users" table
 *
 * @author Florin
 */
public class User implements Serializable {

    private int userID;
    private int userTypeID;
    private String login;
    private String password;

    public User() {
    }

    public User(int userID, int userTypeID, String login, String password) {
        this.userID = userID;
        this.userTypeID = userTypeID;
        this.login = login;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("userID", userID)
                .append("userTypeID", userTypeID)
                .append("login", login)
                .append("password", "****")
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userID)
                .append(userTypeID)
                .append(login)
                .append(password)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return new EqualsBuilder()
                .append(userID, other.userID)
                .append(userTypeID, other.userTypeID)
                .append(login, other.login)
                .append(password, other.password)
                .isEquals();
    }

}
