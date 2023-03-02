/**
 * A class that substitutes exception handling in java... Will be replaced in later weeks of CMIS 242
 *
 * Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/20/2022
 * @version DiscWeek3.0
 * @author fineh
 */
package FinehoutIsaac_DiscWeek3;

import java.util.Scanner;

public class Try {

	public static int getInt(int min, int max, Scanner scanint) {
		// declare variables
		boolean errorCatching = true;
		int number = 0;

		while (errorCatching) {
			while (!scanint.hasNextInt()) {
				String error = scanint.next();
				System.err.printf(error + " is not an integer between %d - %d.\n", min, max);
				System.out.printf("Please enter an integer between %d - %d.\n", min, max);
			}
			number = scanint.nextInt();
			scanint.nextLine(); // Prevent the crash that occurs when scanning a String directly after scanning
								// an int

			if ((number < min) || (number > max)) {
				System.err.printf(number + " is not an integer between %d - %d.\n", min, max);
				System.out.printf("Please enter an integer between %d - %d.\n", min, max);
			} else {
				errorCatching = false;
			}
		}
		return number;
	}

	public static double getDouble(int min, int max, Scanner scandouble) {
		// declare variables
		boolean errorCatching = true;
		double number = 0;

		while (errorCatching) {
			while (!scandouble.hasNextDouble()) {
				String error = scandouble.next();
				System.err.printf(error + " is not an integer between %d - %d.\n", min, max);
				System.out.printf("Please enter an integer between %d - %d.\n", min, max);
			}
			number = scandouble.nextDouble();
			scandouble.nextLine(); // Prevent the crash that occurs when scanning a String directly after scanning
									// a double

			if ((number < min) || (number > max)) {
				System.err.printf(number + " is not an integer between %d - %d.\n", min, max);
				System.out.printf("Please enter an integer between %d - %d.\n", min, max);
			} else {
				errorCatching = false;
			}
		}
		return number;
	}

	public static boolean getBoolean(Scanner scanstr) {
		boolean errorChecking = true;
		String userChoice;
		userChoice = scanstr.nextLine().toLowerCase();

		// Checking user input for blank answers or answers that aren't yes or no
		while (errorChecking) {
			if ((userChoice == null) || userChoice.isBlank() || userChoice.isEmpty()) {
				System.err.println("You entered nothing.");
				System.out.println("Please enter a yes or no answer");
				userChoice = scanstr.nextLine().toLowerCase();
			} else if ((!((userChoice.charAt(0)) == 'y')) && (!((userChoice.charAt(0)) == 'n'))) {
				System.err.print(userChoice + " is not a yes or no answer\n");
				System.out.println("Please enter a yes or no answer");
				userChoice = scanstr.nextLine().toLowerCase();
			} else {
				errorChecking = false;
			}

			// Returning true or false based on user input
		}
		if (userChoice.charAt(0) == 'y') {
			return true;
		}
		return false;
	}

	public static String getString(int min, int max, Scanner scanstr) {
		boolean errorChecking = true;
		String userChoice;
		userChoice = scanstr.nextLine();

		// Checking if user input is null, empty, or blank, and the length of characters
		while (errorChecking) {
			if ((userChoice == null) || userChoice.isBlank() || userChoice.isEmpty()) {
				System.err.println("You entered nothing.");
				System.out.printf("Please enter a string.\n" + "=>", min, max);
				userChoice = scanstr.nextLine();
			} else if ((userChoice.length() < min) || (userChoice.length() > max)) {
				System.err.printf("%s is not between %d - %d characters long.\n", userChoice, min, max);
				System.out.println("Please enter a string within the character limits.\n" + "=>");
				userChoice = scanstr.nextLine();
			} else {
				errorChecking = false;
			}
		}
		return userChoice;
	}

}
