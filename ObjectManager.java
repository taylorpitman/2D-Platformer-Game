
/**********************************************************
 * Program Name   : ObjectManager
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class manages all objects in a specific
 *  level by determining which ones to draw, how many there are
 * and more
 * 
 * METHODS
 * -----
 * ObjectManager
 * loadImages
 * update
 * initObjects
 * draw
 * drawCoins
 * drawSpikes
 * drawDoor
 * drawPowerUps
 * checkObjectCollision
 **********************************************************/

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ObjectManager 
{
    //class constants
    final int COIN_IDLE_INDEX       = 0;
    final int WEAPON_INDEX          = 0;
    final int SHIELD_INDEX          = 1;
    public final int COIN_TYPE      = 0;
    public final int DOOR_TYPE      = 1;
    public final int WEAPON_TYPE    = 2;
    public final int SHIELD_TYPE    = 3;
    public final int SPIKES_TYPE    = 4;
    public final int LIVES_TYPE     = 5;

    //class variables
    GamePanel game;
    ImageIcon[] coinImages;
    ImageIcon[] powerUpImages;
    ImageIcon spikesImage;
    ImageIcon doorImage;
    ArrayList<Coin> coins;
    ArrayList<PowerUps> powerUps;
    ArrayList<Spikes> spikes;
    ArrayList<Door> door;
    Coin curCoin;        
    PowerUps curPowerUp;
    Spikes curSpike;
    Door curDoor;

    public ObjectManager(GamePanel imGame)
    {
        //local constants

        //local variables

        /******start*******/

        //initialize variables
        game = imGame;

        //load images
        loadImages();

        //initialize objects    
        initObjects();
        
    }//END constructor

    public void loadImages()
    {
        //local constants

        //local variables
        ImageIcon coinIdle;
        ImageIcon sword;
        ImageIcon shield;

        /*****start******/

        //instantiate arrays
        coinImages = new ImageIcon[2];
        powerUpImages = new ImageIcon[2];

        //coin array
        coinIdle = new ImageIcon(this.getClass().getResource("/objectSprites/Coin Rotating.gif"));
        coinImages[COIN_IDLE_INDEX] = coinIdle;

        //powerup array
        sword = new ImageIcon(this.getClass().getResource("/objectSprites/Sword Shine.gif"));
        shield = new ImageIcon(this.getClass().getResource("/objectSprites/Shield Shine.gif"));
        powerUpImages[0] = sword;
        powerUpImages[1] = shield;

        //spikes image
        spikesImage = new ImageIcon(this.getClass().getResource("/objectSprites/Spikes.gif"));

        //door image
        doorImage = new ImageIcon(this.getClass().getResource("/objectSprites/Door.gif"));

    }

    public void update()
    {
        //local constants

        //local variables

        /****start****/

        //initialize objects
        initObjects();
    }

    public void initObjects()
    {
        //local constants

        //local variables

        /***start***/
        
        //instaintiate array lists
        coins = game.levelManager.getCurLevel().getCoins();
        powerUps = game.levelManager.getCurLevel().getPowerUps();
        spikes = game.levelManager.getCurLevel().getSpikes();
        door = game.levelManager.getCurLevel().getDoors();
    }

    public void draw(Graphics2D g)
    {
        //local constants

        //local varibales

        /*****start******/

        //draw coins
        drawCoins(g);

        //draw powerups
        drawPowerUps(g);

        //draw spikes
        drawSpikes(g);

        //draw door
        drawDoor(g);

    }

    public void drawCoins(Graphics2D g)
    {
        //local constants 

        //local varibales

        /*****start*******/

        //FOR(the amount of coins)
        for(int i = 0; i < coins.size(); i++)
        {
            //get curren coin
            curCoin = coins.get(i);

            //IF(coin is active)
            if(curCoin.isActive())
            {
                //draw image
                g.drawImage(coinImages[COIN_IDLE_INDEX].getImage(), 
                curCoin.getHitBox().x - curCoin.getxDrawOffset(),
                curCoin.getHitBox().y - curCoin.getyDrawOffset(),
                coinImages[COIN_IDLE_INDEX].getIconWidth()  * game.SCALE,
                coinImages[COIN_IDLE_INDEX].getIconHeight() * game.SCALE,
                null);
                
            }//END IF

        }//END FOR
    }

    public void drawSpikes(Graphics2D g)
    {
        //local constants 

        //local varibales

        /*****start*******/

        //FOR(number of spikes)
        for(int i = 0; i < spikes.size(); i++)
        {
            //get current spike
            curSpike = spikes.get(i);

            //IF(spike is active)
            if(curSpike.isActive())
            {
                //draw image
                g.drawImage(spikesImage.getImage(), 
                curSpike.getX() - curSpike.getxDrawOffset(),
                curSpike.getY() - curSpike.getyDrawOffset(),
                spikesImage.getIconWidth()  * game.SCALE,
                spikesImage.getIconHeight() * game.SCALE,
                null);

            }//END IF
        }//ENF FOR
    }

    public void drawDoor(Graphics2D g)
    {
        //local constants 

        //local varibales

        /*****start*******/

        //FOR(number of doors)
        for(int i = 0; i < door.size(); i++)
        {
            //get current door
            curDoor = door.get(i);

            //IF(door is active)
            if(curDoor.isActive())
            {

                //draw door
                g.drawImage(doorImage.getImage(), 
                curDoor.getHitBox().x - curDoor.getxDrawOffset(),
                curDoor.getHitBox().y - curDoor.getyDrawOffset(),
                doorImage.getIconWidth()  * game.SCALE,
                doorImage.getIconHeight() * game.SCALE,
                null);

            }//END IF

        }//END FOR
    }

    public void drawPowerUps(Graphics2D g)
    {
        //local constants 

        //local variables

        /*****start******/

        //FOR(number of powerups)
        for(int i = 0; i < powerUps.size(); i++)
        {
            //get current powerup
            curPowerUp = powerUps.get(i);

            //IF(power up is active)
            if(curPowerUp.isActive())
            {
                //IF(power up is weapon)
                if(curPowerUp.getObjType() == WEAPON_TYPE )
                {
                    //draw weapon
                    g.drawImage(powerUpImages[WEAPON_INDEX].getImage(), 
                    curPowerUp.getHitBox().x - curPowerUp.getxDrawOffset(),
                    curPowerUp.getHitBox().y - curPowerUp.getyDrawOffset(),
                    powerUpImages[WEAPON_INDEX].getIconWidth() * game.SCALE,
                    powerUpImages[WEAPON_INDEX].getIconHeight() * game.SCALE,
                    null);

                }
                //ELSE power up is shield
                else
                {
                    //draw shield
                    g.drawImage(powerUpImages[SHIELD_INDEX].getImage(), 
                    curPowerUp.getHitBox().x - curPowerUp.getxDrawOffset(),
                    curPowerUp.getHitBox().y - curPowerUp.getyDrawOffset(),
                    powerUpImages[SHIELD_INDEX].getIconWidth() * game.SCALE,
                    powerUpImages[SHIELD_INDEX].getIconHeight() * game.SCALE,
                    null); 

                }//END IF

            }//ENDIF

        }//END FOR
    }

    public void checkObjectCollision(Rectangle hitBox)
    {
        //local constants

        //local variables

        /********start********/

        //FOR(the amount of coins)
        for(int i = 0; i < coins.size(); i++)
        {
            //get current coin
            curCoin = coins.get(i);

            //IF(current coin is active)
            if(curCoin.isActive())
            {
                //IF(player collides with coin)
                if(hitBox.intersects(curCoin.getHitBox()))
                {
                    //play sound effect
                    game.playSoundEffect(game.audio.COIN);

                    //set active to false
                    curCoin.setActive(false);

                    //add to coin score
                    game.addCoin();

                }//END IF

            }//END IF

        }//END FOR

       //FOR(the amount of powerups)
       for(int i = 0; i < powerUps.size(); i++)
       {
            //get current powerup
           curPowerUp = powerUps.get(i);
        
           //IF(theres a collision)
           if(hitBox.intersects(curPowerUp.getHitBox()))
           {
                //IF(power up is weapon)
                if(curPowerUp.getObjType() == WEAPON_TYPE )
                {
                    //set weapon to inactive
                    curPowerUp.setActive(false);

                    //give player weapon
                    game.player.attack();     

                }//ENDIF
            
           }//END IF

       }//END FOR

       //FOR(the amount of spikes)
       for(int i = 0; i < spikes.size(); i++)
       {
            //get current spike
            curSpike = spikes.get(i);

            //IF(player hitbox intersects with spike hit box)
            if(hitBox.intersects(curSpike.getHitBox()))
            {
                //player loses life
                game.player.loseLife();

            }//END IF

       }//END FOR

       //FOR(the amount of doors)
       for(int i = 0; i < door.size(); i++)
       {
            //get current door
            curDoor = door.get(i);

            //IF(player intersects with door)
            if(hitBox.intersects(curDoor.getHitBox()))
            {
                //level over
                game.player.levelComplete();
                
            }//END IF

       }//END FOR

    }//END check collision

    public void reset()
    {
        //local constants

        //local variables

        /*****start*****/
        
        //FOR(the amount of coins)
        for(int i = 0; i < coins.size(); i++)
        {
            //get current coin
            curCoin = coins.get(i);

            //IF(current coin is not active)
            if(!curCoin.isActive())
            {
                //reset coin
                curCoin.reset();

            }//END IF

        }//END for

       //FOR(the amount of powerups)
       for(int i = 0; i < powerUps.size(); i++)
       {
            //get power up
           curPowerUp = powerUps.get(i);

           //if(power up is not active)
           if(!curPowerUp.isActive())
           {
                //reset it
                curPowerUp.reset();

           }//END IF

       }//END FOR

    }//END METHOD
}
