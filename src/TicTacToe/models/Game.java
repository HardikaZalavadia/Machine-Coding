package TicTacToe.models;

import TicTacToe.exceptions.MorePlayersException;
import TicTacToe.exceptions.MorethanOneBotException;
import TicTacToe.exceptions.DuplicateSymbolException;
import TicTacToe.stretagies.winningStretagy.WinningStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Players> player;
    private Board board;
    private List<Moves> moves;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategies> winningStrategy;
    private Players winner;

    public Players getWinner() {
        return winner;
    }

    public void setWinner(Players winner) {
        this.winner = winner;
    }

    private Game(int dimension, List<Players> player, List<WinningStrategies> winningStrategy) {
        this.board = new Board(dimension);
        this.winningStrategy = winningStrategy;
        this.player = player;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard(){
        board.printBoard();
    }
    public void makeMove(){
        Players players = player.get(nextPlayerIndex);
        Cell cell = players.makeMove(board);

        Moves move = new Moves(players, cell);
        moves.add(move);

        if(checkFinalWinner(board,move)){
            gameState = GameState.SUCCESS;
            winner = players;
            return;
        }
        if(moves.size() == board.getSize() * board.getSize()){
            gameState = GameState.DRAW;
            return;
        }
        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % player.size();
    }

    private boolean checkFinalWinner(Board board, Moves move) {
        for(WinningStrategies winningStrategy : winningStrategy){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public void undo(){
        Moves lastMove = removeLastMove();
        if(lastMove==null){
            return;
        }
        updateCellAndStrategy(lastMove);
        updateNextPlayer();
    }

    private void updateNextPlayer() {
        if(nextPlayerIndex != 0){
            nextPlayerIndex--;
        }
        else{
            nextPlayerIndex = player.size()-1;
        }
    }

    private void updateCellAndStrategy(Moves lastMove) {
        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayers(null);

        for(WinningStrategies winningStrategies : winningStrategy){
            winningStrategies.undo(lastMove,board);
        }
    }

    private Moves removeLastMove() {
        if(moves.size()==0){
            System.out.println("no move to undo....");
        }
        Moves lastMove = moves.get(moves.size()-1);
        moves.remove(lastMove);
        return lastMove;
    }

    public static class Builder{
        private List<WinningStrategies> winningStrategy;
        private List<Players> player;
        private int dimension;
        private Builder(){
            this.dimension = 0;
            this.player = new ArrayList<>();
            this.winningStrategy = new ArrayList<>();
        }
        public Game build() throws MorethanOneBotException, MorePlayersException, DuplicateSymbolException {
            // validate bot count, dimension and number of players, unique symbol
            vaidateBotCount();
            validateDimentionNumberOfPlayers();
            validateSymbol();
            return new Game(dimension,player,winningStrategy);
        }

        private void validateSymbol() throws DuplicateSymbolException {
            Set<Character> set = new HashSet<>();
            for(Players players : player){
                set.add(players.getSymbol());
            }
            if(set.size() < player.size()){
                throw new DuplicateSymbolException();
            }
        }

        private void validateDimentionNumberOfPlayers() throws MorePlayersException {
            if (player.size() != dimension-1) {
                throw new MorePlayersException();
            }
        }

        private void vaidateBotCount() throws MorethanOneBotException {
            int botCount = 0;
            for(Players players : player){
                if(players.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new MorethanOneBotException();
            }
        }


        public Builder setWinningStrategy(List<WinningStrategies> winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public Builder setPlayer(List<Players> player) {
            this.player = player;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
    }

    public List<Players> getPlayer() {
        return player;
    }

    public void setPlayer(List<Players> player) {
        this.player = player;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Moves> getMoves() {
        return moves;
    }

    public void setMoves(List<Moves> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<WinningStrategies> getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(List<WinningStrategies> winningStrategy) {
        this.winningStrategy = winningStrategy;
    }
}
