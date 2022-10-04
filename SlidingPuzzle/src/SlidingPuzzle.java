import java.io.IOException;

public class SlidingPuzzle {

	// Players initialization

	private static Board board;

	public static void main(final String[] args) throws IOException {
		System.out.println("MAIN");

		board = new Board();

		Utils.printBoard(board.getBoard());

		while (true) {

			char move;


			move = (char) System.in.read();


			switch (move) {
			case 'w':
				board = Utils.moveUp(board);
				break;
			case 's':
				board = Utils.moveDown(board);
				break;
			case 'a':
				board = Utils.moveLeft(board);
				break;
			case 'd':
				board = Utils.moveRight(board);
				break;
			default:
				break;
			}
				

			Utils.printBoard(board.getBoard());
				

			}

		

	}

}
