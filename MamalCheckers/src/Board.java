
public class Board  {
	private Mammal[][] board;

    public Board() {
        this.board = new Mammal[8][8];
       
    }

    public Mammal[][] getBoard() {  
    	return board;
    }

    public void initializeGame() {
        defualtBoard();
        printBoard();
    }

	public void defualtBoard() {

		this.board[0][1]= new Cat ("C","2");
		this.board[0][3]= new Dog ("D","2");
		this.board[0][5]= new Elephant ("E","2");
		this.board[0][7]= new Mouse ("M","2");
		this.board[1][0]= new Dog ("D","2");
		this.board[1][2]= new Elephant ("E","2");
		this.board[1][4]= new Mouse ("M","2");
		this.board[1][6]= new Cat ("C","2");
		this.board[2][1]= new Elephant ("E","2");
		this.board[2][3]= new Mouse ("M","2");
		this.board[2][5]= new Cat ("C","2");
		this.board[2][7]= new Dog ("D","2");

		this.board[7][0]= new Cat ("C","1");
		this.board[7][2]= new Dog ("D","1");
		this.board[7][4]= new Elephant ("E","1");
		this.board[7][6]= new Mouse ("M","1");
		this.board[6][1]= new Dog ("D","1");
		this.board[6][3]= new Elephant ("E","1");
		this.board[6][5]= new Mouse ("M","1");
		this.board[6][7]= new Cat ("C","1");
		this.board[5][6]= new Elephant ("E","1");
		this.board[5][4]= new Mouse ("M","1");
		this.board[5][2]= new Cat ("C","1");
		this.board[5][0]= new Elephant ("E","1");
	}


	 protected void printBoard() {
	        System.out.println("The board:");
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if (!(board[i][j] instanceof Mammal) && ((i + j) % 2 == 0)) {
	                    board[i][j] = new UnreacheballeCell("*", "0");
	                } else if (!(board[i][j] instanceof Mammal) && ((i + j) % 2 != 0)) {
	                    board[i][j] = new EmptyCell("-", "0");
	                }
	                System.out.print(board[i][j].toString());
	                System.out.print('\t');
	            }
	            System.out.println();
	            System.out.println();
	        }
	    }

	
	}



