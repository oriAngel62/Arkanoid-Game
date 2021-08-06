package game;

import biuoop.KeyboardSensor;
import levels.FirstLevel;
import levels.LevelInformation;
import levels.SecondLevel;
import levels.ThirdLevel;
import levels.FourthLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.GameFlow class
 * responsible for the levels in the game.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> levelInformation;
    private List<Integer> levelList;

    /**
     * Constructor.
     * build the system for the levels.
     *
     * @param ar        run the levels.
     * @param ks        waiting for key press.
     * @param levelList list of levels.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, List<Integer> levelList) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.levelInformation = new ArrayList();
        this.levelList = levelList;
        buildLevels();
        runLevels(levelInformation);


    }

    /**
     * buildLevels.
     * build the levels.
     */
    public void buildLevels() {

        FirstLevel firstLevel = new FirstLevel();
        SecondLevel secondLevel = new SecondLevel();
        ThirdLevel thirdLevel = new ThirdLevel();
        FourthLevel fourthLevel = new FourthLevel();

        if (levelList.size() != 0) {
            for (int i = 0; i < levelList.size(); i++) {
                if (levelList.get(i) == firstLevel.levelNumber()) {
                    levelInformation.add(firstLevel);
                }
                if (levelList.get(i) == secondLevel.levelNumber()) {
                    levelInformation.add(secondLevel);
                }
                if (levelList.get(i) == thirdLevel.levelNumber()) {
                    levelInformation.add(thirdLevel);
                }
                if (levelList.get(i) == fourthLevel.levelNumber()) {
                    levelInformation.add(fourthLevel);
                }
            }
        } else {
            levelInformation.add(firstLevel);
            levelInformation.add(secondLevel);
            levelInformation.add(thirdLevel);
            levelInformation.add(fourthLevel);
        }
    }

    /**
     * runLevels.
     * run the levels.
     *
     * @param levels the list of the level information.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter(0);
        Counter lives = new Counter(7);
        int countLevels = 1;
        Animation a2 = new PauseScreen(keyboardSensor);
        Animation a1k = new KeyPressStoppableAnimation(keyboardSensor, "m", a2);
        for (LevelInformation levelInfo : levels) {
            GameLevel level;
            if (countLevels == levels.size()) {
                level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives, true);
            } else {
                level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives, false);
            }
            level.initialize();
            level.run();
            score = level.getScore();
            //check for losing
            if (level.getEndGame()) {
                break;
            }
            countLevels++;
        }


    }
}


