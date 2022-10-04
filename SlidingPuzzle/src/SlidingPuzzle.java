public class SlidingPuzzle {
	
	// Players initialization

	private static Board board;
	
	public static void main(final String[] args) {
		System.out.println("MAIN");

		board = new Board();

		Utils.printBoard(board.getBoard());
		
		while (true) {
			
			Utils.moveUp(board);
			
			Utils.printBoard(board.getBoard());

			break;
		}

	}
	

}
