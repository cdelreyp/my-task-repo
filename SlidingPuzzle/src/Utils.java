
public class Utils {

	private static int SIZE = 4;

	public static void printBoard(int[][] board) {
		System.out.println("---------------------------------");
		for (int contX = 0; contX < SIZE; contX++) {
			for (int contY = 0; contY < SIZE; contY++) {
				System.out.print("| " + board[contX][contY] + " \t");
			}
			System.out.print("|\n");
		}
		System.out.println("---------------------------------");
	}

	public static boolean isSolved(int[][] board) {
		int last = 0;

		// If 0 is not bot-right is not solved
		if(board[SIZE-1][SIZE-1]!=0) {
			return false;
		} else {
			for (int contX = 0; contX < SIZE; contX++) {
				for (int contY = 0; contY < SIZE; contY++) {
					if (board[contX][contY] <= last && board[contX][contY] != 0) {
						return false;
					}
					last = board[contX][contY];
				}
			}
			return true;
		}
	}

	static Board moveUp(Board board) {

		int x = board.getRow0();
		int y = board.getCol0();

		if (x == 0)
			return board;

		int a = board.getPos(x - 1, y);

		board.setPos(x - 1, y, 0);
		board.setPos(x, y, a);

		board.setRow0(x - 1);

		return board;
	}

	public static Board moveDown(Board board) {

		int x = board.getRow0();
		int y = board.getCol0();

		if (x == SIZE - 1)
			return board;

		int a = board.getPos(x + 1, y);

		board.setPos(x + 1, y, 0);
		board.setPos(x, y, a);

		board.setRow0(x + 1);

		return board;
	}

	public static Board moveLeft(Board board) {

		int x = board.getRow0();
		int y = board.getCol0();

		if (y == 0)
			return board;

		int a = board.getPos(x, y - 1);

		board.setPos(x, y - 1, 0);
		board.setPos(x, y, a);

		board.setCol0(y - 1);

		return board;
	}

	public static Board moveRight(Board board) {

		int x = board.getRow0();
		int y = board.getCol0();

		if (y == SIZE - 1)
			return board;

		int a = board.getPos(x, y + 1);

		board.setPos(x, y + 1, 0);
		board.setPos(x, y, a);

		board.setCol0(y + 1);

		return board;
	}
}