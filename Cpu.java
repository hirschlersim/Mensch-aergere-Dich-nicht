import java.util.concurrent.TimeUnit;

/**
 * The Cpu class represents a computer-controlled player in the game.
 */
public class Cpu extends Player {

	/**
	 * Constructs a new Cpu object with the specified board and dice.
	 *
	 * @param board the game board
	 * @param dice  the dice for generating random numbers
	 */
	public Cpu(Board board, Dice dice) {
		super(board, dice);
	}

	/**
	 * Performs the next turn for the CPU player. The CPU player rolls the dice,
	 * sorts the figures based on evaluation, and moves the highest evaluated figure
	 * that can make a valid move.
	 */
	@Override
	public void nextTurn() {

		try { // Adding a delay to make the CPU more "human"
			TimeUnit.MILLISECONDS.sleep(dice.delay());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		int value = dice.diceThrow();
		int i = 0;
		System.out.println("CPU " + team + " hat eine " + value + " gewuerfelt");
		sortFiguresAfterEva(value);
		while (i < 4 && !board.moveFigure(figures[i++], value, spaceFree)) {
		}
		
		if (value == 6) {
			System.out.println(board);
			nextTurn();
		}
	}

	/**
	 * Sorts the figures based on evaluation after rolling the dice. The evaluation
	 * takes into account the possibility of hitting other players and the relative
	 * position of the figures on the board.
	 *
	 * @param diceValue the value obtained from rolling the dice
	 */
	private void sortFiguresAfterEva(int diceValue) {

		// go through every figure and check the evaluation.
		int[] evaluation = new int[4];
		Figure[] result = new Figure[4];

		final int faktorHit = Integer.MAX_VALUE / 2;
		final int faktorPosition = 1;

		for (int i = 0; i < figures.length; i++) { // init the evaluation array
			evaluation[i] = board.getMoveEvaluation(figures[i], diceValue); // 1 if we hit another Player else 0
			evaluation[i] *= faktorHit;

			evaluation[i] += figures[i].getRelPosition() * faktorPosition; // how far is the figure with a factor

			if (figures[i].getRelPosition() + diceValue >= 40) { // can this figure move in the "home"
				evaluation[i] = Integer.MAX_VALUE; // this is the best possible move
			}
		}

		// sort the array
		for (int iResult = 0; iResult < result.length; iResult++) {

			// current max evaluation
			int evaMax = evaluation[0];
			int indexMax = 0;

			// loop through the evaluation array to get the currently best figure
			for (int i = 1; i < evaluation.length; i++) {
				if (evaMax < evaluation[i]) {
					indexMax = i;
					evaMax = evaluation[i];
				}
			}
			// now we found the best index -> update result array
			result[iResult] = figures[indexMax];
			evaluation[indexMax] = Integer.MIN_VALUE; // this figure has been used so the evaluation goes to an error
														// state
		}
		figures = result;
	}
}
