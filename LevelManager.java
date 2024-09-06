
/**********************************************************
 * Program Name   : LevelManager
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class manages all the levels
 *  in an array and keeps track of the current level the user
 *  is on.
 * 
 * METHODS
 * -----
 * LevelManager     - constructor method
 * inputMaps        - instaintates each level
 * setLevel         - sets curent level
 * draw             - draws current level
 * getCurLevel      - gets current level
 **********************************************************/

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class LevelManager
{
    //class constants
    final String TUTORIAL_FILE         = "/maps/levelTutorial.txt";
    final String LEVEL_ONE_FILE        = "/maps/levelOne.txt";
    final String LEVEL_TWO_FILE        = "/maps/levelTwo.txt";
    final String LEVEL_THREE_FILE      = "/maps/levelThree.txt";

    final String TUTORIAL_OBJ_FILE      = "/levels/tutorial_objects.txt";
    final String LEVEL_ONE_OBJ_FILE     = "/levels/levelOne_objects.txt";
    final String LEVEL_TWO_OBJ_FILE     = "/levels/LevelTwo_objects.txt";
    final String LEVEL_THREE_OBJ_FILE   = "/levels/levelThree_objects.txt";

    final String TUTORIAL_ENEMY_FILE      = "/levels/tutorial_enemies.txt";
    final String LEVEL_ONE_ENEMY_FILE     = "/levels/levelOne_enemies.txt";
    final String LEVEL_TWO_ENEMY_FILE     = "/levels/levelTwo_enemies.txt";
    final String LEVEL_THREE_ENEMY_FILE   = "/levels/levelThree_enemies.txt";

    final String CONTROLS = "controls.png";
    final String BUG_NOTICE = "bugNotice.png";

    final int TUTORIAL    = 0;
    final int LEVEL_ONE   = 1;
    final int LEVEL_TWO   = 2;
    final int LEVEL_THREE = 3;

    final int LEVEL_COUNT = 4;

    //class variables
    BufferedImage background;
    BufferedImage controls, bugNotice;
    GamePanel game;
    TileManager tileManager;
    InputStreamReader inputReader;
    private int level;
    Level[] levels;
    private Level curLevel;
 
   /**********************************************************
     * Method Name   : LevelManager
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This is the constructor for the 
     * level manager and instantiates all level objects
     * 
     * BEGIN LevelManager
     *  initialize data
     *  initialize levels array
     *  call input maps
     * END LevelManager
     **********************************************************/
    public LevelManager(GamePanel inGame, TileManager inTilesManager)
    {
        //initialize data
        game = inGame;
        tileManager = inTilesManager;

        //initialize size
        levels = new Level[LEVEL_COUNT];

        //input maps
        inputMaps();

    }

   /**********************************************************
     * Method Name   : inputMaps
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method instantiates 
     *  all levels and puts them into an array
     * 
     * BEGIN inputMaps
     *  tutorial level
     *  level one
     *  level two
     *  level three
     *  set default current level to tutorial
     * END input Maps
     **********************************************************/
    public void inputMaps()
    {
        //local constants

        //local variables

        /******start******/

        //tutorial level
        levels[TUTORIAL] = new Level(game, tileManager, TUTORIAL_FILE, TUTORIAL_OBJ_FILE, TUTORIAL_ENEMY_FILE);

        //level one
        levels[LEVEL_ONE] = new Level(game, tileManager, LEVEL_ONE_FILE, LEVEL_ONE_OBJ_FILE,LEVEL_ONE_ENEMY_FILE);

        //level two
        levels[LEVEL_TWO] = new Level(game, tileManager, LEVEL_TWO_FILE, LEVEL_TWO_OBJ_FILE,LEVEL_TWO_ENEMY_FILE);

        //level three
        levels[LEVEL_THREE] = new Level(game, tileManager, LEVEL_THREE_FILE, LEVEL_THREE_OBJ_FILE, LEVEL_THREE_ENEMY_FILE);

        //default current is tutorial
        curLevel = levels[TUTORIAL];

    }//END INPUTMAPS

    /**********************************************************
     * Method Name   : setLevel
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This sets the current level of the 
     *  game
     * 
     * BEGIN setLevel
     *  initialize new level
     *  get current level
     *  initialize enemies
     *  reset game
     * END setLevel
     **********************************************************/
    public void setLevel(int inLevel)
    {
        //local constants

        //local variables

        /******start*******/

        //initialize new level
        level = inLevel;
        
        //get current level
        curLevel = levels[level];

        //reset enemy
        game.getEnemyManager().initEnemies();
        game.reset();

    }

    /**********************************************************
     * Method Name   : draw
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method draws the current levels 
     * to the screen
     * 
     * BEGIN draw
     *  draw current level
     *  IF(current level is tutorial)
     *      TRY
     *          initialize image
     *          draw controls
     *      CATCH(IOEXCEPTION)
     *          display image
     *      END TRYCATCH
     *  END IF
     **********************************************************/

    public void draw(Graphics2D graphics2d)
    {
        //local constants

        //local variables 

        /******start*******/

        //draw current level
        curLevel.draw(graphics2d);

        //IF(current level is tutorial)
        if(curLevel == levels[TUTORIAL])
        {
            //TRY
            try
            {
                //initialize image
                controls = ImageIO.read(this.getClass().getResourceAsStream(CONTROLS));

                //draw controls and bug notices
                graphics2d.drawImage(controls, null, 50, 50);
            }
            //CATCH(IO exception)
            catch(IOException e)
            {
                e.printStackTrace();

            }//END TRY CATCH

        }//END IF

    }//END draw

    /**********************************************************
     * Method Name   : getCurLevel
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the current level
     * 
     * BEGIN getCurLevel
     *  return current level
     * END getCurLevel
     **********************************************************/
    public Level getCurLevel()
    {
        //local constants

        //local variables

        /***Start****/

        //return level
        return curLevel;
    }


}//END LevelManager