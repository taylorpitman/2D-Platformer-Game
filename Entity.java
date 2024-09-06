
/**********************************************************
 * Program Name   : Entity
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This abstract class is the parent method
 *  to the player and enemies, holding their common
 *  attributes
 *
 * METHODS
 * ----
 * Entity       - constructor
 * initHitBox   - instantiates hit box of entity
 * updateHitBox - updates hitbox coordinates
 * getEntityX   - returns x position
 * getEntityY   - returns y position
 * getHitBox    - returns hitbox
 * getDirection - returns direction of entity
 **********************************************************/
import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Entity 
{

    // class constants
    final double INIT_GRAVITY = 0.15;

    // class variables
    protected double x, y, speed;
    protected ImageIcon idleRight, idleLeft, jump, fall, left, right, attack, death;
    protected String direction;
    protected Rectangle hitBox;

    //jumping/ gravity
    protected double gravity;
    protected double airSpeed;
    protected double jumpSpeed;
    protected double fallSpeedAfterCollision;
    protected boolean inAir;

    GamePanel game;

    /**********************************************************
     * Method Name   : Entity
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN Entity
     *  initialize game object
     *  initialize airspeed
     *  initialize jump speed   
     *  initialize fall speed after collision
     *  initialize gravity
     *  initialize entity in air boolean
     * END Entity
     **********************************************************/
    public Entity(GamePanel inGame) 
    {
        //local constants

        //local variables

        /*****start******/

        // initialize game object class
        game = inGame;

        // initialize airspeed
        airSpeed = 0;

        // initialize jump speed
        jumpSpeed = -2.25 * game.SCALE;

        // initialize fall speed after collision
        fallSpeedAfterCollision = .5 * game.SCALE;

        // initialize gravity
        gravity = INIT_GRAVITY * game.SCALE;

        // initialize entity collision and in air
        inAir = false;
    }

    /**********************************************************
     * Method Name   : initHitBox
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method instantiates 
     *  a hit box for the entity
     *
     * BEGIN initHitBox
     *  instantiate hitbox
     * END initHitBox
     **********************************************************/
    protected void initHitBox(int xPos, int yPos, int boxWidth, int boxHeight) 
    {
        //local constants

        //local variables

        /****start****/

        //initialize hitbox
        hitBox = new Rectangle(xPos, yPos, boxWidth, boxHeight);
    }

    /**********************************************************
     * Method Name   : updateHitBox
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method instantiates 
     *  updates the hitbox coordinates
     *
     * BEGIN updateHitBox
     *  update position
     * END updateHitBox
     **********************************************************/
    protected void updateHitBox(int newX, int newY) 
    {
        //local constants

        //local variables

        /****start****/

        //update positions
        hitBox.x = newX;
        hitBox.y = newY;
    }

    /**********************************************************
     * Method Name   : getEntityX
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the entities 
     *  x position
     *
     * BEGIN getEntityX
     *  return x position
     * END getEntityX
     **********************************************************/
    public double getEntityX() 
    {
        //local constants

        //local variables

        /****start****/

        //return x
        return x;
    }

    /**********************************************************
     * Method Name   : getEntityY
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the entities 
     *  Y position
     *
     * BEGIN getEntityY
     *  return Y position
     * END getEntityY
     **********************************************************/
    public double getEntityY() 
    {
        //local constants

        //local variables

        /****start****/

        //return y
        return y;
    }

    /**********************************************************
     * Method Name   : getHitBox
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the entities 
     *  hitBox
     *
     * BEGIN getHitBox
     *  return hitbox
     * END getHitBox
     **********************************************************/
    public Rectangle getHitBox() 
    {

        //local constants

        //local variables

        /****start****/

        //return hitbox
        return hitBox;
    }

    /**********************************************************
     * Method Name   : getDirection
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the entities 
     *  direction
     *
     * BEGIN getDirection
     *  return direction
     * END getDirection
     **********************************************************/
    public String getDirection() 
    {
        //local constants

        //local variables

        /****start****/

        //return direction
        return direction;
    }

}
