
/**********************************************************
 * Program Name   : Level
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class hols each individual level
 *  holding, loading and drawing each of their prospected files
 *  given through the constructor
 * 
 * METHODS
 * ----
 * Level            - constructor method
 * initLevel        - instantitates everything needed for a level
 * loadEnemies      - loads enemies for level
 * loadObjects      - loads objects for level
 * placeObjInArray  - plaves object in correct array
 * loadLevel        - loads tiles
 * draw             - draws tiles
 * drawTiles        - draws each individual tile
 * getCoins         - returns coin array list
 * getPowerUps      - returns power up array list
 * getSpikes        - returns spikes array list
 * getDoors         - returns door
 * getEnemies       - returns enemies array list
 * getMapIndex      - returns tile map index array
 **********************************************************/

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Level 
{
    //class constants
    final String LEVEL_BACKGROUND_FILE = "/tiles/background.png";
    public final int COIN_TYPE      = 0;
    public final int DOOR_TYPE      = 1;
    public final int WEAPON_TYPE    = 2;
    public final int SHIELD_TYPE    = 3;
    public final int SPIKES_TYPE    = 4;
    public final int LIVES_TYPE     = 5;

    //class variables   
    GamePanel game;
    BufferedImage background;
    TileManager tileManager;
    InputStream map;
    InputStream objects;
    InputStream enemiesInput;
    private String levelFile;
    private String objFile;
    private String enemyFile;
    private ArrayList<Coin> coins;
    private ArrayList<PowerUps> powerUps;
    private ArrayList<Spikes> spikes;
    private ArrayList<Door> doors;
    private ArrayList<Enemy> enemies;
    private int[][] mapIndex;

    /**********************************************************
     * Program Name   : Level
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: constructor method
     *
     * Pseudocode:
     * -------
     * BEGIN Level
     *  initialize game object
     * END Level
     **********************************************************/
    public Level(GamePanel imGame, TileManager imTileManager, String imLevelFile, String imObjFile, String imEnemyFile)
    {
        //local constants

        //local variables

        /****Start****/

        //initialize data
        tileManager = imTileManager;
        levelFile   = imLevelFile;
        enemyFile   = imEnemyFile;
        objFile     = imObjFile;
        game        = imGame;


        //initialize level
        initLevel();

        //load level
        loadLevel();
        
    }//END Constructor

    /**********************************************************
     * Program Name   : initLevel
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method instaintiates all
     *  needed lists and data for a level
     * 
     * Pseudocode:
     * -------
     * BEGIN initLevel
     *  initialize mapIndex
     *  initiailize array lists
     *  TRY
     *      initialize input streams
     *  CATCH(IO exception)
     *      display message
     *  END TRYCATCH
     * END initLevel
     **********************************************************/
    public void initLevel()
    {
        //local constants

        //local variables

        /******start******/

        //initialize mapindex
        mapIndex = new int[game.MAX_COLUMNS][game.MAX_ROWS];

        //initialize array lists
        coins = new ArrayList<>();
        powerUps = new ArrayList<>();
        spikes = new ArrayList<>();
        doors = new ArrayList<>();
        enemies = new ArrayList<>();

        //try
        try
        {  
            //initialize inputStreams
            map = this.getClass().getResourceAsStream(levelFile);
            background = ImageIO.read(this.getClass().getResourceAsStream(LEVEL_BACKGROUND_FILE));
            objects = this.getClass().getResourceAsStream(objFile);
            enemiesInput = this.getClass().getResourceAsStream(enemyFile);

        }//CATCH(IO exception)
        catch(IOException exception)
        {
            exception.printStackTrace();

        }//END TRY-CATCH

    }

    /**********************************************************
     * Program Name   : loadEnemies
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method loads enemies needed for level
     *  into an array
     * 
     * Pseudocode:
     * -------
     * BEGIN loadEnemies
     *  TRY
     *      instantitae buffered reader 
     *      read line
     *      WHILE(there are more lines)
     *          split line up
     *          get coordinates from tokens
     *          add enemy to list
     *          read next line
     *      END WHILE
     *      close reader
     *  CATCH(IO EXCEPTION)
     *      display message
     *  END TRYCATCH
     * END loadEnemies
     **********************************************************/
    public void loadEnemies()
    {
        //local constants
        final int ENEMY_X = 0;
        final int ENEMY_Y = 1;

        //local variables
        String line;
        String[] lineArray;
        int x;
        int y;
        BufferedReader reader;

        /*******start*******/

        //TRY
        try
        {
            //instantiate buffered reader
            reader = new BufferedReader(new InputStreamReader(enemiesInput));

            //read line
            line = reader.readLine();

            //WHILE(there are more lines)
            while(line != null)
            {
                //split line up
                lineArray = line.split(" ");

                //get coordinate from tokens
                x = Integer.parseInt(lineArray[ENEMY_X]);
                y = Integer.parseInt(lineArray[ENEMY_Y]);

                //add enemy to list
                enemies.add(new Enemy(x, y, game));

                //read next line
                line = reader.readLine();
            }

            //close reader
            reader.close();

        }//CATCH(io exception)
        catch(IOException e)
        {
            //display print stack trace
            e.printStackTrace();

        }//END TRY-CATCH

    }//END loadObjects


    /**********************************************************
     * Program Name   : loadObjects
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method loads enemies needed for level
     *  into an array
     * 
     * Pseudocode:
     * -------
     * BEGIN loadObjects
     *  TRY
     *      instantitae buffered reader 
     *      read line
     *      WHILE(there are more lines)
     *          split line up
     *          get object type from first token
     *          call place object in array
     *          read next line
     *      END WHILE
     *      close reader
     *  CATCH(IO EXCEPTION)
     *      display message
     *  END TRYCATCH
     * END loadObjects
     **********************************************************/
    public void loadObjects()
    {
        //local constants
        final int OBJ_ID_INDEX = 0;

        //local variables
        String line;
        String[] lineArray;
        int object;
        BufferedReader reader;

        /*****start*******/

        //TRY
        try
        {
            //instantiate buffered reader
            reader = new BufferedReader(new InputStreamReader(objects));

            //read line
            line = reader.readLine();

            //WHILE(there are more lines)
            while(line != null)
            {
                //split line up
                lineArray = line.split(" ");

                //get first token(parse to int)
                object = Integer.parseInt(lineArray[OBJ_ID_INDEX]);

                //call place object in array method
                placeObjInArray(object, lineArray);

                //read next line
                line = reader.readLine();
                
            }//END WHILE

            //close reader
            reader.close();

        }//CATCH(io exception)
        catch(IOException e)
        {
            //display print stack trace
            e.printStackTrace();

        }//END TRY-CATCH

    }//END loadObjects


    /**********************************************************
     * Program Name   : placeObjInArray
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method places the object into the 
     *  correct array
     * 
     * Pseudocode:
     * -------
     * BEGIN placeObjInArray
     *  get x coordinate from token
     *  get y coordinate from token
     *  IF(object is coin)
     *      add coin to array list
     *  ELSE IF(object is weapon)
     *      add to array list
     *  ELSE IF(object is spike)
     *      add to array list
     *  ELSE IF(object is door)
     *      add to array list
     *  END IF
     * END placeObjInArray
     **********************************************************/
    public void placeObjInArray(int object, String[] line)
    {
        //local constants
        final int X_INDEX = 1;
        final int Y_INDEX = 2;

        //local variables
        int x;
        int y;

        /*******start******/

        //get x coordinate
        x = Integer.parseInt(line[X_INDEX]);

        //get y coordinate
        y = Integer.parseInt(line[Y_INDEX]);

        //IF(object is coin)
        if(object == COIN_TYPE)
        {
            //add coin object to array list
            coins.add(new Coin(x, y, game));
        }
        //ELSE IF(object is weapon or shield)
        else if(object == WEAPON_TYPE || object == SHIELD_TYPE)
        {      
            //add coin object to array list
            powerUps.add(new PowerUps(x, y, object, game));
        }
        //ELSE IF(object is a spike)
        else if(object == SPIKES_TYPE)
        {
            //add spike to array list
            spikes.add(new Spikes(x,y, game));

        }
        //ELSE IF(object is door)
        else if(object == DOOR_TYPE)
        {
            //add door to array list
            doors.add(new Door(x,y,game));
            
        }//END IF
    }

    /**********************************************************
     * Program Name   : loadLevel
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method places the object into the 
     *  correct array
     * 
     * Pseudocode:
     * -------
     * BEGIN loadLevel
     *  TRY
     *      instantiate buffered reader
     *      FOR(the amount of rows)
     *          read line
     *          FOR(the amount of columns)
     *              split line
     *              parse token to get index
     *              place in 2d array 
     *          END FOR
     *      END FOR
     *      close reader
     *      load objects
     *      lod enemies
     *  CATCH(IOEXCEPTION)
     *      display message
     *  END TRYCATCH
     * END loadLevel
     **********************************************************/
    public void loadLevel()
    {
        //local constants

        //local variables
        String line;
        String[] lineArray;
        int index;
        BufferedReader reader;

        /*****start*******/

        //TRY
        try
        {
            //instantiate buffered reader
            reader = new BufferedReader(new InputStreamReader(map));

            //FOR(the amount of rows)
            for(int i = 0; i < game.MAX_ROWS; i++)
            {
                //read line
                line = reader.readLine();

                //FOR(the amount of columns)
                for(int j = 0; j < game.MAX_COLUMNS; j++)
                {
                    //add each number as an entity in array
                    lineArray = line.split(" ");
                    
                    //parse token to get index
                    index = Integer.parseInt(lineArray[j]);

                    //place into 2d array
                    mapIndex[j][i] = index;

                }//END FOR
               
            }//END FOR

            //close reader
            reader.close();

            //create objects
            loadObjects();

            //create enemies
            loadEnemies();

        }//CATCH(io exception)
        catch(IOException e)
        {
            //display print stack trace
            e.printStackTrace();

        }//END TRY-CATCH

    }//END loadMap

    /**********************************************************
     * Program Name   : draw
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method draws the tiles
     * 
     * Pseudocode:
     * -------
     * BEGIN draw
     *  call draw tiles
     * END draw
     **********************************************************/
    public void draw(Graphics2D graphics2d)
    {
        //local constants

        //local variables 

        /******start*******/

        //drawTiles
        drawTiles(graphics2d);

    }//END draw


    /**********************************************************
     * Program Name   : drawTiles
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method draws the tiles
     * 
     * Pseudocode:
     * -------
     * BEGIN drawTiles
     *  draw background image
     *  FOR(the amount of rows)
     *      FOR(the amount of columns)
     *         get tile num
     *          IF(index is not null)
     *              draw tile at position
     *          END IF
     *      increase x position
     *      END FOR
     *      increase y position
     *      initialize x to zero
     *  END FOR
     * END drawTiles
     **********************************************************/
    public void drawTiles(Graphics2D graphics)
    {
        //local constants
        final int NULL = 0;

        //local variables 
        int x = 0;
        int y = 0;
        int tileNum;

        /******start*******/

        //draw background
        graphics.drawImage(background, 0,0, null);

        //FOR(the amount of rows)
        for(int i = 0; i < game.MAX_ROWS; i++)
        {
            //FOR(the amount of columns)
            for(int j = 0; j < game.MAX_COLUMNS; j++)
            {
                //get tile number
                tileNum = mapIndex[j][i];

                //IF(index is not null)
                if(tileNum != NULL)
                {
                    //draw tile at position
                    graphics.drawImage(tileManager.getTiles()[tileNum].getImage(), x, y, (tileManager.getTiles()[tileNum].getImage().getHeight() * game.SCALE),
                                            (tileManager.getTiles()[tileNum].getImage().getWidth() * game.SCALE), null);

                }//END IF
                
                //increase x position
                x += game.TILE_SIZE;

            }//END FOR

            //increase y position
            y += game.TILE_SIZE;

            //put x back to 0
            x = 0;

        }//END FOR

    }//END drawTiles

    /**********************************************************
     * Program Name   : getCoins
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns the coins 
     *  array list
     * 
     * Pseudocode:
     * -------
     * BEGIN getCoins
     *  return list
     * END getCoins
     **********************************************************/
    public ArrayList<Coin> getCoins() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return coin list
        return coins;
    }

    /**********************************************************
     * Program Name   : getPowerUps
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns the power ups 
     *  array list
     * 
     * Pseudocode:
     * -------
     * BEGIN getPowerUps
     *  return list
     * END getgetPowerUpsCoins
     **********************************************************/
    public ArrayList<PowerUps> getPowerUps() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return power up list
        return powerUps;
    }

    /**********************************************************
     * Program Name   : getSpikes
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns the spikes
     *  array list
     * 
     * Pseudocode:
     * -------
     * BEGIN getSpikes
     *  return list
     * END getSpikes
     **********************************************************/
    public ArrayList<Spikes> getSpikes() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return spike list
        return spikes;
    }

    /**********************************************************
     * Program Name   : getDoors
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns the doors
     *  array list
     * 
     * Pseudocode:
     * -------
     * BEGIN getDoors
     *  return list
     * END getDoors
     **********************************************************/
    public ArrayList<Door> getDoors() 
    {
        //local constants

        //local variables

        /*********start*********/

        //return door list
        return doors;
    }

    /**********************************************************
     * Program Name   : getEnemies
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns the enemies
     *  array list
     * 
     * Pseudocode:
     * -------
     * BEGIN getEnemies
     *  return list
     * END getEnemies
     **********************************************************/
    public ArrayList<Enemy> getEnemies()
    {
        //local constants

        //local variables

        /*********start*********/

        //return enemy list
        return enemies;
    }

    /**********************************************************
     * Program Name   : getMapIndex
     * Author         : Taylor
     * Date           : October 14, 2022
     * Course/Section : CSC 112-301
     * Program Description: this method returns the map index array
     * 
     * Pseudocode:
     * -------
     * BEGIN getMapIndex
     *  return array
     * END getMapIndex
     **********************************************************/
    public int[][] getMapIndex()
    {
        //local constants

        //local variables

        /***start***/

        //return map indexes
        return mapIndex;
    }
    
    
}
