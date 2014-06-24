/*
 * Passport.java 2014/03/29
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.entities;

import java.io.Serializable;
import java.sql.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A bean for "passports" table
 *
 * @author Florin
 */
public class Passport implements Serializable {

    private int passportID;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date birthday;
    private String pSeries;
    private String pNumber;
    private String whoIssued;
    private Date whenIssued;

    public Passport() {
    }

    public Passport(int passportID, String lastName, String firstName,
            String patronymic, Date birthday, String pSeries,
            String pNumber, String whoIssued, Date whenIssued) {
        this.passportID = passportID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.pSeries = pSeries;
        this.pNumber = pNumber;
        this.whoIssued = whoIssued;
        this.whenIssued = whenIssued;
    }

    public int getPassportID() {
        return passportID;
    }

    public void setPassportID(int passportID) {
        this.passportID = passportID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getpSeries() {
        return pSeries;
    }

    public void setpSeries(String pSeries) {
        this.pSeries = pSeries;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getWhoIssued() {
        return whoIssued;
    }

    public void setWhoIssued(String whoIssued) {
        this.whoIssued = whoIssued;
    }

    public Date getWhenIssued() {
        return whenIssued;
    }

    public void setWhenIssued(Date whenIssued) {
        this.whenIssued = whenIssued;
    }

    /**
     * Methods that generates the string representation of passport object to be
     * shown on the web page
     *
     * @return specially built string for representing passport on web page
     */
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(lastName).append(" ").append(firstName).append("\n");
        if (patronymic != null) {
            sb.append(patronymic).append("\n");
        }
        sb.append(birthday).append("\n");
        sb.append(pSeries).append(pNumber).append("\n");
        sb.append(whoIssued).append(" ").append(whenIssued);
        return sb.toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("passportID", passportID)
                .append("lastName", lastName)
                .append("firstName", firstName)
                .append("patronymic", patronymic)
                .append("birthday", birthday)
                .append("pSeries", pSeries)
                .append("pNumber", pNumber)
                .append("whoIssued", whoIssued)
                .append("whenIssued", whenIssued)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(passportID)
                .append(lastName)
                .append(firstName)
                .append(patronymic)
                .append(birthday)
                .append(pSeries)
                .append(pNumber)
                .append(whoIssued)
                .append(whenIssued)
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
        final Passport other = (Passport) obj;
        return new EqualsBuilder()
                .append(passportID, other.passportID)
                .append(lastName, other.lastName)
                .append(firstName, other.firstName)
                .append(patronymic, other.patronymic)
                .append(birthday, other.birthday)
                .append(pSeries, other.pSeries)
                .append(pNumber, other.pNumber)
                .append(whoIssued, other.whoIssued)
                .append(whenIssued, other.whenIssued)
                .isEquals();
    }

}
