package com.bus.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bus.bean.Customer;
import com.bus.custom.ConsoleColors;
import com.bus.dao.CustomerDao;
import com.bus.dao.CustomerDaoImpl;

public class CusSignUp2usecase {

	public static boolean cusSignUp() {

		boolean flag = false;

		try {
			Scanner sc = new Scanner(System.in);

			// Username Validation
			String username;
			boolean isValidUsername = false;
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter Username" + ConsoleColors.RESET);
				username = sc.next();

				if (username.length() >= 5 && username.matches("[a-zA-Z0-9]+")) {
					isValidUsername = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid username! Must be at least 5 characters long, contain only letters and numbers." + ConsoleColors.RESET);
				}

			} while (!isValidUsername);

			// Password Validation (at least 8 characters)
			String password;
			boolean isValidPassword = false;
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter Password" + ConsoleColors.RESET);
				password = sc.next();

				if (password.length() >= 8) {
					isValidPassword = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid password! Must be at least 8 characters long." + ConsoleColors.RESET);
				}

			} while (!isValidPassword);

			// First Name Validation (non-empty, letters only)
			String firstName;
			boolean isValidFirstName = false;
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter First Name" + ConsoleColors.RESET);
				firstName = sc.next();

				if (firstName.matches("[a-zA-Z]+") && firstName.length() >= 3) {
					isValidFirstName = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid first name! Must contain only letters." + ConsoleColors.RESET);
				}

			} while (!isValidFirstName);

			// Last Name Validation (non-empty, letters only)
			String lastName;
			boolean isValidLastName = false;
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter Last Name" + ConsoleColors.RESET);
				lastName = sc.next();

				if (lastName.matches("[a-zA-Z]+") && firstName.length() >= 3 ) {
					isValidLastName = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid last name! Must contain only letters." + ConsoleColors.RESET);
				}

			} while (!isValidLastName);

			// Address Validation (non-empty)
			sc.nextLine();
			String address;
			boolean isValidAddress = false;
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter Address" + ConsoleColors.RESET);
				address = sc.nextLine();

				if ( address.length() >= 3) {
					isValidAddress = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid address! Must not be empty." + ConsoleColors.RESET);
				}

			} while (!isValidAddress);

			// Mobile Validation (10 digits)
			String mobile;
			boolean isValidMobile = false;
			do {
				System.out.println(ConsoleColors.ORANGE + "Enter Mobile" + ConsoleColors.RESET);
				mobile = sc.next();

				if (mobile.matches("\\d{8}")) {
					isValidMobile = true;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid mobile number! Must be exactly 8 digits." + ConsoleColors.RESET);
				}

			} while (!isValidMobile);

			// Creating Customer object and attempting sign-up
			CustomerDao dao = new CustomerDaoImpl();
			Customer customer = new Customer(username, password, firstName, lastName, address, mobile);

			String result = dao.cusSignUp(customer);

			if (result == "Sign up Successfull") {
				flag = true;
				System.out.println(ConsoleColors.GREEN_BACKGROUND + result + ConsoleColors.RESET);
			}
			else {
				System.out.println(ConsoleColors.RED_BACKGROUND + result + ConsoleColors.RESET);
			}
		}

		catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid Input" + ConsoleColors.RESET);
		}

		return flag;
	}
}

