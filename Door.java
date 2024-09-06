/**********************************************************
 * Program Name   : Door
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class holds the details
 *  of a door object that the user can go to, to complete
 * the level
 *
 **********************************************************/
public class Door extends GameObject
{
    //class constants
    final int PIXELS_X = 2;
    final int PIXELS_Y = 2;

    //class variables
    
    /**********************************************************
     * Method Name   : Door
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN Door
     *  initialize parent class
     *  initialize hitbox
     *  initialize offsets
     * END Door
     **********************************************************/


    public Door(int imX, int imY, GamePanel inGame) 
    {
        //local constants

        //local varibles

        /*****start******/

        //initialize parent class contructor
        super(imX, imY , 0, inGame);

        //initialize hit box
        initHitBox((25 * game.SCALE) , (30 * game.SCALE));

        //initialize offset
        xDrawOffset = PIXELS_X * game.SCALE;
        yDrawOffset = PIXELS_Y * game.SCALE;

    }//END constructor
    
}
