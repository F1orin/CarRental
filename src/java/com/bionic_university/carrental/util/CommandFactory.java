/*
 * CommandFactory.java 2014/04/10
 *
 * Copyright (C) 2014 Florin.
 *
 */
package com.bionic_university.carrental.util;

import com.bionic_university.carrental.commands.AdminZoneButtonCommand;
import com.bionic_university.carrental.commands.CalculateCostCommand;
import com.bionic_university.carrental.commands.ConfirmOrderCommand;
import com.bionic_university.carrental.commands.ConfirmPaymentCommand;
import com.bionic_university.carrental.commands.CreateOrderCommand;
import com.bionic_university.carrental.commands.GiveVehicleCommand;
import com.bionic_university.carrental.commands.HomeButtonCommand;
import com.bionic_university.carrental.commands.ICommand;
import com.bionic_university.carrental.commands.LoadOrderListCommand;
import com.bionic_university.carrental.commands.LogInCommand;
import com.bionic_university.carrental.commands.LogOutCommand;
import com.bionic_university.carrental.commands.MakeOrderButtonCommand;
import com.bionic_university.carrental.commands.NoCommand;
import com.bionic_university.carrental.commands.RegisterCommand;
import com.bionic_university.carrental.commands.RejectOrderCommand;
import com.bionic_university.carrental.commands.ResetOrderCommand;
import com.bionic_university.carrental.commands.ReturnDamagedVehicleCommand;
import com.bionic_university.carrental.commands.ReturnVehicleCommand;
import com.bionic_university.carrental.commands.SelectOrderCommand;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 * Class is a factory method that produces an instance of the proper command
 *
 * @author Florin
 */
public class CommandFactory {

    private static CommandFactory instance;
    HashMap<String, ICommand> commands = new HashMap<>();

    private CommandFactory() {
        //filling the map with available commands
        commands.put("login", new LogInCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("homeButton", new HomeButtonCommand());
        commands.put("registration", new RegisterCommand());

        commands.put("makeOrderButton", new MakeOrderButtonCommand());
        commands.put("adminZoneButton", new AdminZoneButtonCommand());

        commands.put("calculateCost", new CalculateCostCommand());
        commands.put("createOrder", new CreateOrderCommand());

        commands.put("loadOrderList", new LoadOrderListCommand());
        commands.put("selectOrder", new SelectOrderCommand());

        commands.put("confirmOrder", new ConfirmOrderCommand());
        commands.put("rejectOrder", new RejectOrderCommand());
        commands.put("giveVehicle", new GiveVehicleCommand());
        commands.put("returnVehicle", new ReturnVehicleCommand());
        commands.put("returnDamagedVehicle", new ReturnDamagedVehicleCommand());
        commands.put("confirmPayment", new ConfirmPaymentCommand());
        commands.put("resetOrder", new ResetOrderCommand());
    }

    public static synchronized CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public ICommand getCommand(HttpServletRequest req) {
        String action = req.getParameter("command");
        ICommand command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
