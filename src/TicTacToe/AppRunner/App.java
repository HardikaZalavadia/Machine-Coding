package TicTacToe.AppRunner;

import TicTacToe.controller.GameController;
import TicTacToe.exceptions.DuplicateSymbolException;
import TicTacToe.exceptions.MorePlayersException;
import TicTacToe.exceptions.MorethanOneBotException;
import TicTacToe.models.*;
import TicTacToe.stretagies.winningStretagy.ColmanWinningStrategy;
import TicTacToe.stretagies.winningStretagy.DiagonalWinningStrategy;
import TicTacToe.stretagies.winningStretagy.RowWinningStrategy;
import TicTacToe.stretagies.winningStretagy.WinningStrategies;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicateSymbolException, MorePlayersException, MorethanOneBotException {

        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int dimension = 3;
        List<Players> players = new ArrayList<>();
        List<WinningStrategies> winningStrategies = new ArrayList<>();

        players.add(new Players("Niv", PlayerType.HUMAN, 1L, 'X'));
        players.add(new Bot("Bot", PlayerType.BOT,2L,'0',DifficultyLevel.EASY));

        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColmanWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game = gameController.startGame(dimension,players,winningStrategies);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            game.printBoard();
            System.out.println("Dose any one wants to undo ? y/n");
            String undo = scanner.next();
            while(undo.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }
        if(game.getGameState().equals(GameState.SUCCESS)){
            System.out.println(game.getWinner().getName()+" is winner...");
        }
        if(game.getGameState().equals(GameState.DRAW)){
            System.out.println("Game is draw");
        }


    }
}
