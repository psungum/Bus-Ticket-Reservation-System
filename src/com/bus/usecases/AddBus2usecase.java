package com.bus.usecases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

import com.bus.bean.Bus;
import com.bus.custom.ConsoleColors;
import com.bus.dao.AdminDao;
import com.bus.dao.AdminDaoImpl;

public class AddBus2usecase {

	static Scanner sc = new Scanner(System.in);

	public static void AddBus() {

		try {

			int busNo = getInt("Enter bus number", AddBus2usecase::validateBusNo);

			String bName = getString("Enter bus name", AddBus2usecase::validateBusName);

			String routeFrom = getString("Enter Route from", input -> input.length() >= 3 && input.length() <= 20);

			String routeTo = getString("Enter Route To", input -> input.length() >= 3 && input.length() <= 20);

			String bType = getString("Enter Bus Type - AC / NonAC", input -> input.equals("AC") || input.equals("NonAC"));

			//String departure = getString("Enter Departure date and time in format (YYYY-MM-DD HH:MI:SS)", AddBus2usecase::validateDate );
			String departure = getDateTime("Enter Departure date and time in format (YYYY-MM-DD HH:MI:SS)");

			String arrival = getDateTime("Enter Arrival date and time in format (YYYY-MM-DD HH:MI:SS)");

			int totalSeats = getInt("Enter Total Seats", seats -> seats > 0);

			int availSeats = getInt("Enter Available Seats", seats -> seats >= 0 && seats <= totalSeats);

			int fare = getInt("Enter fare", price -> price > 0);

			Bus bus = new Bus(busNo, bName, routeFrom, routeTo, bType, departure, arrival, totalSeats, availSeats, fare);

			AdminDao dao = new AdminDaoImpl();

			String result = dao.addBus(bus);

			if (result.equals("Bus added Successfully")) {
				System.out.println(ConsoleColors.GREEN_BACKGROUND + result + ConsoleColors.RESET);
			} else {
				System.out.println(ConsoleColors.RED_BACKGROUND + result + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input" + ConsoleColors.RESET);
		}
	}

	private static boolean validateDate(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		try {
			formatter.parse(input);
		} catch(Exception e){
			return false;
		}

		return true;
	}

	static int getInt(String message, Predicate<Integer> predicate) {
		int input = 0;
		while (true) {
			try {
				System.out.println(ConsoleColors.ORANGE + message + ConsoleColors.RESET);
				input = sc.nextInt();
				sc.nextLine(); // clear the buffer
				if (predicate.test(input)) {
					break;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input" + ConsoleColors.RESET);
				}
			} catch (InputMismatchException e) {
				System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input" + ConsoleColors.RESET);
				sc.nextLine(); // clear the buffer
			}
		}
		return input;
	}

	static String getString(String message, Predicate<String> predicate) {
		String input = "";
		while (true) {
			try {
				System.out.println(ConsoleColors.ORANGE + message + ConsoleColors.RESET);
				input = sc.nextLine().trim();
				if (predicate.test(input)) {
					break;
				} else {
					System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input" + ConsoleColors.RESET);
				}
			} catch (Exception e) {
				System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input" + ConsoleColors.RESET);
			}
		}
		return input;
	}


	static String getString2(String message, Predicate<String> predicate) {
		System.out.println(ConsoleColors.ORANGE + message + ConsoleColors.RESET);
		String input = sc.nextLine().trim();

		if (!predicate.test(input)) {
			System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid input" + ConsoleColors.RESET);
			getString(message, predicate);
		}

		return input;
	}

	static String getDateTime(String message) {
		String dateTime = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		while (true) {
			try {
				System.out.println(ConsoleColors.ORANGE + message + ConsoleColors.RESET);
				dateTime = sc.nextLine().trim();
				LocalDateTime.parse(dateTime, formatter); // Validate the date format
				break;
			} catch (DateTimeParseException e) {
				System.out.println(ConsoleColors.RED_BACKGROUND + "Invalid date/time format! Please use (YYYY-MM-DD HH:MI:SS)" + ConsoleColors.RESET);
			}
		}
		return dateTime;
	}

	static boolean validateBusNo(int busNo) {
		return busNo > 0 && busNo < 300;
	}

	 static boolean validateBusName(String busName) {
		return busName.length() >= 5 && busName.length() <= 15 && busName.matches("[a-zA-Z ]+");
	}
}

