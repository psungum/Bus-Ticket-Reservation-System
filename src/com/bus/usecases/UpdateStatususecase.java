package com.bus.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bus.custom.ConsoleColors;
import com.bus.dao.AdminDao;
import com.bus.dao.AdminDaoImpl;

public class UpdateStatususecase {

	public static void updateStatus() {

		Scanner sc = new Scanner(System.in);
		int cusId = -1;

		try {
			System.out.println(ConsoleColors.ORANGE + "Enter customer Id" + ConsoleColors.RESET);
			cusId = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid customer ID! Please enter a valid integer." + ConsoleColors.RESET);
			return; // Exit the method if invalid input
		}

		AdminDao dao = new AdminDaoImpl();
		String result = dao.updateStatus(cusId);

		// If the result contains "not", we assume it's a failure message
		if (result.toLowerCase().contains("not")) {
			System.out.println(ConsoleColors.RED_BACKGROUND + result + ConsoleColors.RESET);
		} else {
			System.out.println(ConsoleColors.GREEN_BACKGROUND + result + ConsoleColors.RESET);
		}
	}
}

