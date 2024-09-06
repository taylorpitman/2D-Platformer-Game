

/**********************************************************
 * Program Name   : GameObject
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class gives each tile an image
 *  and holds them for the maps.
 **********************************************************/
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class TileManager 
{
    //class constants
    BufferedImage backgroundFile;

    //class variables
    private Tile[] tiles;
    private GamePanel panel;
    public int[][] tileIndex;

    public TileManager(GamePanel inPanel)
    {
        //local constants
        final int TILE_COUNT = 5;

        //local variables

        /********start*********/

        //initialize panel
        panel = inPanel;   
         
        //instantiate array of tiles
        tiles = new Tile[TILE_COUNT];

        tileIndex = new int[panel.MAX_COLUMNS][panel.MAX_ROWS];

        //fill array
        getImages();
        
    }//END CONSTRUCTOR

    public void getImages()
    {
        //local constants 

        //local variables 

        //TRY
        try 
        {
            //initialize array with tile images
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tile0.png")));
            tiles[0].setIndex(0);

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tile1.png")));
            tiles[1].setCollision(true);
            tiles[1].setIndex(1);

            tiles[2] = new Tile();
            tiles[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tile2.png")));
            tiles[2].setCollision(true);
            tiles[2].setIndex(2);

            tiles[3] = new Tile();
            tiles[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tile3.png")));
            tiles[3].setCollision(true);
            tiles[3].setIndex(3);

            tiles[4] = new Tile();
            tiles[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tile4.png")));
            tiles[4].setCollision(true);
            tiles[4].setIndex(4);
            
        }//CATCH
        catch(IOException exception)
        {
            //display message
            System.out.println("TILES NOT FOUND");

        }//END TRY-CATCH
    }

    public Tile[] getTiles()
    {
        //local constants

        //loca variables

        /***Start****/

        //return tiles
        return tiles;
    }

}//END class


