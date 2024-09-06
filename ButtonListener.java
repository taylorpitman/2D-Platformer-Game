/**********************************************************
 * Program Name   : ButtonListener
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class lets the game panel
 * class know which button is being pressed and inputted
 *  by the user
 *
 * METHODS
 * ----
 * ButtonListener           - constructor
 * actionPreformed          - executes action based on button choice
 * levelCompleteMenuChoice  - changes game state 
 * gameOverMenuChoice       - changes game state 
 * mainMenuChoice           - changes game state 
 * pauseMenuChoice          - changes game state 
 * levelMenuChoice          - changes game state
 **********************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener
{
    //class constants

    //class variables
    GamePanel game;     //game panel class object

    /**********************************************************
     * Program Name   : ButtonListener
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: constructor method
     *
     * Pseudocode:
     * -------
     * BEGIN ButtonListener
     *  initialize game object
     * END ButtonListener
     **********************************************************/
    public ButtonListener(GamePanel inGame)
    {
        //local constants

        //local variables

        //initialize game object
        game = inGame;

    }//END constructor

   /**********************************************************
     * Program Name   : actionPerformed
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: this method takes in the input
     *  of the user and sends a direction back to game panel
     *
     * Pseudocode:
     * -------
     * BEGIN actionPerformed
     *  IF(game state is main menu)
     *      execute options
     *  ELSE IF(gamestate is pause)
     *      execute options
     *  ELSE IF(gamestate is level select)
     *      execute options
     *  ELSE IF(gamestate is game over)
     *      execute options
     *  ELSE IF(game state is level complete)
     *      execute options
     *  END IF
     * END actionPerformed
     **********************************************************/
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //IF(game state is main menu)
        if(game.getCurGameState() == game.MAIN_MENU)
        {
            //execute options
            mainMenuChoice(e);
        }
        //ELSE IF(game state is pause)
        else if(game.getCurGameState() == game.PAUSE)
        {
            //execute options
            pauseMenuChoice(e);

        }//ELSE IF(game state is level select)
        else if(game.getCurGameState() == game.LEVEL_SELECT)
        {
            //execute options
            levelMenuChoice(e);
        }
        //ELS IF(game state is game over)
        else if(game.getCurGameState() == game.GAME_OVER)
        {
            //execute options
            gameOverMenuChoice(e);
        }
        //ELSE IF(gamestate is level complete)
        else if(game.getCurGameState() == game.LEVEL_COMPLETE)
        {
            //execute options
            levelCompleteMenuChoice(e);

        }//END IF

    }//END actionPreformed

   /**********************************************************
     * Program Name   : levelCompleteMenuChoice
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: this method changes the game sstate
     *  and removes needed buttons
     *
     * Pseudocode:
     * -------
     * BEGIN levelCompleteMenuChoice
     *  IF(source is exit)
     *      change game state
     *  END IF
     *  remove buttons
     * END levelCompleteMenuChoice
     **********************************************************/
    private void levelCompleteMenuChoice(ActionEvent e) 
    {
        //local constants

        //local variables

        /******start********/

        //IF(source is exit)
        if(e.getSource() == game.pauseExit)
        {
            //change game state
            game.setCurGameState(game.MAIN_MENU);

        }//END IF

        //remove buttons
        game.remove(game.pauseExit);
        game.remove(game.coinsCollected);

    }//END levelCompleteMenuChoice

   /**********************************************************
     * Program Name   : gameOverMenuChoice
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: this method changes the game sstate
     *  and removes needed buttons
     *
     * Pseudocode:
     * -------
     * BEGIN gameOverMenuChoice
     *  IF(source is tryagain)
     *      change game state
     *  ELSE IF(source is exit)
     *      change gamestate
     *  END IF
     *  remove buttons
     * END gameOverMenuChoice
     **********************************************************/
    private void gameOverMenuChoice(ActionEvent e) 
    {
        //local constants

        //local variables

        /****start********/

        //IF(source is try again)
        if(e.getSource() == game.tryAgain)
        {
            //change gamestate
            game.setCurGameState(game.PLAY);

        }
        //ELSE IF(source is exit)
        else if(e.getSource() == game.pauseExit)
        {
            //change gamestate
            game.setCurGameState(game.MAIN_MENU);

        }//END IF

        //remove buttons             
        game.remove(game.tryAgain);
        game.remove(game.pauseExit);

    }//END gameOverMenuChoice

   /**********************************************************
     * Program Name   : mainMenuChoice
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: this method changes the game sstate
     *  and removes needed buttons
     *
     * Pseudocode:
     * -------
     * BEGIN mainMenuChoice
     *  IF(source is start)
     *      change game state
     *  ELSE IF(source is tutorial)
     *      change gamestate
     *      change current level
     *  ELSE IF(source is exit)
     *      system exit
     *  END IF
     *  remove buttons
     * END mainMenuChoice
     **********************************************************/
    public void mainMenuChoice(ActionEvent e)
    {
        //local constants

        //local variables

        /****start********/

        //IF(source is start)
        if(e.getSource() == game.mainStart)
        {
            //change game state
            game.setCurGameState(game.LEVEL_SELECT);

        }
        //ELSE IF(source is tutorial)
        else if(e.getSource() == game.mainTutorial)
        { 
            //change game state
            game.setCurGameState(game.PLAY); 

            //change current level
            game.setCurLevel(game.TUTORIAL);  
            game.getLevelManager().setLevel(game.getCurLevel());
            
        }
        //ELSE IF
        else if(e.getSource() == game.mainExit)
        {
            //exit 
            System.exit(0);

        }//END IF

        //remove buttons             
        game.remove(game.mainStart);
        game.remove(game.mainTutorial);
        game.remove(game.mainExit);

    }

   /**********************************************************
     * Program Name   : pauseMenuChoice
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: this method changes the game sstate
     *  and removes needed buttons
     *
     * Pseudocode:
     * -------
     * BEGIN pauseMenuChoice
     *  IF(source is continue)
     *      change game state
     *  ELSE IF(source is exit)
     *      change game state
     *      reset game
     *  END IF
     *  remove buttons
     * END pauseMenuChoice
     **********************************************************/
    public void pauseMenuChoice(ActionEvent e)
    {
        //local constants

        //local variables

        /****start********/

        //IF(source is continue game)
        if(e.getSource() == game.pauseContinue)
        {
            //change gamestate
            game.setCurGameState(game.PLAY);
            
        }
        //ELSE IF(game state is exit)
        else if(e.getSource() == game.pauseExit)
        {
            //change gamestate
            game.setCurGameState(game.MAIN_MENU);

            //reset game
            game.reset();
            
        }//END IF

        //remove buttons
        game.remove(game.pauseContinue);
        game.remove(game.pauseExit);

    }

   /**********************************************************
     * Program Name   : levelMenuChoice
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: this method changes the game sstate
     *  and removes needed buttons
     *
     * Pseudocode:
     * -------
     * BEGIN levelMenuChoice
     *  IF(source is level one)
     *      change current level
     *      update level manager
     *  ELSE IF(source is level two)
     *      change current level
     *      update level manager
     *  ELSE IF(source is level three)
     *      change current level
     *      update level manager
     *  END IF
     *  reset enemy manager
     *  switch game state
     *  remove buttons
     * END levelMenuChoice
     **********************************************************/
    public void levelMenuChoice(ActionEvent e)
    {
        //local constants

        //local variables

        /****start********/

        //IF(level one is pressed)
        if(e.getSource() == game.levelOne)
        {
            //change current level
            game.setCurLevel(game.LEVEL_ONE);
            
            //update level manager
            game.getLevelManager().setLevel(game.getCurLevel());

        }
        //ELSE IF(level two is pressed)
        else if(e.getSource() == game.levelTwo)
        {
            //change current level
            game.setCurLevel(game.LEVEL_TWO);

            //update level manager
            game.getLevelManager().setLevel(game.getCurLevel());
            
        }
        //ELSE IF(level three is pressed)
        else if(e.getSource() == game.levelThree)
        {
            //change current level
            game.setCurLevel(game.LEVEL_THREE);

            //update level manager
            game.getLevelManager().setLevel(game.getCurLevel());

        }//END IF

        //reset enemy manager
        game.getEnemyManager().reset();

        //switch game state to play 
        game.setCurGameState(game.PLAY);

        //remove buttons             
        game.remove(game.levelOne);
        game.remove(game.levelTwo);
        game.remove(game.levelThree);

    }//END METHOD

}//END CLASS