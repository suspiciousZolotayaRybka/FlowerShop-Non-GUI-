/**
 * Customer contains two constructor.
 * One constructor randomizes normal customers.
 * The other is accessed via super() in friend and critic.
 *
 * Customer contains all the methods that are required to make a transaction happen between customer and flower shop.
 * These include methods to see what flowers the flowerShop has vs what the customer wants,
 * and return money and ratings based on the customer's preference.
 *
 * Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/20/2022
 * @version DiscWeek3.0
 * @author fineh
 */
package FinehoutIsaac_DiscWeek3;

import java.util.ArrayList;
import java.util.Random;

public class Customer implements FlowerParts {

	// Declare private static final String[]s
	private static final String[] MONEY_COEFFICIENTS = { "$", "$$", "$$$" };

	// Declare non final attributes
	private boolean[] hasPreference = new boolean[3];
	private int[] preferences = new int[3];
	private final int numPreferences;
	private int moneyCoefficient;

	// Customer constructor used to create Friends and Critic subclasses via super
	// constructor
	public Customer(boolean[] hasPreference, int[] preferences, int numPreferences, int moneyCoefficient) {
		this.hasPreference = hasPreference;
		this.preferences = preferences;
		this.numPreferences = numPreferences;
		this.moneyCoefficient = moneyCoefficient;
	}

	// Constructor for random Customer - used for normal customer
	public Customer() {

		/*-*
		 * Find the total number of preferences the Customer will have.
		 * It is a random number out of three.
		 */
		Random rng = new Random();
		int preference;
		numPreferences = (rng.nextInt(3) + 1);

		/*-*
		 * Prior to filling in the preferences,
		 * give customer full array of preferences for Flower Parts.
		 * Preferences will later be turned on or off, depending on numPreferences,
		 * by using a boolean array to turn preferences true or false.
		 */
		for (int i = 0; i < 3; i++) {
			preference = rng.nextInt(4);
			hasPreference[i] = true;
			preferences[i] = preference;
			// Note that this setup is already complete for a customer who has 3 preferences
		}

		/*-*
		 * Fill in preferences depending on how many preferences the customer has.
		 * This switch turns random spots in the boolean array false,
		 * if the customer has less than three preferences.
		 * If the customer has three preferences, nothing needs to be done.
		 * It also gives customers moneyCoefficients;
		 * more preferences = higher paying customer.
		 */
		switch (numPreferences) {

		// Customer only has one preference
		case (1):
			int onlyPreference = rng.nextInt(3);
			for (int i = 0; i < 3; i++) {
				if (onlyPreference == i) {
					// apply random preference to preferences
					preferences[i] = rng.nextInt(5);
				} else {
					// customer does not have a preference
					hasPreference[i] = false;
				}
			}
			moneyCoefficient = 1;
			break;

		// Customer has two preferences
		case (2):
			int onlyNonPreference = rng.nextInt(3);
			for (int i = 0; i < 3; i++) {
				if (onlyNonPreference == i) {
					// customer does not have a preference
					hasPreference[i] = false;
				} else {
					// apply random preference to preferences
					preferences[i] = rng.nextInt(5);
				}
			}
			moneyCoefficient = 2;
			break;
		case (3):
			// Array of preferences was already generated at the beginning
			moneyCoefficient = 3;
			break;
		default:
			System.err.println("error");
			moneyCoefficient = 9000000;
		}
	}

	/**
	 * @return hasPreferences
	 */
	public boolean[] getHasPreferences() {
		return hasPreference;
	}

	/**
	 * @return preferences
	 */
	public int[] getPreferences() {
		return preferences;
	}

	/**
	 * @return numPreferences
	 */
	public int getNumPreferences() {
		return numPreferences;
	}

	@Override
	public String toString() {
		// declare variables
		String customer = "";

		customer += String.format("NORMAL\nPreference(s): %d (%s)\n", numPreferences,
				MONEY_COEFFICIENTS[moneyCoefficient - 1]);
		// if the customer has a preference, it will be added onto his/her string
		if (hasPreference[0]) {
			customer += String.format("Color: %s\n", COLORS[preferences[0]]);
		}
		if (hasPreference[1]) {
			customer += String.format("Blossom: %s (%s)\n", BLOSSOM_NAMES[preferences[1]], BLOSSOMS[preferences[1]]);
		}
		if (hasPreference[2]) {
			customer += String.format("Stem: %s (%s)\n", STEM_NAMES[preferences[2]], STEMS[preferences[2]]);
		}

		return customer;
	}

	/*-*
	 * More customers are generated when the weather is better.
	 *
	 * Critics spawn in the first customer index.
	 * If it is rainy or extreme weather, critics will not spawn
	 * Sunny = 20% chance
	 * Cloudy = 15% chance
	 * Windy = 10% chance
	 *
	 * Friends spawn in the second Customer index.
	 * Friends have a 40% chance to spawn in Sunny, cloudy, and windy weather.
	 * Friends have a 60% chance to spawn in Rainy or extreme weather.
	 */
	public static ArrayList<Customer> getCustomerList(int currentWeather, Companion[] companions) {
		// Declare variables
		Random ran = new Random();
		Customer randomCust;
		ArrayList<Customer> customerList = new ArrayList<Customer>(0);
		boolean[] criticHasPreferences = new boolean[3];
		int[] criticPreferences = new int[3];
		int randomPreferenceCritic;

		// Assigning Random Preferences for Flower Critics
		randomPreferenceCritic = ran.nextInt(3);
		criticHasPreferences[randomPreferenceCritic] = true;
		criticPreferences[randomPreferenceCritic] = ran.nextInt(5);

		// more customers will be generated for better weather
		switch (currentWeather) {
		case (0):
			// sunny
			for (int i = 0; i < 6; i++) {
				// 20% chance to spawn a critic at first index
				if ((i == 0) && (ran.nextInt(100) < 20)) {
					customerList.add(new Critic(criticHasPreferences, criticPreferences));
				}
				// 30% chance to spawn a friend at second index
				else if ((i == 1) && (ran.nextInt(100) < 30)) {
					customerList.add(companions[ran.nextInt(2)]);
					// Spawn a normal random customer
				} else {
					randomCust = new Customer();
					customerList.add(randomCust);
				}
			}
			break;
		case (1):
			// cloudy
			for (int i = 0; i < 5; i++) {
				// 15% chance to spawn a critic at first index
				if ((i == 0) && (ran.nextInt(100) < 15)) {
					customerList.add(new Critic(criticHasPreferences, criticPreferences));
				}
				// 30% chance to spawn a friend at second index
				else if ((i == 1) && (ran.nextInt(100) < 30)) {
					customerList.add(companions[ran.nextInt(2)]);
					// Spawn a normal random customer
				} else {
					randomCust = new Customer();
					customerList.add(randomCust);
				}
			}
			break;
		case (2):
			// windy
			for (int i = 0; i < 4; i++) {
				// 10% chance to spawn a critic at first index
				if ((i == 0) && (ran.nextInt(100) < 10)) {
					customerList.add(new Critic(criticHasPreferences, criticPreferences));
				}
				// 30% chance to spawn a friend at second index
				else if ((i == 1) && (ran.nextInt(100) < 30)) {
					customerList.add(companions[ran.nextInt(2)]);
					// Spawn a normal random customer
				} else {
					randomCust = new Customer();
					customerList.add(randomCust);
				}
			}
			break;
		case (3):
			// rainy
			for (int i = 0; i < 3; i++) {
				// 60% chance to spawn a friend at second index
				if ((i == 1) && (ran.nextInt(100) < 60)) {
					customerList.add(companions[ran.nextInt(2)]);
					// Spawn a normal random customer
				} else {
					randomCust = new Customer();
					customerList.add(randomCust);
				}
			}
			break;
		case (4):
			// extreme
			for (int i = 0; i < 2; i++) {
				// 60% chance to spawn a friend at second index
				if ((i == 1) && (ran.nextInt(100) < 60)) {
					customerList.add(companions[ran.nextInt(2)]);
					// Spawn a normal random customer
				} else {
					randomCust = new Customer();
					customerList.add(randomCust);
				}
			}
			break;
		default:
			System.err.println("error");

		}

		return customerList;
	}

	/*-*
	 * If a customer has a preference for a flower part,
	 * and the player has a flower that supports the preference,
	 * return true to customerFlowerMatch at the specific customer
	 *
	 * Control statements are setup in the following format:
	 * For loop to iterate through each customer
	 * 		For loop to iterate through each flower
	 * 			Find matching colors
	 * 			Find matching blossoms
	 * 			Find matching stems
	 * 		For loop to iterate through each flower
	 * 			Switch statement controlled by numPreferences for each customer
	 * 				If statement that checks each flower index to see if numPreferencesSatisfied is greater than the customer numPreferences
	 *
	 *	The two for loops for flowers seems redundant, as though I could put it all under one...
	 *	But I am not good at nested for loops, and changing it when I already got it working scares me...
	 */
	public static boolean[] getCustomerFlowerMatch(ArrayList<Customer> customerList, ArrayList<Flower> flowerList) {
		// declare variables
		boolean[] customerFlowerMatch = new boolean[customerList.size()];
		int customerNum = 0;
		int numPreferencesSatisfiedAtFloweri[] = new int[flowerList.size()];

		// iterate through customers
		for (Customer customer : customerList) {

			numPreferencesSatisfiedAtFloweri = new int[flowerList.size()];

			// iterate through flowers
			for (int i = 0; i < flowerList.size(); i++) {

				// Find Color Match
				if ((customer.getHasPreferences()[COLOR_NUM])
						&& (customer.getPreferences()[COLOR_NUM] == flowerList.get(i).getColor())) {
					numPreferencesSatisfiedAtFloweri[i]++;
				}

				// find Blossom Match
				if ((customer.getHasPreferences()[BLOSSOM_NUM])
						&& (customer.getPreferences()[BLOSSOM_NUM] == flowerList.get(i).getBlossom())) {
					numPreferencesSatisfiedAtFloweri[i]++;
				}

				// Find stem match
				if ((customer.getHasPreferences()[STEM_NUM])
						&& (customer.getPreferences()[STEM_NUM] == flowerList.get(i).getStem())) {
					numPreferencesSatisfiedAtFloweri[i]++;
				}
			}
			// If a customer has 2 or 3 preferences,
			// numPreferencesSatisfiedAtFloweri must match
			for (int i = 0; i < flowerList.size(); i++) {
				switch (customer.getNumPreferences()) {

				// Customer has 1 preference
				case (1):
					if (numPreferencesSatisfiedAtFloweri[i] > 0) {
						customerFlowerMatch[customerNum] = true;
					}
					break;

				// Customer has 2 preferences
				case (2):
					if (numPreferencesSatisfiedAtFloweri[i] > 1) {
						customerFlowerMatch[customerNum] = true;
					}
					break;

				// Customer has 3 preferences
				case (3):
					if (numPreferencesSatisfiedAtFloweri[i] > 2) {
						customerFlowerMatch[customerNum] = true;
					}
					break;
				default:
					System.err.println("error");
				}
			} // End of customer numPreferences Switch Statement
			customerNum++;
		} // End of customer for loop
		return customerFlowerMatch;
	}// End of method

	/**
	 * Pull out the flowers that apply to the customer. This method cannot be
	 * accessed if there is no flower for the customers, as was found out in the
	 * last method.
	 *
	 * @param flowerList list of the user's flower
	 * @param customer   the customer the user is selling to
	 * @return
	 */
	public static Flower[] findFlowersForCustomer(ArrayList<Flower> flowerList, Customer customer) {

		// declare variables
		Flower[] applicableFlowers = new Flower[100];
		int numApplicableFlowers = 0;
		int numPreferencesSatisfied;
		for (int i = 0; i < flowerList.size(); i++) {

			numPreferencesSatisfied = 0;
			// check for color match
			if ((flowerList.get(i).getColor() == customer.getPreferences()[COLOR_NUM])
					&& customer.getHasPreferences()[COLOR_NUM]) {
				numPreferencesSatisfied++;
			}

			// check for blossom match
			if ((flowerList.get(i).getBlossom() == customer.getPreferences()[BLOSSOM_NUM])
					&& customer.getHasPreferences()[BLOSSOM_NUM]) {
				numPreferencesSatisfied++;
			}

			// check for stem match
			if ((flowerList.get(i).getStem() == customer.getPreferences()[STEM_NUM])
					&& customer.getHasPreferences()[STEM_NUM]) {
				numPreferencesSatisfied++;
			}

			// throw the number of preferences satisfied for this specific flower
			// against however many preferences the customer has
			switch (customer.getNumPreferences()) {
			case (1):
				if (numPreferencesSatisfied > 0) {
					applicableFlowers[numApplicableFlowers] = new Flower(flowerList.get(i).getColor(),
							flowerList.get(i).getBlossom(), flowerList.get(i).getStem());
					numApplicableFlowers++;
				}
				break;
			case (2):
				if (numPreferencesSatisfied > 1) {
					applicableFlowers[numApplicableFlowers] = new Flower(flowerList.get(i).getColor(),
							flowerList.get(i).getBlossom(), flowerList.get(i).getStem());
					numApplicableFlowers++;
				}
				break;
			case (3):
				if (numPreferencesSatisfied > 2) {
					applicableFlowers[numApplicableFlowers] = new Flower(flowerList.get(i).getColor(),
							flowerList.get(i).getBlossom(), flowerList.get(i).getStem());
					numApplicableFlowers++;
				}
				break;
			default:
				System.err.println("error");
				break;
			}
		}

		return applicableFlowers;
	}

	public static int getLenApplicableFlowers(Flower[] applicableFlowers) {
		// declare variable
		int lenApplicableFlowers = 0;

		for (Flower flower : applicableFlowers) {
			if (flower != null) {
				lenApplicableFlowers++;
			}
		}

		return lenApplicableFlowers;
	}

	/*-*
	 * applicableFlowersInFlowerList is ordered in the same order as applicableFlowers.
	 * Each index contains the location of the applicableFlower in flowerList,
	 * so that the program knows which flower to remove from flowerList when the user sells a flower to a customer from applicableFlowers[]
	 */

	public static int[] findApplicableFlowersInFlowerList(ArrayList<Flower> flowerList, Flower[] applicableFlowers,
			int lenApplicableFlowers) {
		// declare variables
		int[] applicableFlowersInFlowerList = new int[100];
		int numApplicableFlowers = 0;

		for (int i = 0; i < flowerList.size(); i++) {
			if (numApplicableFlowers == lenApplicableFlowers) {
				// Do nothing
				// All flowers in applicableFlowers have already been found in flowerList
			} else {
				// Still need to find applicableFlowers in flowerList.
				// If the flower in applicableFlowers is equal to the flower in flowerList,
				// at the corresponding index in flowerList to applicableFlowersInFlowerList
				if ((applicableFlowers[numApplicableFlowers].getColor() == flowerList.get(i).getColor())
						&& (applicableFlowers[numApplicableFlowers].getBlossom() == flowerList.get(i).getBlossom())
						&& (applicableFlowers[numApplicableFlowers].getStem() == flowerList.get(i).getStem())) {
					applicableFlowersInFlowerList[numApplicableFlowers] = i;
					numApplicableFlowers++;
				}
			}
		}

		return applicableFlowersInFlowerList;
	}

	public static Flower[] setSizesOfApplicableFlowers(ArrayList<Flower> flowerList, Flower[] applicableFlowers,
			int[] applicableFlowersInFlowerList, int lenApplicableFlowers) {

		for (int i = 0; i < lenApplicableFlowers; i++) {
			applicableFlowers[i].setSize(flowerList.get(applicableFlowersInFlowerList[i]).getSize());
		}
		return applicableFlowers;
	}

	/*-*
	 * This method returns an int[].
	 * The first index represents how much $ the player gets from the transaction.
	 * The second index represents the rating the customer leaves the player
	 */
	public static int[] getTransaction(Flower flower, Customer customer, int paymentAmount) {
		// declare variables
		int[] transaction = new int[2];
		Random ran = new Random();

		/*-*
		 * Determine the amount of money the player receives.
		 * Older flowers are worth more.
		 * Customers with more preferences give more money.
		 */
		switch (flower.getSize()) {
		case (0):// Young
			transaction[0] = 4;
			transaction[0] *= customer.moneyCoefficient;
			break;
		case (1):// Young Adult
			transaction[0] = 6;
			transaction[0] *= customer.moneyCoefficient;
			break;
		case (2):// Adult
			transaction[0] = 10;
			transaction[0] *= customer.moneyCoefficient;
			break;
		case (3):// Mature
			transaction[0] = 15;
			transaction[0] *= customer.moneyCoefficient;
			break;
		case (4):// Old
			transaction[0] = 35;
			transaction[0] *= customer.moneyCoefficient;
			break;
		default:
			System.err.println("error");
		}

		// Friends always give four times the amount of money and a five star rating.
		if (customer instanceof Companion) {
			transaction[1] = 5;

			// Critics always give $15. Their rating is based on the age of the flower
		} else if (customer instanceof Critic) {

			transaction[0] = 15;

			// Find the rating
			switch (flower.getSize()) {
			case (0):
				transaction[1] = 1;
				break;
			case (1):
				transaction[1] = 3;
				break;
			case (2):
				transaction[1] = 4;
				break;
			case (3):
				transaction[1] = 5;
				break;
			case (4):
				transaction[1] = 5;
				break;
			default:
				System.err.println("error");
				break;

			}

			/*-*
			 * Normal Customer
			* Determine the rating based on payment amount.
			* Selling flowers at higher prices results in a lower rating.
			*/
		} else {
			switch (paymentAmount) {
			case (1):
				transaction[1] = ran.nextInt(2) + 1; // 1 or 2 star rating
				transaction[0] *= 2; // Twice the amount of money earned
				break;
			case (2):
				transaction[1] = ran.nextInt(2) + 3; // 3 or 4 star rating. No changes to money earned
				break;
			case (3):
				transaction[1] = 5; // 5 star rating
				transaction[0] /= 2; // Money earned cut in half
				break;
			default:
				System.err.println("error");

			}
		}
		return transaction;
	}

	public static void printPriceOptions(Flower flower, Customer customer) {
		int paymentAmount[] = new int[3];

		// The average payment amount is based on the flower size, then changes based on
		// the rating given
		switch (flower.getSize()) {
		case (0):// Young
			paymentAmount[1] = 4;
			paymentAmount[1] *= customer.moneyCoefficient;
			break;
		case (1):// Young Adult
			paymentAmount[1] = 6;
			paymentAmount[1] *= customer.moneyCoefficient;
			break;
		case (2):// Adult
			paymentAmount[1] = 10;
			paymentAmount[1] *= customer.moneyCoefficient;
			break;
		case (3):// Mature
			paymentAmount[1] = 15;
			paymentAmount[1] *= customer.moneyCoefficient;
			break;
		case (4):// Old
			paymentAmount[1] = 35;
			paymentAmount[1] *= customer.moneyCoefficient;
			break;
		default:
			System.err.println("error");
		}

		/*-*
		 * Add the two extra ends to the paymentAmount,
		 * depending on whether the customer leaves a good or bad rating
		 */
		paymentAmount[0] = paymentAmount[1] / 2;
		paymentAmount[2] = paymentAmount[1] * 2;

		// Customer is a critic or a friend
		if (customer instanceof Companion) {
			// Do Nothing

		} else if (customer instanceof Critic) {
			// Do nothing

		} else {
			System.out.printf(
					"1 - $%d (1-2 star rating)\n" + "2 - $%d (3-4 star rating)\n" + "3 - $%d (5 star rating)\n=>",
					paymentAmount[2], paymentAmount[1], paymentAmount[0]);
		}
	}

	/*-*
	 * Print out all current customers. used in FlowerShop
	 */
	public static boolean printCustomers(ArrayList<Customer> customerList, boolean[] customerFlowerMatch) {
		// declare variables
		boolean canSatisfyCustomer = false;
		int i = 0;
		System.out.println("Current Customers\n--------------\n");
		for (Customer customer : customerList) {
			if (customerFlowerMatch[i]) {
				canSatisfyCustomer = true;
			}
			System.out.printf("%s:\n%s\n%s\n--------------\n", i + 1, customer,
					customerFlowerMatch[i] ? "Flower avaiable for customer!"
							: "No flowers available for this customer...");
			i++;
		}
		return canSatisfyCustomer;
	}

	// Discussion 6 / Isaac Finehout / CMIS 242 6384 / 10 February 2023
	// Overloading
	public void describeCustomer() {
		System.out.println("The customer is ready to buy a flower!");
	}

	// Overloading
	public void describeCustomer(String nameFlowerShop) {
		System.out.println("The customer is ready to buy a flower at " + nameFlowerShop + "!");
	}

}
