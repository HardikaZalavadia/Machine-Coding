package TicTacToe.models;

public class Moves {
    private Players players;

    private Cell cell;

    public Moves(Players players, Cell cell) {
        this.players = players;
        this.cell = cell;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
