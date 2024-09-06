

/**********************************************************
 * Program Name   : Spikes 
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class holds the attributes for the
 *  spike objects in which kill the player if the player touches them
 **********************************************************/
import java.awt.Rectangle;

public class Spikes extends GameObject
{

    //class constants
    final int PIXELS_Y = 2;
    final int PIXELS_X = 2;
    final int SPIKE_HIT = 30;

    //class variables

    public Spikes(int imX, int imY, GamePanel inGame) 
    {
        //local constants

        //local varibles

        /*****start******/

        //initialize parent class contructor
        super(imX, imY , 0, inGame);

        //use image width later
        initHitBox();

        xDrawOffset = PIXELS_X * game.SCALE;
        yDrawOffset = PIXELS_Y * game.SCALE;
        
    }//END CONSTRUCTOR

    protected void initHitBox() 
    {
        //local constants
        final int OFFSET_X = 6;
        final int OFFSET_Y = 15;

        final int SPIKE_WIDTH = 25; 
        final int SPIKE_HEIGHT = 10;

        //local varibales

        /**start**/

        //initialize hitbox
        hitBox = new Rectangle((x+ OFFSET_X), (y + OFFSET_Y), SPIKE_WIDTH, SPIKE_HEIGHT);
    }

}