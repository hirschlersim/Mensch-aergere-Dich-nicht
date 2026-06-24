import java.util.Scanner;

public class Game {

	public static void main(String[] args) {

		try {
			Player[] p = new Player[4];
			int num = -1; // error state for the user input
			int activePlayer = 3; // must be 3 so the "0" player start's and the loop terminates correctly
			Scanner scanner = new Scanner(System.in);
			Board board = new Board();
			int round = 1;
			Dice dice = new Dice(); // known good seed 12562474

			do {
				System.out.println("How many players? (1, 2, 3 or 4)"); // get the amount of human Players
				num = scanner.nextInt();
			} while (num < 0 || 4 < num);

			for (int i = 0; i < num; i++) { // human players always go first
				p[i] = new Human(board, dice);
			}
			for (int i = num; i < 4; i++) {
				p[i] = new Cpu(board, dice);
			}

			do {
				System.out.println("\nRunde: " + round++);
				activePlayer = (activePlayer + 1) % 4; // its the next's players turn
				p[activePlayer].nextTurn(); // player makes his move
				for(int i=0; i<4; i++) {
					System.out.print("Team " + (i+1) + ": ");
					for(int j=0; j<4; j++) {
						if(p[i].spaceFree[j]) System.out.print("o");
						else System.out.print("x");
					}
					System.out.println();
				}
				System.out.println(board);
			} while (!p[activePlayer].hasWon()); // the move lead to a win

			scanner.close();

		} catch (IndexOutOfBoundsException iobE) {
			iobE.printStackTrace();
		} catch (NullPointerException npE) {
			npE.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
