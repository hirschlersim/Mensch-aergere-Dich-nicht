import java.util.Random;

/**
 * The Dice class represents a dice that can be thrown to get a random number.
 */
public class Dice {

	private Random dice;

	/**
	 * Constructs a new Dice object with a random seed.
	 */
	public Dice() {
		dice = new Random();
	}

	/**
	 * Constructs a new Dice object with the specified seed.
	 *
	 * @param seed the seed for generating random numbers
	 */
	public Dice(long seed) {
		dice = new Random(seed);
	}

	/**
	 * Throws the dice and returns a random number between 1 and 6.
	 *
	 * @return the random number obtained from throwing the dice
	 */
	public int diceThrow() {
		return dice.nextInt(6) + 1;
	}

	/**
	 * Generates a random delay between 0 and 2500 milliseconds.
	 *
	 * @return the random delay in milliseconds
	 */
	public int delay() {
		return dice.nextInt(2500);
	}

}
