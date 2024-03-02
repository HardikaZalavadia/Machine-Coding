package TicTacToe.stretagies.winningStretagy;

import TicTacToe.models.Board;
import TicTacToe.models.Moves;
import TicTacToe.stretagies.winningStretagy.WinningStrategies;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategies {
    Map<Integer,Map<Character,Integer>> rowMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Moves move) {
        int row = move.getCell().getRow();
        if(!rowMap.containsKey(row)){
            rowMap.put(row,new HashMap<>());
        }
        Map<Character,Integer> map = rowMap.get(row);
        char symbol = move.getPlayers().getSymbol();
        if(!map.containsKey(symbol)){
            map.put(symbol,0);
        }
        map.put(symbol,map.get(symbol)+1);

        if(board.getCell().size() == map.get(symbol)){
            System.out.println("winner by col" + row);
            return true;
        }
        return false;
    }

    @Override
    public void undo(Moves move, Board board) {
        int row = move.getCell().getRow();
        char symbol = move.getPlayers().getSymbol();
        Map<Character, Integer> map = rowMap.get(row);
        map.put(symbol,map.get(symbol)-1);
    }
}
