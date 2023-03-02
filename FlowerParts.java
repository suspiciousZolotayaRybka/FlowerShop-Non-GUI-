/**
 * Contains constants used by Customer and Flower (inherited through Customer by Friend and Critic)
 *
 * Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/20/2022
 * @version DiscWeek3.0
 * @author fineh
 */
package FinehoutIsaac_DiscWeek3;

public interface FlowerParts {

	// Declare final variables
	String[] SIZE = { "Young ($)", "Young Adult ($$)", "Adult ($$$)", "Mature ($$$$)", "Old ($$$$$)" };
	String[] COLORS = { "Pink", "Red", "Yellow", "Blue", "Orange" };
	String[] BLOSSOMS = { "o", "@", "ф", "ぎ", "؟" };
	String[] BLOSSOM_NAMES = { "American", "European", "Russian", "Japanese", "Middle-Eastern" };
	String[] STEMS = { "│", "║", "◠", "△", "▞" };
	String[] STEM_NAMES = { "Tulip", "Rose", "Marigold", "Peony", "Lily" };

	String PINK = "\033[1;35m";
	String RED = "\033[1;31m";
	String YELLOW = "\033[1;33m";
	String BLUE = "\033[1;34m";
	String ORANGE = "\033[1;38;5;208m";
	String GREEN = "\033[32m";
	String RESET = "\033[0m";

	int COLOR_NUM = 0;
	int BLOSSOM_NUM = 1;
	int STEM_NUM = 2;

	int YOUNG_NUM = 0;
	int YOUNG__ADULT_NUM = 1;
	int ADULT_NUM = 2;
	int MATURE_NUM = 3;
	int OLD_NUM = 4;

	int PINK_NUM = 0;
	int RED_NUM = 1;
	int YELLOW_NUM = 2;
	int BLUE_NUM = 3;
	int ORANGE_NUM = 4;

	int AMERICAN_NUM = 0;
	int EUROPEAN_NUM = 1;
	int RUSSIAN_NUM = 2;
	int JAPANESE_NUM = 3;
	int MIDDLE_EASTERN_NUM = 4;

	int TULIP_NUM = 0;
	int ROSE_NUM = 1;
	int MARIGOLD_NUM = 2;
	int PEONY_NUM = 3;
	int LILY_NUM = 4;

}
