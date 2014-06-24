/*
 * Vehicle.java 2014/03/29
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A bean for "vehicles" table
 *
 * @author Florin
 */
public class Vehicle implements Serializable {

    private int vehicleID;
    private String make;
    private String model;
    private boolean autoGearbox;
    private boolean airConditioner;
    private int seats;
    private BigDecimal dailyPrice;

    public Vehicle() {
    }

    public Vehicle(int vehicleID, String make, String model,
            boolean autoGearbox, boolean airConditioner,
            int seats, BigDecimal dailyPrice) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.autoGearbox = autoGearbox;
        this.airConditioner = airConditioner;
        this.seats = seats;
        this.dailyPrice = dailyPrice;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAutoGearbox() {
        return autoGearbox;
    }

    public void setAutoGearbox(boolean autoGearbox) {
        this.autoGearbox = autoGearbox;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    /**
     * Methods that generates the string representation of vehicle object to be
     * shown on the web page
     *
     * @return specially built string for representing vehicle on web page
     */
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(make).append(" ").append(model);
        if (autoGearbox) {
            sb.append(", Auto");
        } else {
            sb.append(", Manual");
        }
        if (airConditioner) {
            sb.append(", A/C");
        }
        sb.append(", $").append(dailyPrice).append("/day");
        return sb.toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("vehicleID", vehicleID)
                .append("make", make)
                .append("model", model)
                .append("autoGearbox", autoGearbox)
                .append("airConditioner", airConditioner)
                .append("seats", seats)
                .append("dailyPrice", dailyPrice)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(vehicleID)
                .append(make)
                .append(model)
                .append(autoGearbox)
                .append(airConditioner)
                .append(seats)
                .append(dailyPrice)
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
        final Vehicle other = (Vehicle) obj;
        return new EqualsBuilder()
                .append(vehicleID, other.vehicleID)
                .append(make, other.make)
                .append(model, other.model)
                .append(autoGearbox, other.autoGearbox)
                .append(airConditioner, other.airConditioner)
                .append(seats, other.seats)
                .append(dailyPrice, other.dailyPrice)
                .isEquals();
    }

}
