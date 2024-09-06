/**********************************************************
 * Program Name   : FireBall
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class contains the attributes
 *  of a fireball in which when it comes into contact with an enemy
 *  the enemy dies
 *
 * METHODS
 * -----
 * FireBall             - constructor method
 * initHitBox           - instantitaes fireball hitbox
 * updateHitBox         - update hitbox position
 * loadFireBall         - load fireball image
 * findCoordinates      - find starting coordinates of fire ball
 * shoot                - update x position
 * update               - updates fireball if its active
 * checkEnemyCollision  - checks if fireball has come in contact with an enemy
 * draw                 - draw fire ball to screen
 **********************************************************/

import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon; 
import java.util.ArrayList;

public class FireBall 
{
    //class constants

    //class variables
    ImageIcon fireBall;
    Rectangle hitBox;
    private int x;
    private int y;
    Player player;
    private boolean active;
    private int ballSpeed;

     /**********************************************************
     * Method Name   : FireBall
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN FireBall
     *  initialize data
     *  load fireball
     *  find starting coordinates
     *  initialize hitbox
     * END FireBall
     **********************************************************/
    public FireBall(Player imPlayer)
    {
        //local constants

        //local variables

        /*********start*********/

        //initialize data
        player = imPlayer;
        active = true;

        //load fireball
        loadFireBall();

        //find starting coordinates
        findCoordinates();

        //initialize hitbox
        initHitBox();

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
    public void initHitBox()
    {
        //local constants

        //local variables

        /*********start*********/

        //initialize hitbox
        hitBox = new Rectangle(x, y, fireBall.getIconWidth() * player.game.SCALE, fireBall.getIconHeight() * player.game.SCALE);

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
    public void updateHitBox()
    {
        //local constants

        //local variables

        /*********start*********/

        //initialize hitbox x and y
        hitBox.x = x;
        hitBox.y = y;
    }
    
    /**********************************************************
     * Method Name   : loadFireBall
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method loads the fireBall image
     *
     * BEGIN loadFireBall
     *  loadImage
     * END loadFireBall
     **********************************************************/
    public void loadFireBall()
    {
        //local constants

        //local variables

        /********start********/
        
        //load image
        fireBall = new ImageIcon(this.getClass().getResource("fireball.gif"));
    }

    /**********************************************************
     * Method Name   : findCoordinates
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method finds the starting coordinates
     *  of the fireball
     *
     * BEGIN findCoordinates
     *  IF(player direction is left)
     *      initialize fireball x
     *      initialize speed direction
     *  ELSE
     *      initialize fireball x
     *      initiialize speed direction
     *  END IF
     *  initialize y
     * END findCoordinates
     **********************************************************/
    public void findCoordinates()
    {
        //local constants

        //local variables

        /******Start*******/
        
        //IF(player direction is left)
        if(player.getDirection().equals(player.LEFT) || player.getDirection().equals(player.IDLE_LEFT))
        {
            //initialize x
            x = (int) player.x;   
            
            //ball speed is negative
            ballSpeed = -2;
        }
        //ELSE
        else
        {
            //initialize x coord
            x = (int) player.x + 2 * player.image.getIconWidth();

            //ball speed is positive
            ballSpeed = 2;

        }//END IF

        //initialize y
        y = (int) player.y + player.image.getIconHeight()/2;
    }

    /**********************************************************
     * Method Name   : shoot
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method updates the x position
     *  of the fire ball
     *
     * BEGIN shoot
     *  update x position
     * END shoot
     **********************************************************/
    public void shoot()
    {
        //local constants

        //local variables

        /*********start*********/
        
        //update x position
        x += ballSpeed;
    }

    /**********************************************************
     * Method Name   : update
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method updates the fireball
     *
     * BEGIN update
     *  IF(fireball is active)
     *      update x position
     *      update hitbox
     *      check collision
     *  END IF
     * END update
     **********************************************************/
    public void update()
    {
        //local constants

        //local variables

        /*********start*********/

        //IF(ball is active)
        if(active)
        {
            //update x position
            shoot();

            //update hit box
            updateHitBox();

            //check if collision
            checkEnemyCollision();
            
        }//END IF
    }

    /**********************************************************
     * Method Name   : checkEnemyCollision
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method checks if the
     *  fireball comes in contact with an enemy
     *
     * BEGIN checkEnemyCollision
     *  get list of enemies
     *  FOR(aount of enemies)
     *      get current enemy
     *      IF(enemy is active)
     *          IF(fireball collides with enemy)
     *              set enemy active to false
     *              set fireball active to false
     *          END IF
     *      END IF
     *  END FOR
     * END checkEnemyCollision
     **********************************************************/
    public void checkEnemyCollision()
    {
        //local constants

        //local variables
        ArrayList<Enemy> enemyList;
        Enemy enemy;

        /********start********/

        //get list of enemies
        enemyList = player.game.getEnemyManager().getEnemies();

        //FOR(the amount of enemies)
        for(int i = 0; i < enemyList.size(); i++)
        {
            //get current enemy
            enemy = enemyList.get(i);

            //IF(current enemy is active)
            if(enemy.isActive())
            {
                //IF(fireball collides with enemy)
                if(hitBox.intersects(enemy.getHitBox()))
                {
                    //set active to false
                    enemy.setActive(false);

                    //set active to false
                    active = false;
                    
                }//END IF

            }//END IF

        }//END FOR
    }

    /**********************************************************
     * Method Name   : draw
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method draws the fire ball
     *  to the screen
     *
     * BEGIN draw
     *  IF(fire ball is active)
     *      draw image
     *  END IF
     * END draw
     **********************************************************/
    public void draw(Graphics2D graphics)
    {
        //local constants

        //local variables

        /*********start*********/ 

        //(fireball is active)
        if(active)
        {
            //draw image
            graphics.drawImage(fireBall.getImage(), x, y, fireBall.getIconWidth() * player.game.SCALE, fireBall.getIconHeight() * player.game.SCALE, null);
            
        }//END IF
    }
}
