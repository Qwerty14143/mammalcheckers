import java.util.Scanner;
public class Game {
static Scanner sc = new Scanner(System.in);
static Board gameBoard = new Board();

	public static void main(String[] args) {
		gameBoard.initializeGame();
		while (true) {
			UserMove();
			CompMove();

		}

	}

	private static void CompMove() {
		boolean computerMoved = false;// a helper flag to us
		if (caneat()) {//default move is to check if he can eat a pawn
		} else {
			do {
			    int randomRow = (int) (Math.random() * 8);
			    int randomColumn = (int) (Math.random() * 8);

			    if (gameBoard.getBoard()[randomRow][randomColumn].getOwner().equals("2")) {
			        // You have found a cell with a computer mammal

			        if (gameBoard.getBoard()[randomRow][randomColumn].canComputerMoveTwice()) {
			            gameBoard.getBoard()[randomRow][randomColumn].moveCompRandomly();
			            computerMoved = true;
			            break;
			        }

			        if (gameBoard.getBoard()[randomRow][randomColumn].canComputerMove()) {
			            gameBoard.getBoard()[randomRow][randomColumn].movePieceComp();
			            computerMoved = true;
			            break;
			        }
			    }

			} while (true);


		}
		System.out.println("Computer has played. ");
		gameBoard.printBoard();
	}

	private static boolean caneat() {// will check if the computer can eat a pawn
		Mammal[][] board_Temp = gameBoard.getBoard().clone();//build a helper 2Darray to eliminate fined W
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (gameBoard.getBoard()[i][j] == null) {
					board_Temp[i][j] = new EmptyCell("*",null);
				}
				if (gameBoard.getBoard()[i][j] != null){
					board_Temp[i][j] = gameBoard.getBoard()[i][j];
				}
			}
		}

		int randomRow = (int) (Math.random() * 8);
		int randomColumn = (int) (Math.random() * 8);

		while (CheckIfExist2(board_Temp)) {//check if there is still w left in our helper 2Darray

			while (board_Temp[randomRow][randomColumn].getOwner() != "2" ) {
				randomRow = (int) (Math.random() * 8);
				randomColumn = (int) (Math.random() * 8);
			}
			if (gameBoard.getBoard()[randomRow][randomColumn].canEatComputer()) {
				gameBoard.getBoard()[randomRow][randomColumn].eat();
			}

			board_Temp[randomRow][randomColumn].setOwner("?");// change the 2 to ? in our helper array
		}

		return false;
	}



	private static boolean CheckIfExist2(Mammal[][] board_Temp) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board_Temp[i][j].getSymbol().equals("2")) {
					return true;
				}
			}
		}
		return false;}

	public static void UserMove(){
		
		System.out.println("");
		System.out.println("It's your turn, please enter your move.");
		String playerMove = sc.next();

		if ("STOP".equals(playerMove)) {
			System.out.println("Sorry, computer has won :(");
			System.exit(0);
		}
		if (inputvalid(playerMove)) {
			String t1 = playerMove.substring(0, 1);
			String t2 = playerMove.substring(1, 2);
			String t3 = playerMove.substring(3, 4);
			String t4 = playerMove.substring(4, 5);

			int toRow = Integer.parseInt(t1);
			int toColumn = Integer.parseInt(t2);
			int beforeRow = Integer.parseInt(t3);
			int beforeColumn = Integer.parseInt(t4);

			beforeRow = 8 - beforeRow;
			beforeColumn = beforeColumn - 1;
			toRow = 8 - toRow;
			toColumn = toColumn - 1;
			Mammal movingPiece = gameBoard.getBoard()[beforeRow][beforeColumn];
			movingPiece.setGameBoard(gameBoard); // Set the game board reference
			movingPiece.currentRow = beforeRow; // Set the current position of the piece
			movingPiece.currentColumn = beforeColumn;
			movingPiece.newRow = toRow; // Target row to move to
			movingPiece.newColumn = toColumn; // Target column to move to
			movingPiece.movePieceUser(); // Attemp
			gameBoard.printBoard(); // Print the board state after the attempted move
		}

	}
	private static boolean inputvalid(String player_move) {
		for (int i=0;i<player_move.length();i++) {
			char currentChar = player_move.charAt(i);
			if ((currentChar < '1' || currentChar > '8') && currentChar != '-') {
				return false;
			}
		}
		return true;
	}
}
