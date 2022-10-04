
public class Utils {
	
	private static int SIZE=4;
	
	public void printBoard(int[][] board) {
		System.out.println("---------------");
		for(int contX=0; contX< SIZE;contX++) {
			for(int contY=0; contY< SIZE;contY++) {
				System.out.print("| "+board[contX][contY]+" \t");
			}
			System.out.print("|");
		}
		System.out.println("---------------");
	}
}