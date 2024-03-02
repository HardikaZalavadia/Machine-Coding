package TicTacToe.stretagies.botPlayingStretagy;

import TicTacToe.models.Board;
import TicTacToe.models.Cell;
import TicTacToe.models.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategies{
    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row : board.getCell()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return cell;
                }
            }
        }
        return null;
    }
}
