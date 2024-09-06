/**********************************************************
 * Program Name   : KeysHandler
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class lets the game panel know
 *  what keyboard button control is being pressed and at what time
 *  It also removes buttones if the game state needs to be changed
 * 
 * METHODS
 * ----
 * KeysHandler  - constructor method
 * keyPressed   - determines which control is being initiated
 * keyReleased  - determines which control is being released
 * getJump      -  returns jump boolean
 * getGoLeft    - returns go left boolean
 * setAll       - sets all booleans to false
 * getGoRight   - returns go right boolean
 **********************************************************/
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeysHandler implements KeyListener
{
    // --------------------------------------------------------------
    // Responds to the user pressing arrow keys by adjusting the
    // image and image location accordingly.
    // --------------------------------------------------------------
    
    //class constants

    //class variables
    private boolean jump, goLeft, goRight;
    private boolean goDown;
    GamePanel game;

    /**********************************************************
     * Program Name   : KeysHandler
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: constructor method
     *
     * Pseudocode:
     * -------
     * BEGIN KeysHandler
     *  initialize game object
     * END KeysHandler
     **********************************************************/
    public KeysHandler(GamePanel inGame)
    {  
        //local constants

        //local variables

        /*********start*********/

        //initialize game object
        game = inGame;
    }

    /**********************************************************
     * Program Name   : keyPressed
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method determines 
     *  what control is being pressed
     *
     * Pseudocode:
     * -------
     * BEGIN keyPressed
     *  IF(game state is play)
     *      IF(input is space)
     *          update jump
     *          play sound effect
     *      ELSE IF(input is move left)
     *          update goleft
     *      ELSE IF(input is move right)    
     *          update goRight
     *      ELSE IF(input is pause)
     *          update game state
     *          removeUI
     *      END IF
     *  END IF
     * END keyPressed
     **********************************************************/
    @Override
    public void keyPressed(KeyEvent event) 
    {
        //local constants

        //local variables

        /************start***********/

        //IF(game state is play)
        if(game.getCurGameState() == game.PLAY)
        {
            //IF(input is space)
            if(event.getKeyCode() == KeyEvent.VK_SPACE)
            {
                //update jump
                jump = true;
                
                //play sound effect
                game.playSoundEffect(game.audio.JUMP_EFFECT);

            }
            //ELSE IF(character input is move left)
            else if(event.getKeyCode() == KeyEvent.VK_A)
            {
                //update go left
                goLeft = true;

            }
            //ELSE IF(character input is move right)
            else if(event.getKeyCode() == KeyEvent.VK_D)
            {
                //update go right
                goRight = true;

            }//ELSE IF(character input is pause)
            else if(event.getKeyCode() == KeyEvent.VK_ESCAPE)
            {   
                //change game state to pause
                game.setCurGameState(game.PAUSE);

                //remove UI
                game.removeUIButtons();

            }//END IF

        }//END IF

    }//END keyPressed

    /**********************************************************
     * Program Name   : keyReleased
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method determines 
     *  what control is being released
     *
     * Pseudocode:
     * -------
     * BEGIN keyReleased
     *  IF(game state is play)
     *      IF(input is space)
     *          update jump
     *      ELSE IF(input is move left)
     *          update goleft
     *      ELSE IF(input is move right)    
     *          update goRight
     *      END IF
     *  END IF
     * END keyPressed
     **********************************************************/
    @Override
    public void keyReleased(KeyEvent event) 
    {
        //IF(game state is play)
        if(game.getCurGameState() == game.PLAY)
        {
            //IF(key released is jump)
            if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_SPACE)
            {
                //update jump
                jump = false;
            }
            //ELSE IF(key released is left)
            else if(event.getKeyCode() == KeyEvent.VK_KP_LEFT || event.getKeyCode() == KeyEvent.VK_A)
            {
                //update go left
                goLeft = false;
            }
            //ELSE IF(key release is right)
            else if(event.getKeyCode() == KeyEvent.VK_KP_RIGHT || event.getKeyCode() == KeyEvent.VK_D)
            {
                //update go right
                goRight = false;

            }//END IF

        }//END IF

    }//END keyReleased

    @Override
    public void keyTyped(KeyEvent event) 
    {}

    /**********************************************************
     * Program Name   : getJump
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns jump boolean
     *
     * Pseudocode:
     * -------
     * BEGIN getJump
     *  return jump
     * END getJump
     **********************************************************/
    public boolean getJump()
    {
        //local constants

        //local variables

        /*********start*********/

        //return jump
        return jump;
    }

    /**********************************************************
     * Program Name   : getGoLeft
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns go left boolean
     *
     * Pseudocode:
     * -------
     * BEGIN getGoLeft
     *  return GoLeft
     * END getGoLeft
     **********************************************************/
    public boolean getGoLeft()
    {
        //local constants

        //local variables

        /*********start*********/

        //return go left
        return goLeft;
    }

    /**********************************************************
     * Program Name   : setAll
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method sets all 
     *  direction to the given boolean
     *
     * Pseudocode:
     * -------
     * BEGIN setAll
     *  set all booleans to new value
     * END setAll
     **********************************************************/
    public void setAll(boolean newBoolean)
    {
        //local constants

        //local variables

        /*********start*********/

        //set all booleans to false
        goLeft = newBoolean;
        goRight = newBoolean;
        jump = newBoolean;
        
    }
    /**********************************************************
     * Program Name   : getGoRight
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns go right boolean
     *
     * Pseudocode:
     * -------
     * BEGIN getGoRight
     *  return GoRight
     * END getGoRight
     **********************************************************/
    public boolean getGoRight()
    {
        //local constants

        //local variables

        /*********start*********/

        //return go right
        return goRight;
    }

 }



    

