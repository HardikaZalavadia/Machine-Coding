package TicTacToe.models;

import TicTacToe.stretagies.botPlayingStretagy.BotPlayingStrategies;
import TicTacToe.stretagies.botPlayingStretagy.BotPlayingStrategyFactory;

public class Bot extends Players{
    private DifficultyLevel difficultyLevel;
    private BotPlayingStrategies botPlayingStrategies;

    public Bot(String name,PlayerType playerType,Long id, char symbol,DifficultyLevel difficultyLevel) {
        super(name,playerType,id,symbol);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategies = BotPlayingStrategyFactory.getStrategyByDifficultyLevel(difficultyLevel);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
