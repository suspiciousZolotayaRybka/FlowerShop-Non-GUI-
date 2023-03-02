/**
 * The player is told what flower preferences their two friends have at the beginning of the round.
 * Friends give increased money and always give a 5 star rating.
 *
 * Since friend is a reserved word in C++, I changed all references to friend as "companion"
 *
 * Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/20/2022
 * @version DiscWeek3.0
 * @author fineh
 */
package FinehoutIsaac_DiscWeek3;

import java.util.Random;

public class Companion extends Customer {

	/*-*
	 * Each friend has 3 preferences and a moneyCoefficient of 3 (+ 1 in Customer.getTransaction)
	 */

	public Companion(int[] friendsPreference) {
		// Setup a friend
		super(new boolean[] { true, true, true }, friendsPreference, 3, 4);
	}

	/*-*
	 * Allows the user to select what flowers their two friends like
	 */
	public static Companion[] getNewCompanions() {
		// Declare variables
		Companion[] companions = new Companion[2];
		Random ran = new Random();
		int[][] companionsPreferences = new int[2][3];

		// Random friend #1
		companionsPreferences[0] = new int[] { ran.nextInt(4), ran.nextInt(4), ran.nextInt(4) };
		companions[0] = new Companion(companionsPreferences[0]);

		// Random friend #2
		companionsPreferences[1] = new int[] { ran.nextInt(4), ran.nextInt(4), ran.nextInt(4) };
		companions[1] = new Companion(companionsPreferences[1]);

		return companions;
	}

	@Override
	public String toString() {
		// declare variables
		String companion = "";

		companion += String.format("FRIEND\nPreference(s): 3 ($$$$)\n");
		// add friends preferences
		companion += String.format("Color: %s\n", COLORS[this.getPreferences()[0]]);
		companion += String.format("Blossom: %s (%s)\n", BLOSSOM_NAMES[this.getPreferences()[1]],
				BLOSSOMS[this.getPreferences()[1]]);
		companion += String.format("Stem: %s (%s)\n", STEM_NAMES[this.getPreferences()[2]],
				STEMS[this.getPreferences()[2]]);

		return companion;
	}

	// Discussion 6 / Isaac Finehout / CMIS 242 6384 / 10 February 2023
	// Overloading
	@Override
	public void describeCustomer() {
		System.out.println("Your friend enjoys spending time with you and buying flowers.");
	}

	// Overloading
	@Override
	public void describeCustomer(String nameFlowerShop) {
		System.out.println("Your friend enjoys spending time with you and buying flowers at " + nameFlowerShop + ".");
	}
}
