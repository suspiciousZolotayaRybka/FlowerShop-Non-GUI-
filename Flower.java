/**
 * This contains a constructor for flowers, as well as get and set methods, a toString method,
 * and methods to water, pluck, plant, and trim flowers
 *
 * Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/20/2022
 * @version DiscWeek3.0
 * @author fineh
 */
package FinehoutIsaac_DiscWeek3;

import java.util.ArrayList;
import java.util.Scanner;

public class Flower implements FlowerParts {

	private int size;
	private final int blossom;
	private final int stem;
	private final int color;
	private final String name;
	private final String colorANSII;

	// Constructor for Flower.
	public Flower(int color, int blossom, int stem) {
		size = 0;
		this.color = color;
		this.blossom = blossom;
		this.stem = stem;
		name = String.format("%s %s %s", COLORS[color], BLOSSOM_NAMES[blossom], STEM_NAMES[stem]);

		switch (color) {
		case (0):
			colorANSII = PINK;
			break;
		case (1):
			colorANSII = RED;
			break;
		case (2):
			colorANSII = YELLOW;
			break;
		case (3):
			colorANSII = BLUE;
			break;
		case (4):
			colorANSII = ORANGE;
			break;
		default:
			colorANSII = PINK;
			System.err.println("flower constructor error");
			break;
		}
	}

	// Setters and Getters
	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public String getAge() {
		return SIZE[size];
	}

	public String getName() {
		return name;
	}

	public int getBlossom() {
		return blossom;
	}

	public int getColor() {
		return color;
	}

	public int getStem() {
		return stem;
	}

	@Override
	public String toString() {

		String f = "";
		int size = this.size + 2;

		// Creating a header for flower name, color, and Size @formatter:off
		f += String.format("name=%s, size=%s\n"
				+ "=".repeat(20) + "\n" + colorANSII
				, name, SIZE[this.size]);

		// nested for loop that creates a diamond based on the size (from 1-5) for the
		// flower blossom
		for (int row = 1; row <= ((size * 2) - 1); row++) {
			for (int column = 1; column <= ((size * 2) - 1); column++) {
				if ((((row <= size) && (column == ((size - row) + 1))) || (column == ((size + row) - 1)))
						|| (((row >= size) && (column == ((row - size) + 1)))
								|| (column == ((2 * size) - ((row - size) + 1))))) {
					f += BLOSSOMS[blossom]; // Adding the blossom type onto the String if if falls into the correct position
				} else {
					f += " ";
				}
			}
			f += "\n";
		}

		//Changing color code to green
		f += RESET + GREEN;

		// Adding the flower stem based on flower size
		String stem;
		switch (size) {
		case 2:
			stem = String.format("%2s\n"
					, STEMS[this.stem]);
			f += stem.repeat(2);
			break;
		case 3:
			stem = String.format("%3s\n"
					, STEMS[this.stem]);
			f += stem.repeat(3);
			break;
		case 4:
			stem = String.format("%4s\n"
					, STEMS[this.stem]);
			f += stem.repeat(3);
			break;
		case 5:
			stem = String.format("%5s\n"
					, STEMS[this.stem]);
			f += stem.repeat(4);
			break;
		case 6:
			stem = String.format("%6s\n"
					, STEMS[this.stem]);
			f += stem.repeat(5);
			break;
		}

		//End ANSII color code
		f += RESET;

		return f;
		//@formatter:on
	}

	/*-*
	 *   The methods in Flower are all static. They use static binding.
	 *   This means they are bound to the class Flower at compile time,
	 *   rather than being bound to an instance object of Flower at runtime.
	 */
	// creates a new flower in the parameter flowerList
	protected static ArrayList<Flower> plantFlowers(ArrayList<Flower> flowerList) {
		// declare variables
		Scanner stdin = new Scanner(System.in);
		int blossom;
		int stem;
		int color;

		// Get the flower's color @formatter:off
		System.out.println("1 - Pink\n"
				+ "2 - Red\n"
				+ "3 - Blue\n"
				+ "4 - White\n"
				+ "5 - Orange\n"
				+ "What color is your flower?\n" + "=>");
		color = (Try.getInt(1, 5, stdin) - 1); // Subtract one to account for index starting at 0 @formatter: on

		// Find the blossom material @formatter:off
		System.out.println("1 - o (American)\n"
				+ "2 - @ (European)\n"
				+ "3 - ф (Russian)\n"
				+ "4 - ぎ (Japanese)\n"
				+ "5 - ؟ (Middle-Eastern)\n"
				+ "Enter BLOSSOM.\n"
				+ "=> ");
		blossom = (Try.getInt(1, 5, stdin) - 1); // Subtract one to account for index starting at 0 @formatter:on

		// Find the stem material @formatter:off
		System.out.println("1 - │ (Tulip)\n"
				+ "2 - ║ (Rose)\n"
				+ "3 - ◠ (Marigold)\n"
				+ "4 - △ (Peony)\n"
				+ "5 - ▞ (Lily)\n"
				+ "Enter STEM.\n"
				+ "=>");
		stem = (Try.getInt(1, 5, stdin) - 1); // Subtract one to account for index starting at 0 @formatter: on


		// adding flower to list
		Flower f = new Flower(color, blossom, stem);
		flowerList.add(f);

		// Print the new flower name
		System.out.printf("%sCongratulations on your new %s!\n" , "\n".repeat(5) , flowerList.get(flowerList.size() - 1).name);

		return flowerList;
	}

	protected static ArrayList<Flower> waterFlowers(ArrayList<Flower> flowerList) {
		// declare variables
		int sizeCurrentFlower;

		for (int i = 0; i < flowerList.size(); i++) {
			sizeCurrentFlower = flowerList.get(i).getSize();
			if (sizeCurrentFlower == 4) {
				System.out.printf("%s was old and died!\n", flowerList.get(i).getName());
				flowerList.remove(i);
				flowerList = waterFlowers(flowerList);
			} else {
				flowerList.get(i).setSize(sizeCurrentFlower + 1);
			}
		}
		return flowerList;
	}

	protected static ArrayList<Flower> trimFlowers(ArrayList<Flower> flowerList) {
		// declare variables
		int sizeCurrentFlower;

		for (int i = 0; i < flowerList.size(); i++) {
			sizeCurrentFlower = flowerList.get(i).getSize();
			if (flowerList.get(i).getSize() == 0) {
				System.out.printf("%s is young and was not able to be trimmed\n", flowerList.get(i).getName());
			} else {
				flowerList.get(i).setSize(sizeCurrentFlower - 1);
			}
		}
		return flowerList;
	}

	protected static ArrayList<Flower> pluckFlowers(ArrayList<Flower> flowerList) {
		// declare variables
		Scanner stdin = new Scanner(System.in);
		int maxMenuChoice = 1;
		int pluckChoice;

		// print pluck menu
		for (int i = 0; i < flowerList.size(); i++) {
			System.out.printf("%d - %s\n", i + 1, flowerList.get(i).getName());
			maxMenuChoice++;
		}
		System.out.printf("%d - Leave without plucking\n", maxMenuChoice);
		pluckChoice = (Try.getInt(1, maxMenuChoice, stdin) - 1);
		if (maxMenuChoice == (pluckChoice + 1)) {
			return flowerList;
		} else {
			System.out.printf("You plucked %s.\n", flowerList.get(pluckChoice).getName());
			flowerList.remove(pluckChoice);
		}
		return flowerList;
	}
}
