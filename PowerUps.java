
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PowerUps extends GameObject
{
    //class constants

    //class variables
    BufferedImage powerUp;

    public PowerUps(int imX, int imY, int imObjType, GamePanel inGame) 
    {
        //local constants

        //local variables

        /******start******/

        //invoke parent constructor
        super(imX, imY, imObjType, inGame);

        //call load images method
        loadImage();

        //create hitboxes
        createHitBox();

    }

    public void loadImage()
    {
        //local constants
        final int ZERO = 0;

        //local variables

        /******start*******/
        try
        {
            //IF(power up is a shield)
            if(objType == SHIELD_TYPE)
            {
                //initialize shield
                powerUp = ImageIO.read(getClass().getResourceAsStream("/objectSprites/Shield shine.gif"));

            }//else its a weapon
            else

                //initialize weapon
                powerUp = ImageIO.read(getClass().getResourceAsStream("/objectSprites/Sword shine.gif"));

            //END IF

            //offsets are 0 pixels
            xDrawOffset = ZERO;
            yDrawOffset = ZERO;

        }//CATCH(IO exception)
        catch(IOException exception)
        {
            //print stack trace
            exception.printStackTrace();

        }//END TRY CATCH

    }//END load image

    public void createHitBox()
    {
        //local constants

        //local variables

        /****start****/
        
        //call initHitBox method
        initHitBox(powerUp.getWidth() * game.SCALE, powerUp.getHeight() * game.SCALE);
    }
    
}
