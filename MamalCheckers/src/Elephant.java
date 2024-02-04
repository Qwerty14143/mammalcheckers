
public class Elephant extends Mammal {

	public Elephant(String symbol,String owner) {
		super(symbol,owner);
	}

	public boolean moveUserLegal() {
		int middleRow = (currentRow+newRow) / 2;
		int middleColumn = (currentColumn+newColumn) / 2;
		if (this.owner.equals("1")) {
			if((currentRow - newRow) == 1) {
				return (newRow < currentRow) && (((currentRow - newRow) == 1) || (currentRow - newRow) == 2) && isValidMove() && gameBoard.getBoard()[newRow][newColumn] instanceof EmptyCell;
			}
			else if((currentRow - newRow) == 2) {
				return isValidMove() && gameBoard.getBoard()[newRow][newColumn] instanceof EmptyCell && gameBoard.getBoard()[middleRow][middleColumn] instanceof EmptyCell ;
			}
		}
		return false;
	}

	public void movePieceUser() {
		if (moveUserLegal()) {
			gameBoard.getBoard()[newRow][newColumn] = this;
			gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*",null);
		} else {
			System.out.println("Invalid move");
		}
	}


}
