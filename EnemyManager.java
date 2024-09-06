
/**********************************************************
 * Program Name   : EnemyManager
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class manages all the enemy entities
 * by updating them, drawing them and checking for collisions.
 *
 * METHODS
 * ---- 
 * EnemyManager         - constructor
 * update               - updates enemy if theyre active
 * initEnemies          - instantiates array list of enemies
 * draw                 - draws all enemies to panel
 * drawEnemies          - goes through list and draws enemies
 * checkObjectCollision - checks if enemy hits player
 * reset                - resets enemy back to original position
 **********************************************************/
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyManager 
{
    
    //class constants
    final int COIN_IDLE_INDEX       = 0;
    final int WEAPON_INDEX          = 0;
    final int SHIELD_INDEX          = 1;
    public final int COIN_TYPE      = 0;
    public final int DOOR_TYPE      = 1;
    public final int WEAPON_TYPE    = 2;
    public final int SHIELD_TYPE    = 3;
    public final int SPIKES_TYPE    = 4;
    public final int LIVES_TYPE     = 5;

    //class variables
    GamePanel game;
    ImageIcon spikesImage;
    ImageIcon doorImage;
    ArrayList<Enemy> enemies;
    Enemy curEnemy;

    /**********************************************************
     * Method Name   : EnemyManager
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN EnemyManager
     *  initialize variables
     *  initialize enemies
     * END EnemyManager
     **********************************************************/
    public EnemyManager(GamePanel imGame)
    {
        //local constants

        //local variables

        /******start*******/

        //initialize variables
        game = imGame;

        //initialize objects
        initEnemies();

    }//END constructor


    /**********************************************************
     * Method Name   : update
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method updates the enemies'
     *  position and or active status if needed
     *
     * BEGIN update
     *  FOR(the amount of enemies)
     *      get current enemy
     *      IF(current enemy is active)
     *          update enemy
     *      END IF
     *  END FOR
     *  initialize enemies
     * END update
     **********************************************************/
    public void update()
    {
        //local constants

        //local variables

        /****start****/

        //FOR(the amount of enemy)
        for(int i = 0; i < enemies.size(); i++)
        {
            //get current enemy
            curEnemy = enemies.get(i);
    
            //IF(current enemy is active)
            if(curEnemy.isActive())
            {
                //update enemy
                curEnemy.update();
                
            }//END IF

        }//END FOR

        //initialize enemies
        initEnemies();

    }//END method

    /**********************************************************
     * Method Name   : initEnemies
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method instantiates the 
     *  array list of enemies
     *
     * BEGIN initEnemies
     *  instantiate enemy list
     * END initEnemies
     **********************************************************/
    public void initEnemies()
    {
        //local constants

        //local variables

        /***start***/
        
        //instaintiate array list
        enemies = game.getLevelManager().getCurLevel().getEnemies();
        
    }//END METHODS

    /**********************************************************
     * Method Name   : draw
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method draws the enemies to the screen
     *
     * BEGIN draw
     *  call draw enemies method
     * END draw
     **********************************************************/
    public void draw(Graphics2D g)
    {
        //local constants

        //local varibales

        /*****start******/

        //draw Enemies
        drawEnemies(g);

    }//END draw

    /**********************************************************
     * Method Name   : drawEnemies
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method draws the enemies to the screen
     *
     * BEGIN drawEnemies
     *  FOR(the amount of enemies)
     *      get current enemy
     *      IF(enemy is active)
     *          draw enemy
     *      END IF
     *  END FOR
     * END drawEnemies
     **********************************************************/
    private void drawEnemies(Graphics2D g) 
    {
        //local constants

        //local variables

        /****start*****/

        //FOR(the amount of enemies)
        for(int i = 0; i < enemies.size(); i++)
        {
            //get current enemy
            curEnemy = enemies.get(i);

            //IF(enemy is active)
            if(curEnemy.isActive())
            {
                //draw enemy
                g.drawImage(curEnemy.getImage().getImage(), 
                (int)curEnemy.getEntityX(),
                (int)curEnemy.getEntityY(),
                curEnemy.getImage().getIconWidth()  * game.SCALE,
                curEnemy.getImage().getIconHeight() * game.SCALE,
                null);

            }//END IF

        }//END FOR

    }//END drawEnemies

    /**********************************************************
     * Method Name   : checkObjectCollision
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method checks
     *  to see if the player has collided with an enemy
     *
     * BEGIN checkObjectCollision
     *  FOR(the amount of enemies)
     *      get current enemy
     *      IF(enemy is active)
     *          IF(player collides with enemy)
     *              player looses life
     *          END IF
     *      END IF
     *  END FOR
     * END checkObjectCollision
     **********************************************************/
    public void checkObjectCollision(Rectangle hitBox)
    {
        //local constants

        //local variables

        /********start********/

        //FOR(the amount of enemies)
        for(int i = 0; i < enemies.size(); i++)
        {
            //get current enemy
            curEnemy = enemies.get(i);

            //IF(current enemy is active)
            if(curEnemy.isActive())
            {
                //IF(player collides with enemy)
                if(hitBox.intersects(curEnemy.getHitBox()))
                {
                    //have player lose life
                    game.getPlayer().loseLife();

                }//END IF

            }//END IF

        }//END FOR

    }//END check collision

    /**********************************************************
     * Method Name   : reset
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method resets all the enemies 
     *  to their initial positions
     *
     * BEGIN reset
     *  FOR(the amount of enemies)
     *      get current enemy
     *      reset enemy
     *  END FOR
     * END reset
     **********************************************************/
    public void reset()
    {
        //local constants

        //local variables

        /*****start*****/

        //FOR(the amount of enemies)
        for(int i = 0; i < enemies.size(); i++)
        {
            //get current enemy
            curEnemy = enemies.get(i);

            //reset enemy
            curEnemy.reset();

        }//END for

    }//END EnemyManager

    /**********************************************************
     * Method Name    : getEnemies
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the list
     *  of enemies
     *
     * BEGIN getEnemies
     *  return enemy list
     * END getEnemies
     **********************************************************/
    public ArrayList<Enemy> getEnemies()
    {
        //local constants

        //local variables

        /***Start***/

        //return enemy list
        return enemies;
    }
}