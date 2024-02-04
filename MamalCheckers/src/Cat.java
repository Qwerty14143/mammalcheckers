
public class Cat extends Mammal{

	public Cat(String symbol,String owner) {

		super(symbol, owner);
	}

	public boolean moveUserLegal() {
		if (this.owner.equals("1")) {
			if ((newRow <= currentRow) && ((currentRow - newRow) == 1 || (currentRow - newRow) == 0 ) && isValidMove() ) {
				if(Math.abs(currentColumn-newColumn)==1||Math.abs(currentColumn-newColumn)==0) {
					return true;
				}
			}
		}
		return false;
	}

	public void movePiece() {
		if (moveUserLegal() && gameBoard.getBoard()[newRow][newColumn].getSymbol().equals("*")) {
			gameBoard.getBoard()[newRow][newColumn] = this;
			gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*",null);
		} else if (moveUserLegal() && gameBoard.getBoard()[newRow][newColumn].getSymbol().equals("-")) {
			gameBoard.getBoard()[newRow][newColumn] = this;
			gameBoard.getBoard()[currentRow][currentColumn] = new UnreacheballeCell("-", null);
		} else {
			System.out.println("Invalid move");
		}
	}



}
