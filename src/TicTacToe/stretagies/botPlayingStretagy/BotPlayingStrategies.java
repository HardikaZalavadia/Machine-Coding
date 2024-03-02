package TicTacToe.stretagies.botPlayingStretagy;

import TicTacToe.models.Board;
import TicTacToe.models.Cell;
import TicTacToe.models.Moves;

public interface BotPlayingStrategies {
    public Cell makeMove(Board board);
}
