
/**********************************************************
 * Program Name   : UserInterface
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class draws the background of each
 *  game state
 **********************************************************/
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UserInterface
{
    //class constants

    //class variables
    GamePanel game;

    BufferedImage mainMenuBack, pauseMenuBack, levelSelectBack,
    gameOverBack, levelCompleteBack;


    public UserInterface(GamePanel inGame)
    {
        //local constants

        //loca variables

        /***Start****/

        //instantiate game object
        game = inGame;

        //create menus
        createMenus();

    }//END constructor

    public void createMenus()
    {
        //local constants

        //local variables

        /*****start*****/

        //TRY
        try
        {
            //main menu
            mainMenuBack = ImageIO.read(getClass().getResource("/menuImages/mainMenu/main_background.png"));

            //pause menu
            pauseMenuBack = ImageIO.read(getClass().getResourceAsStream("/menuImages/pauseMenu/pause_background.png"));

            //level select menu
            levelSelectBack = ImageIO.read(getClass().getResourceAsStream("/menuImages/levelMenu/level_background.png"));

            //game over menu
            gameOverBack = ImageIO.read(getClass().getResourceAsStream("/menuImages/gameOverMenu/gameOver_background.png"));

            //level complete menu
            levelCompleteBack = ImageIO.read(getClass().getResourceAsStream("/menuImages/levelCompleteMenu/complete_background.png"));

        }
        //CATCH
        catch(IOException exception)
        {
            exception.printStackTrace();

        }//END TRY CATCH
        
    }

    public void draw(Graphics2D graphics)
    {
        //local constants

        //local variables

        /*********start**********/


        //IF(game is in pause state)
        if(game.getCurGameState() == game.PAUSE)
        {
            //draw pause screen
            drawPauseScreen(graphics);
        }
        //ELSE IF(game is in main menu state)
        else if(game.getCurGameState() == game.MAIN_MENU)
        {
            //draw main menu
            drawMainMenu(graphics);

        }
        //ELSE IF(game state is level select)
        else if(game.getCurGameState() == game.LEVEL_SELECT)
        {
            //draw level select menu
            drawLevelSelect(graphics);

        }//ELSEIF( game state is game over)
        else if (game.getCurGameState() == game.GAME_OVER)
        {
            //draw game over menu
            drawGameOver(graphics);

        }
        //ELSE IF(game state is level complete)
        else if(game.getCurGameState() == game.LEVEL_COMPLETE)
        {
            //draw level completed menu
            drawLevelComplete(graphics);

        }//END IF
        
    }//END draw

    public void drawLevelComplete(Graphics2D graphics)
    {
        //local constants

        //local variables

        /************start*************/

        //draw background
        graphics.drawImage(levelCompleteBack, 0, 0, null);

    }
    public void drawLevelSelect(Graphics2D graphics)
    {
        //local constants

        //local variables

        /************start*************/

        //draw background
        graphics.drawImage(levelSelectBack, 0, 0, null);

    }

    public void drawPauseScreen(Graphics2D graphics)
    {
        //local constants

        //local variables

        /************start*************/

        //draw background
        graphics.drawImage(pauseMenuBack, 0, 0, null);

    }

    public void drawMainMenu(Graphics2D graphics)
    {
        //local constants

        //local variables

        /************start*************/

        //draw background
        graphics.drawImage(mainMenuBack, 0, 0, null);


    }

    public void drawGameOver(Graphics2D graphics)
    {
        //local constants

        //local variables

        /************start*************/

        //draw background
        graphics.drawImage(gameOverBack, 0, 0, null);


    }
    
}
