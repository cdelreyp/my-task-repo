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
			int dice1 = Utils.rollDice();
			int dice2 = Utils.rollDice();
			System.out.println("Dados1: " + dice1 );
			System.out.println("Dados2: " + dice2 );
			
			if (move % 2 == 1) {
				// player 1 hace cosas
				player1.setPosition(board.calculatePosition(player1.getPosition() + dice1 + dice2));
				System.out.println("Player1 - " + player1.getPosition() );
				gameOver = (player1.getPosition() == 100);
			}

			if (move % 2 == 0) {
				// player 2 hace cosas
				player2.setPosition(board.calculatePosition(player2.getPosition() + dice1 + dice2));
				System.out.println("Player2 - " + player2.getPosition() );
				gameOver = (player2.getPosition() == 100);
			}

			if(dice1 != dice2) move++;

		}
		// Comprobar ganador
		if(player1.getPosition() == 100) System.out.println("Player1 ha ganado" );
		else System.out.println("Player2 ha ganado" );

	}
	

}
