
/**********************************************************
 * Program Name   : Enemy
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class holds the details
 *  of an enemy entity that the user has to defeat before passing
 *
 * METHODS
 * ----
 * Enemy        - constructor
 * initHitBox   - instantitates hitbox size
 * update       - updates enemy
 * getImage     - returns enemy image
 * reset        - reset enemys position
 * setActive    - sets active
 * getHitBox    - returns hitbox
 * isActive     - returns is active boolean
 * getX         - returns x position
 * getY         - returns y position
 **********************************************************/

import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy extends Entity
{
    //class constants
    final int HIT_DIMENSIONS = 10;
    final int ENEMY_HIT_Y_OFFSET = 14;
    final int ENEMY_HIT_X_OFFSET = 3;
    final String ANIMATION_RIGHT = "/enemySprites/enemy_right.gif";
    final String ANIMATION_LEFT = "/enemySprites/enemy_left.gif";
    final int ENEMY_SPEED_LEFT = -1;
    final int ENEMY_SPEED_RIGHT = 1;
    final int HIT_OFFSET = 15;

    //class variables
    private boolean active;
    private double initialX;
    private ImageIcon enemy;
    private int enemySpeed;
    private int hitBoxRight;
    private int hitBoxLeft;

    /**********************************************************
     * Method Name   : Enemy
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN Enemy
     *  call parent constructor
     *  initialize x and y
     *  initialize enemy speed and image icon
     *  initializ hitbox
     *  initiailize active boolean to true
     * END Enemy
     **********************************************************/
    public Enemy(double imX, double imY, GamePanel inGame)
    {
        //local constants

        //local variables

        /***start***/

        //call parent constructor
        super(inGame);

        //initialize x and y
        x = imX;
        initialX = x;
        y = imY;

        //initialize enemy speed and picture
        enemySpeed = ENEMY_SPEED_RIGHT;
        enemy = new ImageIcon(this.getClass().getResource(ANIMATION_RIGHT));               
        
        //initialize hitbox
        initHitBox();        
        
        //set active to true
        active = true;

    }//END constructor

    /**********************************************************
     * Method Name   : initHitBox
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes 
     *  the enemy's hitbox
     *
     * BEGIN update
     *  instantiate rectangle
     *  inititalize right and left facing hitbox coordinates
     * END update
     **********************************************************/
    protected void initHitBox() 
    {
        //local constants

        //local variables

        /*****start*****/

        //instantiate rectangle
        hitBox = new Rectangle((int)(x + enemy.getIconWidth()) + ENEMY_HIT_X_OFFSET , (int)y + ENEMY_HIT_Y_OFFSET, enemy.getIconWidth()/2 * game.SCALE, ((enemy.getIconHeight()/2) + ENEMY_HIT_X_OFFSET) *game.SCALE);

        //set right and lect facing hitbox x coordinates
        hitBoxRight = hitBox.x;
        hitBoxLeft = hitBox.x - HIT_OFFSET;

    }//END initHitBox

    /**********************************************************
     * Method Name   : update
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method updates
     *  the enemy x coordinate depending where they are
     *
     * BEGIN update
     *  IF(enemy hits a wall or is not on the floor)
     *      movement is left
     *      change image
     *  ELSE IF(enemy is at starting point)
     *      movement is right
     *      change image
     *  END IF
     *  update x coordinate
     * END update
     **********************************************************/
    public void update()
    {
        //local constants

        //local variables

        /****start****/

        //IF(enemy hits a wall or is not on the floor)
        if(!game.getCollisionDetection().checkTile(this, hitBox.x + enemySpeed, y) || !(game.getCollisionDetection().enemyOnFloor(hitBox)))
        {
            //speed is to the left
            enemySpeed = ENEMY_SPEED_LEFT;

            //change image
            enemy = new ImageIcon(this.getClass().getResource(ANIMATION_LEFT));
            hitBox.x -= HIT_OFFSET;
        }
        //ELSE IF(enemy is at starting position)
        else if(x == initialX)
        {
            //speed is to the right
           enemySpeed = ENEMY_SPEED_RIGHT;

           //change image
           enemy = new ImageIcon(this.getClass().getResource(ANIMATION_RIGHT));
           hitBox.x = hitBoxRight;

        }//END IF

        //increase x coordinate
        updateHitBox(hitBox.x + enemySpeed, (int)hitBox.y); 
        x += enemySpeed;

    }//END METHOD

    /**********************************************************
     * Method Name   : getImage
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the current
     *  image icon of the enemy
     *
     * BEGIN getImage
     *  return image
     * END getImage
     **********************************************************/
    public ImageIcon getImage()
    {
        //local constants

        //local variables

        /*Start*/

        //return enemy picture
        return enemy;
    }

    /**********************************************************
     * Method Name   : reset
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method resets the enemy
     *  back to their original position
     *
     * BEGIN reset
     *  set active to true
     *  re initialize enemy speed
     *  reinitalize image and x position
     *  update hitbox
     * END reset
     **********************************************************/
    public void reset()
    {
        //local constants

        //local variables

        /***start***/

        //set active to true
        active = true; 

        //reinitialize enemy speed
        enemySpeed = ENEMY_SPEED_RIGHT;

        //reinitialize image and x position
        enemy = new ImageIcon(this.getClass().getResource(ANIMATION_RIGHT));
        x = initialX;

        //update hitbox
        updateHitBox((int)(x + enemy.getIconWidth()) + ENEMY_HIT_X_OFFSET , (int)y + ENEMY_HIT_Y_OFFSET);
    }

    /**********************************************************
     * Method Name   : setActive
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method changes the active value
     *
     * BEGIN setActive
     *  reinitialize active
     * END setActive
     **********************************************************/
    public void setActive (boolean newActive) 
    {
        //local constants

        //local variables

        /****start****/

        //reinitialize active
        active = newActive;
    
    }

    /**********************************************************
     * Method Name   : getHitBox
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the enemy
     *  hitbox
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
     * Method Name   : isActive
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns active
     *
     * BEGIN isActive
     *  return active
     * END isActive
     **********************************************************/
    public boolean isActive() 
    {
        //local constants

        //local variables

        /****start****/

        //return active boolean
        return active;
    }

   /**********************************************************
     * Method Name   : getX
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the enemy x poosition
     *
     * BEGIN getX
     *  return X coor
     * END getX
     **********************************************************/
    public double getX()
    {
        //local constants

        //local variables

        /****start****/

        //return x
        return x;
    }

/**********************************************************
     * Method Name   : getY
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method returns the enemy Y poosition
     *
     * BEGIN getY
     *  return Y coor
     * END getY
     **********************************************************/
    public double getY()
    {
        //local constants

        //local variables

        /****start****/

        //return y
        return y;
    }

}
