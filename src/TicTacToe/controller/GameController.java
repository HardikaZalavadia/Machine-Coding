package TicTacToe.controller;

import TicTacToe.exceptions.DuplicateSymbolException;
import TicTacToe.exceptions.MorePlayersException;
import TicTacToe.exceptions.MorethanOneBotException;
import TicTacToe.models.Board;
import TicTacToe.models.Game;
import TicTacToe.models.Players;
import TicTacToe.stretagies.winningStretagy.WinningStrategies;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Players> players, List<WinningStrategies> winningStrategy) throws DuplicateSymbolException, MorePlayersException, MorethanOneBotException {
        return Game.getBuilder()
                    .setDimension(dimension)
                .setPlayer(players)
                .setWinningStrategy(winningStrategy)
                .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void undo(Game game){
        game.undo();
    }
}
