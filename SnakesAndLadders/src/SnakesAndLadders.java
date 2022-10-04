public class SnakesAndLadders {
	
	// Players initialization
	private static Player player1;
	private static Player player2;
	private static Board board;
	
	public static void main(final String[] args) {
		System.out.println("MAIN");

		player1 = new Player();
		player2 = new Player();
		board = new Board();

		int move = 1;
		boolean gameOver = false;

		while (!gameOver) {
			if (move % 2 == 1) {
				// player 1 hace cosas
				player1.setPosition(board.calculatePosition(player1.getPosition() + Utils.rollDice()));
				System.out.println("Player1 - " + player1.getPosition() );
				gameOver = (player1.getPosition() == 100);
			}

			if (move % 2 == 0) {
				// player 2 hace cosas
				player2.setPosition(board.calculatePosition(player2.getPosition() + Utils.rollDice()));
				System.out.println("Player2 - " + player2.getPosition() );
				gameOver = (player2.getPosition() == 100);
			}

			move++;

		}
		// Comprobar ganador

	}
	

}
