package com.bus.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.custom.ConsoleColors;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;
import com.bus.exceptions.CustomerException;

public class CusLoginusecase {

	public static Customer CusLogin() {

		Customer customer = null;

		try {

			Scanner sc = new Scanner(System.in);

			String username;
			String password;
			boolean isValidUsername = false;
			boolean isValidPassword = false;

			// Username validation loop
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter Username" + ConsoleColors.RESET);
				username = sc.next();

				if (username.length() >= 5 && username.length() <= 20 && username.matches("[a-zA-Z0-9]+") && !username.contains(" ")) {
					isValidUsername = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid username! Must be at least 5 characters long and contain only letters and numbers." + ConsoleColors.RESET);
				}

			} while (!isValidUsername);

			// Password validation loop (example: at least 8 characters)
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter Password" + ConsoleColors.RESET);
				password = sc.next();

				if (password.length() >= 8 && !password.contains(" ")) {
					isValidPassword = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid password! Must be at least 8 characters long." + ConsoleColors.RESET);
				}

			} while (!isValidPassword);

			CustomerDao dao = new CustomerDaoImpl();

			try {
				customer = dao.cusLogin(username, password);

				System.out.println(ConsoleColors.ROSY_PINK + "Welcome " + customer.getFirstName() + " " + customer.getLastName() + ConsoleColors.RESET);
			} catch (CustomerException e) {
				System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + e.getMessage() + ConsoleColors.RESET);
		}

		return customer;
	}
}

