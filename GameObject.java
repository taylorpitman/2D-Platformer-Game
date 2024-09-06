

/**********************************************************
 * Program Name   : GameObject
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This is the parent class for all
 *  level objects and holds their common attributes
 * 
 * METHODS
 * ----
 * GameObject       - constructor method
 * initHitbox       - initializes game object hitbox
 * reset            - resets object to original attributes
 * getObjType       - returns object type
 * setActive        - changes activity of object
 * getHitBox        - returns object hitbox
 * isActive         - returns whether object is active
 * getxDrawOffset   - get offset
 * getX             - gets x poosition
 * getY             - gets y position
 * getyDrawOffset   - get offset
 **********************************************************/
import java.awt.*;

public class GameObject 
{
    //class constants
    public final int COIN_TYPE = 0;
    public final int DOOR_TYPE = 1;
    public final int WEAPON_TYPE = 2;
    public final int SHIELD_TYPE = 3;
    public final int SPIKES_TYPE = 4;
    public final int LIVES_TYPE = 5;
    public final int FIREBALL_TYPE = 6;

    //class variables
    protected int x,y, objType;
    protected Rectangle hitBox;
    protected boolean active, collided; 
    protected int xDrawOffset, yDrawOffset;
    GamePanel game;

    /**********************************************************
     * Method Name   : GameObject
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN GameObject
     *  initialize givend data
     *  initialize active to true
     * END GameObject
     **********************************************************/
    public GameObject(int imX, int imY, int imObjType, GamePanel inGame)
    {
        //local constants

        //local variables

        /*********start*********/

        //initialize given data
        x = imX;
        y = imY;
        objType = imObjType;
        game = inGame;

        //set active to true
        active = true;

    }//END constructor

    /**********************************************************
     * Method Name   : initHitBox
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method instantiates 
     *  a hit box for the game object
     *
     * BEGIN initHitBox
     *  instantiate hitbox
     * END initHitBox
     **********************************************************/
    protected void initHitBox(int boxWidth, int boxHeight) 
    {
        //local constants

        //local variables

        /*********start*********/

        //initialize hitbox
        hitBox = new Rectangle(x, y, boxWidth, boxHeight);
    }

    /**********************************************************
     * Method Name   : reset
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method resets each object
     *
     * BEGIN reset
     *  set active to true
     * END reset
     **********************************************************/
    public void reset()
    {
        //local constants

        //local variables

        /*********start*********/

        //reset active boolean
        active = true; 
    }

    /**********************************************************
     * Method Name   : getObjType
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the objects type
     *
     * BEGIN getObjType
     *  return object type
     * END getObjType
     **********************************************************/
    public int getObjType() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return object type
        return objType;
    }

    /**********************************************************
     * Method Name   : setActive
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method changes
     *  the activity of the object
     *
     * BEGIN setActive
     *  initialize active booleam
     * END setActive
     **********************************************************/
    public void setActive (boolean newActive) 
    {
        //local constants

        //local variables

        /*********start*********/

        //initialize active boolean
        active = newActive;
    }

    /**********************************************************
     * Method Name   : getHitBox
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the object
     *  hit box
     *
     * BEGIN getHitBox
     *  return hitbox
     * END getHitBox
     **********************************************************/
    public Rectangle getHitBox() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return hitbox
        return hitBox;
    }

    /**********************************************************
     * Method Name   : isActive
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns whether the 
     *  object is active
     *
     * BEGIN isActive
     *  return isActive
     * END isActive
     **********************************************************/
    public boolean isActive() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return active
        return active;
    }

    /**********************************************************
     * Method Name   : getxDrawOffset
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the x position  
     *  offset
     *
     * BEGIN getxDrawOffset
     *  return xDrawOffset
     * END getxDrawOffset
     **********************************************************/
    public int getxDrawOffset() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return offset
        return xDrawOffset;

    }

    /**********************************************************
     * Method Name   : getX
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the x position  
     *  of the object
     *
     * BEGIN getX
     *  return x
     * END getX
     **********************************************************/
    public int getX()
    {
        //local constants

        //local variables

        /*********start*********/

        //return x
        return x;
    }

    /**********************************************************
     * Method Name   : getY
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the y position  
     *  of the object
     *
     * BEGIN getY
     *  return y
     * END getY
     **********************************************************/
    public int getY()
    {
        //local constants

        //local variables

        /*********start*********/

        //return y
        return y;
    }

    /**********************************************************
     * Method Name   : getyDrawOffset
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the y position  
     *  offset
     *
     * BEGIN getyDrawOffset
     *  return yDrawOffset
     * END getyDrawOffset
     **********************************************************/
    public int getyDrawOffset() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return y offset
        return yDrawOffset;
    }

    
}
