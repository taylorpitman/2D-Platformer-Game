
/**********************************************************
 * Program Name   : Tile
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class holds all of the tiles
 * and their immages while also holding if theyve been 
 * collided with or not
 **********************************************************/
import java.awt.image.BufferedImage;

public class Tile
{
    private BufferedImage image;
    private boolean collision;
    private int index;

    public Tile ()
    {
        //local constants

        //loca variables

        /***Start****/

        //collision is false
        collision = false;
    }

    public void setIndex(int inIndex)
    {
        //local constants

        //loca variables

        /***Start****/

        //set new index
        index = inIndex;
    }

    public int getIndex()
    {
        //local constants

        //loca variables

        /***Start****/

        //return index
        return index;
    }

    public void setImage(BufferedImage img)
    {
        //local constant

        //local variables

        //initialize image
        image = img;

    }

    public void setCollision(boolean status)
    {
        //local constants

        //loca variables

        /***Start****/

        //set collision
        collision = status;
    }

    public boolean getCollision()
    {
        //local constants

        //loca variables

        /***Start****/

        //return collision
        return collision;
    }

    public BufferedImage getImage()
    {
        //local constants

        //loca variables

        /***Start****/

        //return image
        return image;
    }
    
}
