package com.bus.usecases;

import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.custom.ConsoleColors;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;
import com.bus.exceptions.BusException;

public class CancelTicketbNameusecase {

	public static void cancelTicket(Customer customer) {

		Scanner sc = new Scanner(System.in);

		String bName;
		boolean isValidBusName = false;
		do {
			System.out.println(ConsoleColors.ORANGE + "Enter Bus Name" + ConsoleColors.RESET);
			bName = sc.nextLine();

			if (!bName.isEmpty() && bName.matches("[a-zA-Z]+")) {
				isValidBusName = true;
			} else {
				System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid bus name! Must contain only letters." + ConsoleColors.RESET);
			}

		} while (!isValidBusName);

		CustomerDao dao = new CustomerDaoImpl();
		try {
			int cusId = customer.getCusId();
			String message = dao.cancelTicket(bName, cusId);

			if ("Ticket cancelled Successfully".equalsIgnoreCase(message.trim())) {
				System.out.println(ConsoleColors.GREEN_BACKGROUND + message + ConsoleColors.RESET);
			} else {
				System.out.println(ConsoleColors.RED_BACKGROUND + message + ConsoleColors.RESET);
			}

		} catch (BusException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
		}
	}
}

