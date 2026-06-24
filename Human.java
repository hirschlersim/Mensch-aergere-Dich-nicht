import java.util.Scanner;

public class Human extends Player {

	public Human(Board board, Dice dice) {
		super(board, dice);
	}

	/**
	 * The main method for the Human class
	 * generates a random number for the dice throw, scans an input from the player for the figure to be moved
	 * checks for possible legal moves (if no legal moves: repeat input scan)
	 * if no movable figure: end of turn
	 * move the chosen figure, for the generated random number
	 */
	@Override
	public void nextTurn() {
		Scanner scanner = new Scanner(System.in);

		int value = dice.diceThrow();
//		int dice = 6;	//Testing for edgecases
		System.out.println("Du (" + team + ") hast eine " + value + " gewuerfelt");
		if (value < 6 && hasOnlyBase())
			return; // close scanner??
		// you need to roll a 6 to start the game

		int i = 0, input = 0;
		boolean moved = true;
		int[] illegal = { -1, -1, -1, -1 };

		do {
			if (!moved) {
				illegal[i++] = input;
				if (i == 4) {
					System.out.println("no legal move possible");
					return;
				}
				// end the round if there is no legal move possible
			}
			do {
				System.out.println("Waehle Figur zum bewegen");
				input = scanner.nextInt();

				for (int j = 0; j < 4; j++) {
					if (input == illegal[j])
						input = -1;
					// dont try the same move twice
				}
				if (input > 0 && input <= 4)
					input = check(value, input);
				// checks for game-rules
			} while (input < 1 || input > 4);
			moved = board.moveFigure(figures[input - 1], value, spaceFree);
		} while (!moved);
		// you do a move, unless you have no moving options
		System.out.println("Relative Position: " + figures[input - 1].getRelPosition());
		if (value == 6) {
			System.out.println(board);
			nextTurn();
		}
//		scanner.close(); // close scanner???
	}
	
	/**
	 * Checks if your figure is blocked by another figure of your colour or if you have a figure that blocks the Startfield
	 * Your Startfield must be free, unless your figure on start is being blocked
	 * If you throw a 6, you must get a figure out of your base,  unless it is being blocked by another figure of your colour
	 * @param dice
	 * @param input
	 * @return the index of the figure you want to play or
	 * @return -1, if the edge-cases weren't met
	 */
	public int check(int dice, int input) {
		int total;
		for (int i = 0; i < 4; i++) {
			total = figures[i].getRelPosition() + dice;
			if (total == dice && total == figures[input - 1].getRelPosition()) {
				System.out.println("*** Ausnahmefall 1 eingetreten ***");
				return input;
			}
			/*
			 * if you move a figure thats blocking another figure thats blocking start
			 * you're allowed to move this figure
			 */
			for (int j = 0; j < 4; j++) {
				if (total == dice && total == figures[j].getRelPosition()
						&& 2 * total == figures[input - 1].getRelPosition()) {
					System.out.println("*** Ausnahmefall 2 eingetreten ***");
					return input;
					/*
					 * if you move a figure, thats blocking a figure, thats blocking a figure, thats
					 * blocking start you're allowed to move this figure
					 */
				}
			}
			if (!isBaseEmpty() && figures[i].getRelPosition() == 0 && figures[i] != figures[input - 1]) {
				// you HAVE to move you figure from relPos 0
				System.out.println("Start blockiert");
				return -1;
			}
		}
		if (dice == 6 && !isBaseEmpty() && figures[input - 1].getRelPosition() > 0) {
			// if you roll a 6, you must get a figure out of your base
			// you have to consider that 1 figure could block the other 3 from leaving
			for (int i = 0; i < 4; i++) {
				if (figures[i].isBase()) {
					System.out.println("Du musst eine Figur aus der Base holen");
					return -1;
				}
			}
		}
		return input;
	}

}
