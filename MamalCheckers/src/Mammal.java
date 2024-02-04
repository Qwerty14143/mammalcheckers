public class Mammal {
    public int newRow;
    public int newColumn;
    public int currentColumn;
    public int currentRow;
	static Board gameBoard = new Board();
    protected String symbol;
    protected String owner;

    public Mammal(String symbol, String owner) {
        this.symbol = symbol;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setOwner(String s) {
        this.owner = s;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean moveCompLegal() {
        if (this.owner.equals("2")) {
            return (newRow > currentRow) && ((newRow - currentRow) == 1) && isValidMove() && gameBoard.getBoard()[newRow][newColumn].getSymbol().equals("*");
        }
        return false;
    }

    public boolean canComputerMoveTwice() {
        boolean canMoveLeft = false;
        boolean canMoveRight = false;
        if (this.currentColumn - 1 >= 0 && gameBoard.getBoard()[this.currentRow + 1][this.currentColumn - 1].getOwner()=="0"){
        		
            canMoveRight = true;
        }
        if (this.currentColumn + 1 < 8 && gameBoard.getBoard()[this.currentRow + 1][currentColumn + 1] .getOwner()=="0") {
            canMoveLeft = true;
        }
        return canMoveRight && canMoveLeft;
    }

    public boolean canComputerMove() {
        boolean canMoveLeft = false;
        boolean canMoveRight = false;
        if (currentColumn - 1 >= 0 && gameBoard.getBoard()[currentRow + 1][currentColumn - 1].getOwner()=="0") {
            canMoveRight = true;
            gameBoard.getBoard()[currentRow + 1][currentColumn - 1] = gameBoard.getBoard()[currentRow][currentColumn];
            return true;

        }
        if (currentColumn + 1 < 8 && gameBoard.getBoard()[currentRow + 1][currentColumn + 1].getOwner()=="0"){
            canMoveLeft = true;
            gameBoard.getBoard()[currentRow + 1][currentColumn + 1] = gameBoard.getBoard()[currentRow][currentColumn];
            return true;
        }
        return false;
    }

    public void moveCompRandomly() {
        boolean moveRight = Math.random() <= 0.3;
        if (canComputerMoveTwice()) {
            if (moveRight) {
                gameBoard.getBoard()[currentRow + 1][currentColumn - 1] = gameBoard.getBoard()[currentRow][currentColumn];
                gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*", "0");
            } else {
                gameBoard.getBoard()[currentRow + 1][currentColumn + 1] = gameBoard.getBoard()[currentRow][currentColumn];
                gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*","0");
            }

        }
    }

    public boolean moveUserLegal() {
        if (this.owner.equals("1")) {
            if ((newRow < currentRow) && (currentRow - newRow == 1) && isValidMove() && gameBoard.getBoard()[newRow][newColumn].getSymbol().equals("*")) {
                return Math.abs(currentColumn - newColumn) == 1;
            }
        }
        return false;
    }

    public void movePieceUser() {
        if (moveUserLegal()) {
            gameBoard.getBoard()[newRow][newColumn] = gameBoard.getBoard()[currentRow][currentColumn];
            gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*", "0");
            // Additional logic here if needed
        } else {
            System.out.println("Invalid move");
        }
    }

    public void movePieceComp() {
        if (moveCompLegal()) {
            gameBoard.getBoard()[newRow][newColumn] = this;
            gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*", "0");
        } else {
            System.out.println("Invalid move");
        }
    }

    public Boolean canEatComputer() {
        int middleRow = (currentRow + newRow) / 2;
        int middleColumn = (currentColumn + newColumn) / 2;
        if (isValidMove()) {
            if (gameBoard.getBoard()[middleRow][middleColumn].getOwner().equals("1") && gameBoard.getBoard()[newRow][newColumn].getSymbol().equals("*")) {
                return true;
            }
        }
        return false;
    }

    public Boolean canEatUser() {
        int middleRow = (currentRow + newRow) / 2;
        int middleColumn = (currentColumn + newColumn) / 2;
        if (isValidMove()) {
            if (gameBoard.getBoard()[middleRow][middleColumn].getOwner().equals("2") && gameBoard.getBoard()[newRow][newColumn].getSymbol().equals("*")) {
                return true;
            }
        }
        return false;
    }

    public void eat() {
        int middleRow = (currentRow + newRow) / 2;
        int middleColumn = (currentColumn + newColumn) / 2;
        if (canEatUser()) {
            gameBoard.getBoard()[newRow][newColumn] = this; // Place the mammal in the captured position
            gameBoard.getBoard()[middleRow][middleColumn] = new EmptyCell("*", "0");
            gameBoard.getBoard()[currentRow][currentColumn] = new EmptyCell("*", "0"); // Make the original position empty
        } else {
            System.out.println("Cannot capture");
        }
    }

    public boolean isValidMove() {
        if (newRow >= 0 && newRow < 8 && newColumn >= 0 && newColumn < 8) {
            return true;
        }
        return false;
    }

    public String toString() {
        return symbol + owner; // You can customize this to return the name or any other information
    }
}
