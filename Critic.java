/**
 * Flower critics always give $15 for a flower.
 * Their rating is based on the age of the flower.
 * If you end the day without servicing a flower critic, you receive three one star ratings.
 *
 * Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/20/2022
 * @version DiscWeek3.0
 * @author fineh
 */
package FinehoutIsaac_DiscWeek3;

public class Critic extends Customer {

	// assign/declare critic attributes
	private String criticName;

	public Critic(boolean[] criticHasPreference, int[] criticPreferences) {

		super(criticHasPreference, criticPreferences, 1, 1);

		// Declare variables
		if (criticHasPreference[0]) {
			criticName = String.format("%s Color Critic", COLORS[criticPreferences[0]]);
		} else if (criticHasPreference[1]) {
			criticName = String.format("%s Blossom Critic", BLOSSOM_NAMES[criticPreferences[1]],
					BLOSSOMS[criticPreferences[1]]);
		} else if (criticHasPreference[2]) {
			criticName = String.format("%s Stem Critic", STEM_NAMES[criticPreferences[2]], STEMS[criticPreferences[2]]);
		}
	}

	@Override
	public String toString() {
		// declare variables
		String critic = "";

		critic += "CRITIC\n";
		critic += criticName + "\n";

		// Add preference onto the critics String
		if (this.getHasPreferences()[0]) {
			critic += String.format("Color: %s\n", COLORS[this.getPreferences()[0]]);
		}
		if (this.getHasPreferences()[1]) {
			critic += String.format("Blossom: %s (%s)\n", BLOSSOM_NAMES[this.getPreferences()[1]],
					BLOSSOMS[this.getPreferences()[1]]);
		}
		if (this.getHasPreferences()[2]) {
			critic += String.format("Stem: %s (%s)\n", STEM_NAMES[this.getPreferences()[2]],
					STEMS[this.getPreferences()[2]]);
		}

		return critic;
	}

	// Discussion 6 / Isaac Finehout / CMIS 242 6384 / 10 February 2023
	// Overloading
	@Override
	public void describeCustomer() {
		System.out.println("The flower critic showed up in a suit, a tie, and a mood to rate your flowers.");
	}

	// Overloading
	@Override
	public void describeCustomer(String nameFlowerShop) {
		System.out.println("The flower critic showed up in a suit, a tie, and a mood to rate " + nameFlowerShop + ".");
	}
}
