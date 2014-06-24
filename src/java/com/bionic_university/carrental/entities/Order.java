/*
 * Order.java 2014/03/29
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A bean for "orders" table
 *
 * @author Florin
 */
public class Order implements Serializable {

    private int orderID;
    private Vehicle vehicle;
    private User user;
    private Passport passport;
    private Timestamp pickUpDate;
    private Timestamp dropOffDate;
    private BigDecimal rentCost;
    private boolean processed;
    private boolean rejected;
    private String rejectDesc;
    private boolean picked;
    private boolean returned;
    private boolean damaged;
    private String damageDesc;
    private BigDecimal damageCost;
    private boolean paid;

    public Order() {
    }

    public Order(int orderID, Vehicle vehicle, User user, Passport passport,
            Timestamp pickUpDate, Timestamp dropOffDate, BigDecimal rentCost,
            boolean processed, boolean rejected, String rejectDesc,
            boolean picked, boolean returned, boolean damaged,
            String damageDesc, BigDecimal damageCost, boolean paid) {
        this.orderID = orderID;
        this.vehicle = vehicle;
        this.user = user;
        this.passport = passport;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.rentCost = rentCost;
        this.processed = processed;
        this.rejected = rejected;
        this.rejectDesc = rejectDesc;
        this.picked = picked;
        this.returned = returned;
        this.damaged = damaged;
        this.damageDesc = damageDesc;
        this.damageCost = damageCost;
        this.paid = paid;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Timestamp getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Timestamp pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Timestamp getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Timestamp dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public BigDecimal getRentCost() {
        return rentCost;
    }

    public void setRentCost(BigDecimal rentCost) {
        this.rentCost = rentCost;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public String getRejectDesc() {
        return rejectDesc;
    }

    public void setRejectDesc(String rejectDesc) {
        this.rejectDesc = rejectDesc;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public String getDamageDesc() {
        return damageDesc;
    }

    public void setDamageDesc(String damageDesc) {
        this.damageDesc = damageDesc;
    }

    public BigDecimal getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(BigDecimal damageCost) {
        this.damageCost = damageCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("orderID", orderID)
                .append("vehicle", vehicle)
                .append("user", user)
                .append("passport", passport)
                .append("pickUpDate", pickUpDate)
                .append("dropOffDate", dropOffDate)
                .append("rentCost", rentCost)
                .append("processed", processed)
                .append("rejected", rejected)
                .append("rejectDesc", rejectDesc)
                .append("picked", picked)
                .append("returned", returned)
                .append("damaged", damaged)
                .append("damageDesc", damageDesc)
                .append("damageCost", damageCost)
                .append("paid", paid)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(orderID)
                .append(vehicle)
                .append(user)
                .append(passport)
                .append(pickUpDate)
                .append(dropOffDate)
                .append(rentCost)
                .append(processed)
                .append(rejected)
                .append(rejectDesc)
                .append(picked)
                .append(returned)
                .append(damaged)
                .append(damageDesc)
                .append(damageCost)
                .append(paid)
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
        final Order other = (Order) obj;
        return new EqualsBuilder()
                .append(orderID, other.orderID)
                .append(vehicle, other.vehicle)
                .append(user, other.user)
                .append(passport, other.passport)
                .append(pickUpDate, other.pickUpDate)
                .append(dropOffDate, other.dropOffDate)
                .append(rentCost, other.rentCost)
                .append(processed, other.processed)
                .append(rejected, other.rejected)
                .append(rejectDesc, other.rejectDesc)
                .append(picked, other.picked)
                .append(returned, other.returned)
                .append(damaged, other.damaged)
                .append(damageDesc, other.damageDesc)
                .append(damageCost, other.damageCost)
                .append(paid, other.paid)
                .isEquals();
    }

}
