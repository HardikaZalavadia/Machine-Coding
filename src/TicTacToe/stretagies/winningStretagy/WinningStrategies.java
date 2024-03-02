package TicTacToe.stretagies.winningStretagy;

import TicTacToe.models.Board;
import TicTacToe.models.Moves;

public interface WinningStrategies {
    public boolean checkWinner(Board board, Moves moves);
    void undo(Moves move,Board board);
}
