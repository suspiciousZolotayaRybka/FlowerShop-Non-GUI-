/**
 * This is the location of main(String[] args)
 *
 * This class runs a simple mini game in which the user's goal is to make as
 * much money as possible in five days, while maintaining a high customer
 * satisfaction rating
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
import java.util.Scanner;

public class FlowerShop {

	// Declare static variables
	private ArrayList<Flower> flowerList;
	private ArrayList<Customer> customerList;
	private static Companion[] companions = new Companion[2];

	private static Scanner stdin = new Scanner(System.in);

	private static String nameFlowerShop;
	private static final String[] WEATHER = { "Sunny", "Cloudy", "Windy", "Rainy", "Extreme Weather" };

	private static int currentWeather;
	private static int money;
	private static double[] ratings;
	private static int numRatings;
	private static double averageRating;
	private static int day;

	private static boolean criticIsSatisfied = true;

	// Constructor for FlowerShop
	private FlowerShop() {

		// declare method variables
		boolean choosingName = true;

		// assign class variables
		flowerList = new ArrayList<Flower>();
		currentWeather = 0; // Start with sunny weather
		money = 30; // Start with $30
		ratings = new double[100]; // userCan have up to 100 ratings before game crashes
		ratings[0] = 5; // user starts at a five-star rating
		numRatings = 1; // current number of ratings
		averageRating = getAverageRating();
		day = 1; // Start on day 1
		// player chooses the name of their flower shop, allows a rename
		while (choosingName) {
			System.out.println("See how much money you can make in 5 days, while maintaining a high rating!\n");
			System.out.println("Please enter the name of your new flower shop.\n" + "=>");
			nameFlowerShop = Try.getString(1, 20, stdin);
			System.out.printf("Are you sure you would like to choose %s? You can't change this later.\n" + "=>",
					nameFlowerShop);
			choosingName = !(Try.getBoolean(stdin)); // double negation
		}

		// player has two random friends
		companions = Companion.getNewCompanions(); // Players friends are determined randomly
		customerList = Customer.getCustomerList(currentWeather, companions);

		System.out.println("\n\n\nPlease take special note of your friends' flower preferences!\n"
				+ "They will visit your flower shop often and are more likely to give you a higher rating and more money!");
		System.out.println("\n");
		System.out.println(companions[0]);
		System.out.println("\n");
		System.out.println(companions[1]);

		// Critics can never spawn on the first day you're open
		if (customerList.get(0) instanceof Critic) {
			customerList.set(0, new Customer());
		}

		System.out.println("Press ENTER when you have finished taking note of your friends' flower preferences...\n");
		stdin.nextLine();
		System.out.println("\n".repeat(20));

		// player starts with 3 random flowers
		for (int i = 0; i < 3; i++) {
			Random ran = new Random();
			flowerList.add(new Flower(ran.nextInt(4), ran.nextInt(4), ran.nextInt(4)));
		}
		// Tell user their starting flowers
		System.out.printf("You opened your store with a %s, a %s, and a %s\n", flowerList.get(0).getName(),
				flowerList.get(1).getName(), flowerList.get(2).getName());
	}

	private void printMenu() {
		//@formatter:off
		System.out.printf(".------------------------------------------------.\n"
				+ "|   %-45s|\n"
				+ "|   %-45s|\n"
				+ ":-----+------------------------------------------:\n"
				+ "| 1.  | Plant New Flower                   (-$15)|\n"
				+ ":-----+------------------------------------------:\n"
				+ "| 2.  | View Current Flowers                     |\n"
				+ ":-----+------------------------------------------:\n"
				+ "| 3.  | Water Flowers                      (-10$)|\n"
				+ ":-----+------------------------------------------:\n"
				+ "| 4.  | Trim Flowers                        (-$5)|\n"
				+ ":-----+------------------------------------------:\n"
				+ "| 5.  | Pluck Flowers                       (+$5)|\n"
				+ ":-----+------------------------------------------:\n"
				+ "| 6.  | View Customers                           |\n"
				+ ":-----+------------------------------------------:\n"
				+ "| 7.  | End the Day                              |\n"
				+ "'-----'------------------------------------------'\n"
				+ "| 8.  | Exit                                     |\n"
				+ "'-----'------------------------------------------'\n"
				+ "Enter your choice\n"
				+ "=>", String.format("%s (DAY %d)", nameFlowerShop , day) , String.format("$%d / %.1f STARS", money , averageRating));
		//@formatter:on
	}

	private void processMenuChoice(int c) {

		switch (c) {
		case 1:
			payToPlantFlower();
			break;
		case 2:
			viewCurrentFlowers();
			break;
		case 3:
			payToWaterFlowers();
			break;
		case 4:
			payToTrimFlowers();
			break;
		case 5:
			bePaidToPluckFlowers();
			break;
		case 6:
			viewCustomers();
			break;
		case 7:
			endDay();
			break;
		case 8:
			System.out.println("Thank you for using the Flower Shop Program!");
			break;
		}
	}

	private void payToPlantFlower() {

		// check to see if user has enough money
		if (money < 15) {
			System.err.printf("You only have $%d and need $15 to plant a new flower.\n", money);
		} else {
			// User buys a new flower
			System.out.println("You paid $15 to plant a new flower.");
			money -= 15;
			flowerList = Flower.plantFlowers(flowerList);

		}

	}

	private void viewCurrentFlowers() {

		// User has not planted any flowers yet
		if (flowerList.size() == 0) {
			System.err.println("You have yet to plant a flower.");
			// User has planted flowers
		} else {
			// Iterate through flowers
			for (Flower flower : flowerList) {
				System.out.print(flower); // Print flower
				System.out.print("\n".repeat(3)); // Print lines for next flower
			}
		}

	}

	private void payToWaterFlowers() {
		if (flowerList.size() == 0) {
			System.err.println("You have not planted any flowers yet!\n");
		} else if (money < 10) {
			System.err.printf("You only have $%d and need $10 to water your flowers\n", money);
		} else {
			// User has planted flowers and grows them
			System.out.println("You watered all your flowers for $10 and they grew in size!");
			money -= 10;
			flowerList = Flower.waterFlowers(flowerList);
		}
	}

	private void payToTrimFlowers() {

		if (flowerList.size() == 0) {
			System.err.println("You have not planted any flowers yet!");
		} else if (money < 5) {
			System.err.printf("You only have $%d and need $5 to trim flowers\n", money);
		} else {
			// user has planted the flowers and has the money to trim their flowers
			flowerList = Flower.trimFlowers(flowerList);
		}
	}

	private void bePaidToPluckFlowers() {

		// Declare variables used to determine if user plucks a flower
		int numFlowersBeforePlucking = flowerList.size();
		int numFlowersAfterPlucking;

		if (flowerList.size() == 0) {
			System.err.println("You have not planted any flowers yet!");
		} else {
			flowerList = Flower.pluckFlowers(flowerList);
			numFlowersAfterPlucking = flowerList.size();
			if (numFlowersAfterPlucking == numFlowersBeforePlucking) {
				System.out.println("You decided not to pluck any flowers.");
			} else {
				money += 5;
				System.out.println("You gave the flower to a random passerby and received a $5 tip.");
			}
		}
	}

	private void viewCustomers() {

		if (flowerList.size() == 0) {
			System.out.println("You have no flowers to sell to customers!");
		} else {
			// declare variables
			boolean[] customerFlowerMatch;
			boolean canSatisfyCustomer;

			// True at each customerList index where the user has a flower that satisfies a
			// customer
			customerFlowerMatch = Customer.getCustomerFlowerMatch(customerList, flowerList);

			// print customers serves as a double method, to print customers to the screen
			// and show if you can satisfy at least one customer
			canSatisfyCustomer = Customer.printCustomers(customerList, customerFlowerMatch);

			if (!canSatisfyCustomer) {
				// If player cannot satisfy at least one customer, they will not be allowed to
				// sell flowers
				System.out.println("None of the customers want a flower you're selling!");
			} else {
				sellCustomerAFlower(customerFlowerMatch);
			}
		}
	}

	/*-*
	 * At the end of the a special event will happen.
	 * If five days have already passed, the final game stats will be printed.
	 * Otherwise, the special event will happen, the weather will change, and a new cutomserList will be generated.
	 */
	private void endDay() {
		// declare variables
		Random rng = new Random();

		System.out.println("You closed the shop for the day.\n");

		// Check to see if the user still has a flower critic in the store
		if (!criticIsSatisfied) {
			System.out.println("You didn't sell a flower to the critic and they left you 3 1-star ratings!\n");
			for (int i = 0; i < 3; i++) {
				ratings[numRatings] = 1;
				numRatings++;
			}
			averageRating = getAverageRating();
			criticIsSatisfied = true;
		}

		day++;

		if (day > 5) {
			printFinalGameStats();
		} else {
			// Find the day's special event and process
			int endOfDayEvent = rng.nextInt(10);
			System.out.printf("TODAY'S SPECIAL EVENT\n");
			processEndOfDayEvent(endOfDayEvent);
			System.out.println("\nPress ENTER to sleep...");
			stdin.nextLine();
			System.out.println("\n".repeat(20));

			// Process the change in weather and get new customers.
			changeWeather();
			customerList = Customer.getCustomerList(currentWeather, companions);
			// If a flower critic is visiting, warn the user
			if (customerList.get(0) instanceof Critic) {
				System.out.println("\nWARNING\nA flower critic appeared in your store!\n"
						+ "Service them before the end of the day or receive 3 1-star ratings!");
				criticIsSatisfied = false;
			}
		}

	}

	public static void main(String[] args) {

		// Declare and initialize handler variables
		FlowerShop handler = new FlowerShop();
		int menuChoice = 0;

		do {
			handler.printMenu(); // Print out the main menu
			menuChoice = Try.getInt(1, 8, stdin); // Finds the user's choice and accounts for input that are not int

			System.out.println("\n".repeat(20)); // Print a new page
			handler.processMenuChoice(menuChoice); // processMenuChoice

		} while ((menuChoice != 8) && (day < 6)); // 5 is the last day the FlowerShop will be open

		stdin.close();

	}

	/*
	 * SUB-METHODS not directly called on from processMenuChoice
	 *
	 *
	 *
	 *
	 */
	private double getAverageRating() {
		double sumRatings = 0;
		for (int i = 0; i < ratings.length; i++) {
			sumRatings += ratings[i];
		}
		return sumRatings / numRatings;
	}

	private void processEndOfDayEvent(int endOfDayEvent) {
		Random ran = new Random();
		switch (endOfDayEvent) {
		case (0):
			// found a flower in the park
			Flower temp = new Flower(ran.nextInt(4), ran.nextInt(4), ran.nextInt(4));
			System.out.printf("You found a new %s during your evening stroll in the park! +1 flower", temp.getName());
			flowerList.add(temp);
			break;
		case (1):
			// taxes were due today
			System.out.println("Bills were due today... -$10");
			money -= 10;
			if (money < 0) {
				System.out.println("Bills caused you to go in debt!");
			}
			break;
		case (2):
			// generous tips
			System.out.println("You had generous tips today! +$20");
			money += 20;
			break;
		case (3):
			System.out.printf("It was an uneventful %s day.\n", WEATHER[currentWeather]);
			break;
		case (4):
			// store broken into
			// user has no flowers so the thief steals money
			if ((flowerList.size() == 0) && (money > 10)) {
				System.out.println("A thief stole $10 from you! -$10");
				money -= 10;
				// user has a flower, the thief will always default to stealing flowers
			} else if (flowerList.size() > 1) {
				System.out.println("The store was broken into while you were gone... -1 flower");
				Random rng = new Random();
				int lostFlower = rng.nextInt(flowerList.size() - 1);
				System.out.printf("The thief stole your %s!\n", flowerList.get(lostFlower).getName());
				flowerList.remove(lostFlower);
				// the user is dirt poor
			} else {
				System.out.println("Your store was broken into, but there was nothing for the thief to steal!\n"
						+ "The thief left you a bad review! + 1-star review");
				ratings[numRatings] = 1;
				numRatings++;
				averageRating = getAverageRating();
			}
			break;
		case (5):
			// a generous benefactor
			Flower temp1 = new Flower(ran.nextInt(4), ran.nextInt(4), ran.nextInt(4));
			Flower temp2 = new Flower(ran.nextInt(4), ran.nextInt(4), ran.nextInt(4));
			System.out.printf(
					"A generous benefactor donated two flowers to %s!\n You received a %s and a %s. +2 flowers\n",
					nameFlowerShop, temp1.getName(), temp2.getName());
			flowerList.add(temp1);
			flowerList.add(temp2);
			break;
		case (6):
			// birthday
			System.out.println("It was your birthday! +$10");
			money += 10;
			break;
		case (7):
			// large family with kids
			if (flowerList.size() == 0) {
				System.out.println("A large family with kids came into your store, but you have no flowers!\n"
						+ "+ 2-star review");
				ratings[numRatings] = 2;
				numRatings++;
				averageRating = getAverageRating();
			} else {
				System.out.println(
						"A large family with kids came into the store... The kids messed with your flowers! + Trim");
				flowerList = Flower.trimFlowers(flowerList);
			}
			break;
		case (8):
			// apprentice waters flowers
			if (flowerList.size() == 0) {
				System.err.println("Your new apprentice offered to water your flowers, but you have none!");
			} else {
				System.out.println("Your new apprentice watered the flowers for you! + Water");
				flowerList = Flower.waterFlowers(flowerList);
			}
			break;
		case (9):
			System.out.printf("It was quiet at %s today.\n", nameFlowerShop);
			break;
		default:
			System.err.println("error");
		}
	}

	private void changeWeather() {
		// declare variables
		Random rng = new Random();
		int previousWeather = currentWeather;

		// change the weather
		currentWeather = rng.nextInt(4);

		// different events happen based on the weather, IF it is different the next day

		if (previousWeather == currentWeather) {
			System.out.printf("You woke up, and the weather is %s, just like yesterday. Nothing happens.\n",
					WEATHER[currentWeather]);
		} else {
			switch (currentWeather) {
			case (0):
				System.out.println("You woke up, and the weather today is Sunny. Nothing happens.");
				currentWeather = 0;
				break;
			case (1):
				System.out.println("You woke up, and the weather today is Cloudy. Nothing happens.");
				currentWeather = 1;
				break;
			case (2):
				System.out.println("You woke up, and the weather today is Windy.");
				currentWeather = 2;
				if (flowerList.size() > 0) {
					System.out.println("The wind trimmed down your flowers! + Trim");
					flowerList = Flower.trimFlowers(flowerList);
				}
				break;
			case (3):
				System.out.println("You woke up, and the weather today is Rainy.");
				if (flowerList.size() > 0) {
					System.out.println("The rain watered your flowers. + Water");
					flowerList = Flower.waterFlowers(flowerList);
				}
				break;
			case (4):
				System.out.println("You woke up, and the weather today is Extreme.");
				if (flowerList.size() > 0) {
					int lostFlower = rng.nextInt(flowerList.size() - 1);
					System.out.printf("The extreme weather killed your %s!\n", flowerList.get(lostFlower).getName());
					flowerList.remove(lostFlower);
				}
				break;
			default:
				System.err.println("error");
			}
		}
	}

	/*-*
	 * A series of if else if statements that checks to see if
	 * the player wants to sell a flower, return to the main menu, or if they sold a valid flower to a customer
	 */
	private void sellCustomerAFlower(boolean[] customerFlowerMatch) {
		// declare variables
		Flower[] applicableFlowers;
		int lenApplicableFlowers;
		int customerChoice;
		int flowerChoice;
		int[] applicableFlowersInFlowerList;
		boolean errorChecking = true;
		int paymentAmount;
		int[] transaction = new int[2];

		do {
			System.out.printf(
					"\n\nEnter the number of the customer you would like to sell a flower to or enter %d to return to main menu.\n=>",
					customerList.size() + 1);
			customerChoice = Try.getInt(1, customerList.size() + 1, stdin);

			// user chooses not to sell a flower to a customer
			if (customerChoice == (customerList.size() + 1)) {
				System.out.println("You chose not to sell a flower to a customer.");
				errorChecking = false;

				// user sells a flower to a customer that wants their flower
			} else if (customerFlowerMatch[customerChoice - 1]) {
				// assign variables
				applicableFlowers = Customer.findFlowersForCustomer(flowerList, customerList.get(customerChoice - 1));
				lenApplicableFlowers = Customer.getLenApplicableFlowers(applicableFlowers);
				applicableFlowersInFlowerList = Customer.findApplicableFlowersInFlowerList(flowerList,
						applicableFlowers, lenApplicableFlowers);
				applicableFlowers = Customer.setSizesOfApplicableFlowers(flowerList, applicableFlowers,
						applicableFlowersInFlowerList, lenApplicableFlowers);

				// if the player is selling flowers to a critic, tell them to sell old flowers
				if (customerList.get(customerChoice - 1) instanceof Critic) {
					System.out.println(
							"A critic's rating is based on the age of your flower. Give the critic your oldest flower!");
				}

				// get flower choice
				System.out.println("Enter the flower you would like to sell.\n=>");
				for (int i = 0; i < lenApplicableFlowers; i++) {
					System.out.printf("\n\n%d:\n%s", i + 1, applicableFlowers[i]);
				}
				flowerChoice = Try.getInt(1, lenApplicableFlowers, stdin);

				// get transactions

				// transaction for a friend
				if (customerList.get(customerChoice - 1) instanceof Companion) {
					transaction = Customer.getTransaction(applicableFlowers[flowerChoice - 1],
							customerList.get(customerChoice - 1), 3);
					System.out.printf("You sold your %s to your friend for $%d and received a 5 star rating!\n",
							applicableFlowers[flowerChoice - 1].getName(), transaction[0]);

					// transaction for a critic
				} else if (customerList.get(customerChoice - 1) instanceof Critic) {
					transaction = Customer.getTransaction(applicableFlowers[flowerChoice - 1],
							customerList.get(customerChoice - 1), 3);
					System.out.printf(
							"You sold your %s %s to the flower critic for $15 and received a %d star rating!\n",
							applicableFlowers[flowerChoice - 1].getAge(), applicableFlowers[flowerChoice - 1].getName(),
							transaction[1]);
					criticIsSatisfied = true;
					if (flowerList.get(flowerChoice - 1).getSize() == 4) {
						System.out.println(
								"The critic was so impressed by your old flower that he gave you an additional 5-star rating!");
						ratings[numRatings] = 5;
						numRatings++;
						averageRating = getAverageRating();
					}

					// transaction for a normal customer
				} else {
					System.out.println("Enter how much you would like to sell your flower for.");
					Customer.printPriceOptions(applicableFlowers[flowerChoice - 1],
							customerList.get(customerChoice - 1));
					paymentAmount = Try.getInt(1, 3, stdin);
					transaction = Customer.getTransaction(applicableFlowers[flowerChoice - 1],
							customerList.get(customerChoice - 1), paymentAmount);
					System.out.printf("You sold your %s for $%d and received a %d star rating!\n",
							applicableFlowers[flowerChoice - 1].getName(), transaction[0], transaction[1]);
				}
				// Remove customers and flowers and adjust money and ratings
				flowerList.remove(applicableFlowersInFlowerList[flowerChoice - 1]);
				customerList.remove(customerChoice - 1);
				money += transaction[0];
				ratings[numRatings] = transaction[1];
				numRatings++;
				averageRating = getAverageRating();
				errorChecking = false;
			} else {
				System.out.printf(
						"You do not have a flower that Customer #%d would want.\n"
								+ "Please reenter your choice or enter %d to exit",
						customerChoice, customerList.size() + 1);
			}
		} while (errorChecking);
	}

	private void printFinalGameStats() {

		String title;
		// If the user sold no flowers, they get a 0 rating
		if (numRatings == 1) {
			averageRating = 0;
		}
		//@formatter:off
		//generate a title based on the money made and rating
		if (money > 100) {
			if (averageRating > 4.5) {
				title = "Congratulations, you're a Flower Powerhouse! (Best Rating)"; 											// Flower Powerhouse
			} else if (averageRating > 2.5) {
				title = "Amazing job, you're a Master of the Petals!"; 															// Master of the Petals
			} else {
				title = "Customers are disgruntled, but you are rich!\nYou earned the title Floral Fortune!";					//Floral Fortune
			}
		} else if (money > 40) {
			if (averageRating > 4.5) {
				title = "You made some sacrifices to please your customers.\nYou're a Bouquet Baron!";							//Bouquet Baron
			} else if (averageRating > 2.5) {
				title = "You came middle of the road in everything!\nYou're a Fragrant Entrepreneur! (Middle Rating)";			//Fragrant Entrepreneur
			} else {
				title = "Customers are disgruntled, but at least you're not poor. You earned the title Blooming Business!";		//Blooming Business
			}
		} else {
			if (averageRating > 4.5) {
				title = "You're the robin hood of flowers...\n Your title is Rose Ruler!";										//Rose Ruler
			} else if (averageRating > 2.5) {
				title = "You're poor, but you managed an average rating nonetheless.\nYou're a Serene Shopkeeper!";				//Fragrant Entrepreneur
			} else {
				title = "Customers are disgruntled and you're dirt poor!\nYou earned the title Petal Peddler! (Worst Rating)";	//Petal Peddler
			}
		}


		System.out.printf("\n".repeat(5)
				+ "Congratulations, you survived five days!\n"
				+ "Store name: %s\n"
				+ "Final amount of money: %d\n"
				+ "Flowers Sold: %d\n"
				+ "Store Rating: %.1f\n"
				+ "%s"
				, nameFlowerShop
				, money
				, numRatings - 1 // accounts for the user initially starting at a five-star rating
				, averageRating
				, title);
		//@formatter:on
	}

}
