public class SnakesAndLadders {
	
	// Players initialization
	private static Player player1;
	private static Player player2;

	public static void main(final String[] args) {
		System.out.println("MAIN");

		player1 = new Player();
		player2 = new Player();

		int move = 1;
		boolean gameOver = false;

		while (!gameOver()) {
			if (move % 2 == 1) {
				// player 1 hace cosas
			}

			if (move % 2 == 0) {
				// player 2 hace cosas
			}

			move++;

		}

	}
	
	public static boolean gameOver() {
		return player1.getPosition() == 100 || player2.getPosition() == 100;
	}

}
