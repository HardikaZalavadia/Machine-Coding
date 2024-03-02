package TicTacToe.stretagies.winningStretagy;

import TicTacToe.models.Board;
import TicTacToe.models.Moves;
import TicTacToe.stretagies.winningStretagy.WinningStrategies;

import java.util.HashMap;
import java.util.Map;

public class ColmanWinningStrategy implements WinningStrategies {
    Map<Integer,Map<Character,Integer>> colMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Moves move) {

        int col = move.getCell().getCol();
        if(!colMap.containsKey(col)){
            colMap.put(col,new HashMap<>());
        }
        Map<Character,Integer> map = colMap.get(col);
        char symbol = move.getPlayers().getSymbol();
        if(!map.containsKey(symbol)){
            map.put(symbol,0);
        }
        map.put(symbol,map.get(symbol)+1);

        if(board.getCell().size() == map.get(symbol)){
            System.out.println("winner by col" + col);
            return true;
        }
        return false;
    }

    @Override
    public void undo(Moves lastMove, Board board) {
        int col = lastMove.getCell().getCol();
        char symbol = lastMove.getPlayers().getSymbol();
        Map<Character,Integer> map = colMap.get(col);
        map.put(symbol,map.get(symbol) - 1);


    }
}
