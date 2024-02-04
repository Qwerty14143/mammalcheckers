
public class Mouse extends Elephant {

	public Mouse(String symbol,String owner) {
		super(symbol,owner);
		// TODO Auto-generated constructor stub
	}
	public void eat() {
		if (canEat()) {
			gameBoard.getBoard()[newRow][newColumn] = this; // Place the mammal in the captured position
			gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*",null); // Make the original position empty
		}
	}
	public Boolean canEat(){
		int middleRow = (currentRow+newRow) / 2;
		int middleColumn = (currentColumn+newColumn) / 2;

		if (isValidMove()) {
			if (gameBoard.getBoard()[middleRow][middleColumn] instanceof Mammal) {
				Mammal middleMammal = (Mammal) gameBoard.getBoard()[middleRow][middleColumn];
				if (!middleMammal.getOwner().equals(this.getOwner())) {
					return true;
				}
			}
		}
		return false;
	}
	
}
