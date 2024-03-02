package TicTacToe.stretagies.botPlayingStretagy;

import TicTacToe.models.DifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategies getStrategyByDifficultyLevel(DifficultyLevel difficultyLevel){
        if(DifficultyLevel.EASY.equals(difficultyLevel)){
            return new EasyBotPlayingStrategy();
        }
         else{
             return new MediumBotPlayingStrategy();
        }

    }

}
