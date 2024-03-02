package TicTacToe.models;

public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Players players;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public void display() {
        if(CellState.EMPTY.equals(cellState)){
            System.out.print("| - |");
        }
        else if(CellState.FILL.equals(cellState)){
            System.out.print("| " + players.getSymbol() + " |" );
        }
    }
}
