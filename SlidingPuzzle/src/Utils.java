
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