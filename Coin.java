
/**********************************************************
 * Program Name   : Coin
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class holds the details
 *  of a coin object that the user can pick up.
 * 
 * METHOD
 * Coin - constructor
 **********************************************************/
public class Coin extends GameObject
{
    //class constants
    final int PIXELS_Y = 2; //empty space above gif
    final int PIXELS_X = 2; //empty space to the left of gif
    final int HIT_DIMENSIONS = 10;

    //class variables
    

    /**********************************************************
     * Method Name   : Coin
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN Coin
     *  initialize parent class
     *  initialize hitbox
     *  initialize offsets
     * END Coin
     **********************************************************/

    public Coin(int imX, int imY, GamePanel inGame) 
    {
        //local constants

        //local variables

        /*****start******/

        //initialize parent class contructor
        super(imX, imY , 0, inGame);

        //initialize hit box
        initHitBox(HIT_DIMENSIONS , HIT_DIMENSIONS);

        //initialize offset
        xDrawOffset = PIXELS_X * game.SCALE;
        yDrawOffset = PIXELS_Y * game.SCALE;
        
    }//END constructor
    
}
