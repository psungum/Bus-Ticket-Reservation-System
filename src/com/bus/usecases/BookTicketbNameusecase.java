package com.bus.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.custom.ConsoleColors;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;
import com.bus.exceptions.BusException;

public class BookTicketbNameusecase {

	public static void BookTicketbName(Customer customer) {

		Scanner sc = new Scanner(System.in);

		String bName;
		boolean isValidBusName = false;
		do {
			System.out.println(ConsoleColors.ORANGE + "Enter Bus Name" + ConsoleColors.RESET);
			bName = sc.nextLine();

			if (!bName.isEmpty() && bName.length() >= 5 && bName.length() <= 15 && bName.matches("[a-zA-Z ]+")) {
				isValidBusName = true;
			} else {
				System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid bus name! Must be 5-15 characters long and contain only letters and spaces." + ConsoleColors.RESET);
			}


		} while (!isValidBusName);

		CustomerDao dao = new CustomerDaoImpl();
		try {
			int no = 0;
			boolean isValidTicketNumber = false;
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter the number of tickets to book" + ConsoleColors.RESET);
				try {
					no = sc.nextInt();

					if (no > 0) {
						isValidTicketNumber = true;
					} else {
						System.out.println(ConsoleColors.RED_BACKGROUND + "The number of tickets must be a positive integer." + ConsoleColors.RESET);
					}
				} catch (InputMismatchException e) {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input! Please enter a valid number of tickets." + ConsoleColors.RESET);
					sc.next(); // Clear the invalid input
				}

			} while (!isValidTicketNumber);

			int cusId = customer.getCusId();
			String message = dao.bookTicket(bName, cusId, no);

			if ("Ticket Booked Successfully".equalsIgnoreCase(message.trim())) {
				System.out.println(ConsoleColors.GREEN_BACKGROUND + message + ConsoleColors.RESET);
			} else {
				System.out.println(ConsoleColors.RED_BACKGROUND + message + ConsoleColors.RESET);
			}

		} catch (BusException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input" + ConsoleColors.RESET);
		}
	}
}

