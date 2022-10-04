

public class Board {

	private static int SIZE=4;
	
	private int row0;
	private int col0;
	
	private int board[][];

	public Board() {
		
		this.board = new int[SIZE][SIZE];
		initTiles();
		
	}
	
	private void initTiles() {
		int cont=0;
		for(int contX=0; contX< SIZE;contX++) {
			for(int contY=0; contY< SIZE;contY++) {
				board[contX][contY] = cont;
				cont++;
			}
		}
		
		for(int i=0;i<50;i++) {
			int x1=(int)(Math.random() * SIZE);
			int y1=(int)(Math.random() * SIZE);
			int x2=(int)(Math.random() * SIZE);
			int y2=(int)(Math.random() * SIZE);
			
			int temp=board[x1][y1];
			board[x1][y1]=board[x2][y2];
			board[x2][y2]=temp;
		}
	}

	public int getRow0() {
		return row0;
	}
	
	public void setRow0(int row0) {
		this.row0 = row0;
	}
	
	public int getCol0() {
		return col0;
	}
	
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public void setCol0(int col0) {
		this.col0 = col0;
	}
	
	public int getPos(int x, int y) {
		return board[x][y];
	}
	
	public void setPos(int x, int y, int value)
	{
		this.board[x][y] = value;
	}
	
	

}
