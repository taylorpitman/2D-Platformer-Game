/**********************************************************
 * Program Name   : ClickHandler
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class creates a fireball object
 *  in a specific time frame when the user clicks their mouse
 *
 * METHODS
 * ---
 * ClickHandler - constructor
 * mouseClicked - preforms action if conditions are met
 * resetCount   - resets click count
 **********************************************************/

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickHandler implements MouseListener
{
    //class constants

    //class variables
    GamePanel game;
    private boolean attack;
    private int count;
    /**********************************************************
     * Method Name   : ClickHandler
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This initializes given data
     *
     * BEGIN ClickHandler
     *  initializ data
     * END ClickHandler
     **********************************************************/
    public ClickHandler(GamePanel inGame)
    {
        //local constants

        //local variables

        /**start***/

        //initialize data
        game = inGame;
        count = 0;
        attack = false;
    }

    /**********************************************************
     * Method Name   : mouseClicked
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method preformes an action
     *  if the mouse is clicked
     *
     * BEGIN mouseClicked
     *  IF(game state is play and player attack is on and count is 0)
     *      start timer
     *      increase count
     *  END IF
     *  IF(playerattack is on)
     *      instantiate fireball
     *      play sound effect
     *  END IF
     * END mouseClicked
     **********************************************************/
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        //local constants

        //local variables

        /*********start******/

        //IF(game state is play and attack is on and count is 0)
        if(game.getCurGameState() == game.PLAY && game.getPlayer().getAttackOn() && count == 0)
        {

            //start timer
            game.getPlayer().startTimer();
            
            //increase count
            count++;

        }//END IF

        //IF(attack is on)
        if(game.getPlayer().getAttackOn())
        {
            //instantiate fireball
            game.getPlayer().setFireBall(new FireBall(game.getPlayer()));

            //play sound effect
            game.playSoundEffect(game.audio.FIRE_BALL);

        }//END IF

    }//END mouseClicked

     /**********************************************************
     * Method Name   : resetCount
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method resets click count
     *
     * BEGIN resetCount
     *  initialize count to 0
     * END resetCount
     **********************************************************/
    public void resetCount()
    {
        //local constants

        //local variables 

        /***Start***/

        //initialize count to 0
        count = 0;

    }//END method

    
    @Override
    public void mousePressed(MouseEvent e) 
    {

    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
            

    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {

    }

    @Override
    public void mouseExited(MouseEvent e) 
    {

    }
    
}
