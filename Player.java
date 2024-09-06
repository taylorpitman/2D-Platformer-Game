/**********************************************************
 * Program Name   : Player
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class holds all the abtributes
 *  of the main character of the game. this class moves them 
 *  where promted when valid, keeps track of their lives and
 *  their power ups
 **********************************************************/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Player extends Entity implements ActionListener
{

    // class constants

    public final String IDLE_LEFT   = "idleLeft";
    final String IDLE_RIGHT         = "idleRight";
    final String JUMP               = "jump";
    final String LEFT = "left";
    final String RIGHT = "right";
    public final String ATTACK = "attack";
    final String DEATH = "death";

    final int MAX_LIVES = 3;

    final int PLAYER_HIT_X = 33;
    final int PLAYER_HIT_Y = 2;
    final int PLAYER_HIT_WIDTH = 10;
    final int PLAYER_HIT_HEIGHT = 15;

    // class variables
    GamePanel game;
    KeysHandler keyHandler;
    ClickHandler clickHandler;
    private int lives;
    double hitBoxX;
    double hitBoxY;
    ImageIcon image = null;
    private boolean attackOn;
    boolean lifeLost;
    private Timer timer;
    float timeCount;
    private FireBall fireBall;

    /**********************************************************
     * Method Name   : Player
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method is the constructor for 
     * the player class and instantiates given data
     **********************************************************/
    public Player(GamePanel inGame, KeysHandler inKeyHandler, ClickHandler inClickHandler) 
    {
        //local constants

        //local variables
    
        /***start***/

        // call parent's constructor
        super(inGame);

        // instantiate data
        game = inGame;
        keyHandler = inKeyHandler;
        clickHandler = inClickHandler;
        lives = MAX_LIVES;

        // set default position and speed
        loadImages();
        setDefault();

    }

    /**********************************************************
     * Method Name   : setDefault
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method sets the defualt
     *  attributes of the player that they should have at the 
     * beginning of each level
     **********************************************************/
    public void setDefault() 
    {
        // local constants

        // local variables

        /******* start ********/

        // intialize position
        x = game.TILE_SIZE - 15;
        y = (game.SCREEN_HEIGHT - (game.TILE_SIZE * 4));

        // initialize speed
        speed = 3;
        timeCount = 0;

        //initialize attack
        attackOn = false;
        lifeLost = false;

        // create hitbox
        initHitBox((int) x + (idleRight.getIconWidth()), (int) y + PLAYER_HIT_Y, PLAYER_HIT_WIDTH * game.SCALE, PLAYER_HIT_HEIGHT * game.SCALE);

        // initialize direction
        direction = IDLE_RIGHT;

    }// END

     /**********************************************************
     * Method Name   : reset
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method resets the player
     *  attributes
     **********************************************************/
    public void reset()
    {
        //local constants

        //local variables

        /****start******/

        //set default direction
        direction = IDLE_RIGHT;

        //set defualt x position
        x = game.TILE_SIZE;
        y = (game.SCREEN_HEIGHT - (game.TILE_SIZE * 4));

        //update hitbox
        updateHitBox((int) x + (idleRight.getIconWidth()), (int) y + PLAYER_HIT_Y);
        attackOn = false;

        //IF(timer is on)
        if(timerOn())
        {
            //stop timer, reset count and reset UI
            timer.stop();
            timeCount = 0;
            game.clockUI.setText("");

        }//END IF

        //reset click handler
        clickHandler.resetCount();

    }

    /**********************************************************
     * Method Name   : loadImages
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method loads the gifs of each player
     *  animation gif
     **********************************************************/
    public void loadImages() 
    {
        // local constants

        // local variables

        /****** start *********/
            
            //convert to Image Icons
            idleRight = new ImageIcon(this.getClass().getResource("/player/MEOWTAR_THE_BLUE_Idle_Right.gif"));
            idleLeft = new  ImageIcon(this.getClass().getResource("/player/MEOWTAR_THE_BLUE_Idle_Left.gif"));
            jump = new ImageIcon(this.getClass().getResource("/player/MEOWTAR_THE_BLUE_Idle_Right.gif"));
            left = new ImageIcon (this.getClass().getResource("/player/MEOWTAR_THE_BLUE_Run_left.gif"));
            right = new ImageIcon(this.getClass().getResource("/player/MEOWTAR_THE_BLUE_Run_right.gif"));

    }// END getImage

    /**********************************************************
     * Method Name   : update
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method updates the player movement,
     * state, ect
     **********************************************************/
    public void update() 
    {
        // local constants

        // local variables

        /****** start ********/

        //IF(timer on)
        if(timerOn())
        {
            //update fireball
            fireBall.update();

        }//END IF

        //call update position method
        updatePos();

        //check if object has been touched
        game.objectManager.checkObjectCollision(hitBox);
        game.enemyManager.checkObjectCollision(hitBox);

    }// END update

   /**********************************************************
     * Method Name    : updatePos
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method updates the position of the
     *  player
     **********************************************************/
    public void updatePos()
    {
        //local constants

        //local variables
        int attemptX = 0;

        /****start****/

        // IF(the character is prompted to move)
        if (keyHandler.getJump() || keyHandler.getGoLeft() || keyHandler.getGoRight()) 
        {
            // IF(character input is jump)
            if (keyHandler.getJump()) 
            {

                //call jump method
                jump();

            } // END IF

            // IF(character input is move left)
            if (keyHandler.getGoLeft()) 
            {

                // update direction
                direction = LEFT;

                //update attempt x speed
                attemptX -= speed;

            } // END IF

            // IF(character input is move right)
            if (keyHandler.getGoRight()) 
            {
                // update direction
                direction = RIGHT;

                //update attempt x speed
                attemptX += speed;

            } // END IF

            //IF(not in air)
            if(!inAir)
            {
                //(IF player is not on the floor)
                if(!game.getCollisionDetection().onFloor(hitBox))
                {
                    //character is in air
                    inAir = true;

                }//END IF

            }//END IF

        } // ELSE standing still
        else 
        {
            //IF(direction was right || jump)
            if(direction.equals(RIGHT) || direction.equals(JUMP))
            {
                //direction is idle right
                direction = IDLE_RIGHT;
            }
            //ELSE
            else if(direction.equals(LEFT))
            {
                //direction is idle left
                direction = IDLE_LEFT;

            }//END IF

        } // END IF

        //IF(in the air)
        if(inAir)
        {
            //IF(there is no collision)
            if (game.getCollisionDetection().checkTile(this, hitBox.x, hitBox.y + airSpeed))
            {
                //adjust y
                y += airSpeed;

                //update update hitbox y
                updateHitBox((int)x + PLAYER_HIT_X, (int)y + PLAYER_HIT_Y);

                //update airspeed
                airSpeed += gravity;

                //update x position
                updateXPos(direction, attemptX);
            }
            //ELSE there is a collision
            else
            {
                //get y position
                hitBox.y = game.getCollisionDetection().getYPosUnderRoofOrAboveFloor(hitBox, airSpeed);

                //IF(air speed is over 0)
                if(airSpeed > 0)
                {
                    //not in air
                    resetInAir();
                }
                //ELSE
                else
                {
                    //air speed is falling speed after collision
                    airSpeed = fallSpeedAfterCollision;

                }//END IF

                //update x position
                updateXPos(direction, attemptX);

            }// END IF
        }
        //ELSE 
        else
        {
            //update x position
            updateXPos(direction, attemptX);

        }// END IF
    }

    /**********************************************************
     * Method Name   : jump
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method sets up the needed attributes 
     *  if the player is jumping
     **********************************************************/
    private void jump() 
    {
        //local constants

        //local variables

        /*********start********/

        //IF(not in ait)
        if(!inAir)
        {
            // update direction
            direction = JUMP;

            //character is not in air
            inAir = true;

            //air speed is jump speed
            airSpeed = jumpSpeed;

        }//END IF
    }

    /**********************************************************
     * Method Name   : resetInAiR
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method resets the inAir
     *  attribute once the user reaches the floor
     **********************************************************/
    private void resetInAir()
    {
        //local constants

        //local variables

        /*********start*********/

        //in air is false
        inAir = false;

        //air speed is 0
        airSpeed = 0;

    }

   /**********************************************************
     * Method Name   : updateXPos
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method updates the X position
     *  of the user
     **********************************************************/
    public void updateXPos(String direction, double xSpeed) 
    {
        //local constants

        //local variables
        
        /*******start*******/

        //IF(can go through tile)
        if (game.getCollisionDetection().checkTile(this, hitBox.x + xSpeed, y))
        {
            //update x
            x += xSpeed;

            //update hitbox
           updateHitBox((int) x + PLAYER_HIT_X, (int) y + PLAYER_HIT_Y);
        }
        //ELSE
        else
        {
            //get closet position next to wall
            hitBox.x = game.getCollisionDetection().getXPosNextToWall(hitBox, xSpeed);
            
        }//END IF

    }// END METHOD

   /**********************************************************
     * Method Name   : draw
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method draws all the needed 
     * player attributes to the screen
     **********************************************************/
    public void draw(Graphics2D graphic)
     {
        // local constant

        // local variables

        /******* start *********/

        //IF(timer is on)
        if(timerOn())
        {
            //draw fireball
            fireBall.draw(graphic);

        }//END IF

        // SWITCH(direction)
        switch (direction) 
        {
            // CASE(character is idle
            case IDLE_RIGHT: 
            {
                // initialize imageIcon
                image = idleRight;

                // break statement
                break;
            }
            // CASE(character is idle
            case IDLE_LEFT: 
            {
                // initialize imageIcon
                image = idleLeft;
            
                // break statement
                break;
            }
            // CASE(character is jumping)
            case JUMP: 
            {
                // initialize image icon
                image = jump;

                // break statement
                break;
            }
            // CASE(character is going left)
            case LEFT: 
            {
                // initialize image icon
                image = left;

                // break statement
                break;
            }
            // CASE(character is going right)
            case RIGHT: 
            {
                // initialize image icon
                image = right;

                // break statement
                break;
            }
        }// END SWITCH

        // draw scaled image
        graphic.drawImage(image.getImage(), (int)x, (int) y, image.getIconWidth() * game.SCALE, image.getIconHeight() * game.SCALE,
                null);

    }// END draw

    /**********************************************************
     * Method Name   : attack
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method turns attack availability
     *  on 
     **********************************************************/
    public void attack() 
    {
        //local constants

        //local variables

        /****start*****/

        //attack boolean true
        attackOn = true;

    }

   /**********************************************************
     * Method Name   : lostLife
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method decrements the players
     *  life and then determines if the game is over or not
     **********************************************************/
    public void loseLife() 
    {
        //local constants

        //local variables

        /****start*****/

        //decrement lives
        lives--;

        //IF(no more lives)
        if(lives == 0)
        {
            //play sound effect
            game.playSoundEffect(game.audio.GAME_OVER);

            //change game state
            game.setCurGameState(game.GAME_OVER);

            //reset lives
            resetLives();

            //remove UI
            game.removeUIButtons();

        }//ELSE
        else
        {
            //play other sound effect
            game.playSoundEffect(game.audio.LOSE_LIFE);

        }//END IF

        //reset game
        game.reset();

        //set all key inputs to false
        keyHandler.setAll(false);

    }//END loseLife

    /**********************************************************
     * Method Name   : resetLives
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method resets the lives back to 
     * 3
     **********************************************************/
    public void resetLives()
    {
        //local constants

        //local variables

        /*****Start*****/

        //reset lives
        lives = MAX_LIVES;
        
    }

    /**********************************************************
     * Method Name   : levelComplete
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method does the actions the game
     * needs before going to the game over screen
     **********************************************************/
    public void levelComplete() 
    {
        //local constants

        //local variables

        /****start*****/

        //play sound effect
        game.playSoundEffect(game.audio.LEVEL_DONE);

        //set all key inputs to false
        keyHandler.setAll(false);

        //reset level
        game.reset();

        //change gamestate
        game.setCurGameState(game.LEVEL_COMPLETE);

        //resetLives
        resetLives();

    }//END levelComplete

   /**********************************************************
     * Method Name   : start timer
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method starts the timer
     *  for firing fireballs
     **********************************************************/
    public void startTimer() 
    {
        //local constants

        //local variables
        
        /****start****/

        //instantiate timer
        timer = new Timer(500, this);

        //start timer
        timer.start();
    }

    /**********************************************************
     * Method Name   : actionPerformed
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method determines what happens
     *  everytime the time updates
     **********************************************************/
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //local constants

        //local variables

        /***start****/

        //increase time count
        timeCount++;

        //update UI
        game.updateTime((int)timeCount);
    }

   /**********************************************************
     * Method Name   : timerOn
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method determines if the timer is 
     *  actively on
     **********************************************************/
    public boolean timerOn()
    {
        //local constants
        final int TEN_SECONDS = 20;
        
        //local variables
        boolean isOn = true;

        /***Start***/

        //IF(time count is 0)
        if(timeCount == 0)
        {
            //timer isnt on
            isOn = false;

        }
        //ELSE IF(time count is above 10 seconds)
        else if(timeCount >=  TEN_SECONDS)
        {
            //stop timer
            timer.stop();
            timeCount = 0;

            //timer isnt on
            isOn = false;

        }//END IF

        //return if it is on
        return isOn;
        
    }

    public boolean getAttackOn()
    {
        //local constats

        //local varibales

        /****start****/

        //return attack on
        return attackOn;
    }

    public void setFireBall(FireBall newBall)
    {
        //local constants

        //local variables

        /***start****/

        //instantiate
        fireBall = newBall;
    }

    public int getLives()
    {
        //local constants

        //local variables

        /***Start***/

        return lives;
    }

}
