/*
 * UserType.java 2014/03/29
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
 * A bean for "usertypes" table
 *
 * @author Florin
 */
public class UserType implements Serializable {

    private int usertypeID;
    private String usertype;

    public UserType() {
    }

    public UserType(int usertypeID, String usertype) {
        this.usertypeID = usertypeID;
        this.usertype = usertype;
    }

    public int getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(int usertypeID) {
        this.usertypeID = usertypeID;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("usertypeID", usertypeID)
                .append("usertype", usertype)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(usertypeID)
                .append(usertype)
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
        final UserType other = (UserType) obj;
        return new EqualsBuilder()
                .append(usertypeID, other.usertypeID)
                .append(usertype, other.usertype)
                .isEquals();
    }

}
