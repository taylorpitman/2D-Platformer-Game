
/**********************************************************
 * Program Name   : CollisionDetection
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class lets the user
 *  know where they cannot go, and puts them in the next to 
 *  best spot
 *
 * METHODS
 * ----
 * CollisionDetection   - Constructor
 * checkTile            - checks each tile player wants to got to
 * isSolid              - checks to see if player is on solid tile
 * getXPosNextToWall    - places player as close to the wall as allowed
 * get yPosUnderRoofOrAboveFloor - places player as close to floor as possible
 * onFloor              - checks to see if plater is on floor
 * enemyOnFloor         - checks to see if enemy is on floor
 **********************************************************/
import java.awt.Rectangle;

public class CollisionDetection 
{
    //global constants

    //global variables 
    GamePanel panel;

    /**********************************************************
     * Method Name   : CollisionDetection
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method initializes given data
     *
     * BEGIN CollisionDetection
     *  initialize data
     * END CollisionDetection
     **********************************************************/
    public CollisionDetection(GamePanel inPanel)
    {
        //local constants

        //local variables

        //initialize data
        panel = inPanel;
    }

    /**********************************************************
     * Method Name    : checkTile
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method checks each possible 
     *  tile and if it is solid
     *
     * BEGIN checkTile
     *  IF(top left tile is not solid)
     *      IF(bottom right tile is not solid)
     *          IF(top right tile is not solid)
     *              IF(bottom left tile is not solid)
     *                  entity can move
     *              END IF
     *          END IF
     *      END IF
     *  END IF
     *  return can move
     * END checkTile
     **********************************************************/
    public boolean checkTile(Entity entity, double x, double y)
    {
        //local constants

        //local variables
        boolean canMove = false;

        /******start******/

        //IF(top left corner tile is not solid)
        if(!isSolid(x, y))
        {
            //IF(bottom right corner tile is not solid)
            if(!isSolid(entity.getHitBox().width + x, entity.getHitBox().height + y))
            {
                //IF(top right corner tile is not solid)
                if(!isSolid(entity.getHitBox().width + x, y))
                {
                    //IF(bottom left corner tile is not solid)
                    if(!isSolid(x, entity.getHitBox().height + y))
                    {
                        //entity can move
                        canMove = true;

                    }//END IF

                }//END IF

            }//END IF

        }//END IF

        //return can move
        return canMove;

    }//END checkTile  

    /**********************************************************
     * Method Name   : isSolid
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method checks if a tile
     *  can be moved through
     *
     * BEGIN isSolid
     *  IF(hitbox is hitting the top or bottom of screen)
     *      tile is solid
     *  ELSE IF(hitbox is hitting left or right screen borders)
     *      tile is solid
     *  ELSE
     *      find current collumn and row
     *      get tile index
     *      IF(tile is solid)
     *          tile is solid
     *      END IF
     *  END IF
     *  return result
     * END isSolid
     **********************************************************/
    private boolean isSolid(double xCorner, double yCorner)
    {
        //local constants   
        final int NOT_SOLID = 0;

        //local variables
        boolean solid = false;
        int xIndex;
        int yIndex;
        int tileValue;

        /*****start*******/

        //IF(hit box is hitting the verticle borders)
        if(xCorner < 0 || xCorner >= panel.SCREEN_WIDTH)
        {
            //entity cant pass
            solid = true;
        }  
        //ELSE IF(hit box is hitting the horizontal borders)
        else if(yCorner < 0 || yCorner >= panel.SCREEN_HEIGHT)
        {
            //entity cant pass
            solid = true;
        }
        //ELSE
        else
        {
  
            //find column and row
            xIndex = (int) xCorner / panel.TILE_SIZE;
            yIndex = (int) yCorner / panel.TILE_SIZE;

            //get tile value
            tileValue = panel.getLevelManager().getCurLevel().getMapIndex()[xIndex][yIndex];

            //IF(tile is solid)
            if(tileValue != NOT_SOLID)
            {
                //entity cant pass
                solid = true;

            }//END IF
            
        }//END IF

        //return if it is solid
        return solid;
    }

    /**********************************************************
     * Method Name   : getXPosNextToWall
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method gets the closest x position
     *  possible without interacting with a solid tile
     *
     * BEGIN getXPosNextToWall
     *  get current tile
     *  IF(entity is going to the right)
     *      get tile x position
     *      get offset to hitbox
     *      calculate needed x position for entity
     *  ELSE moving left
     *      calculate needed x position
     *  END IF
     *  return new position
     * END getXPosNextToWall
     **********************************************************/
    public int getXPosNextToWall(Rectangle hitBox, double xSpeed)
    {
        //local constants
        final int MOVING_RIGHT = 0;
        final int PIXEL = 1;

        //local variables
        int currentTile;
        int tileXPos;
        int xOffset;
        int xPos;

        /**********start********/

        //get current tile
        currentTile = hitBox.x / panel.TILE_SIZE;

        //IF(entity is going to the right)
        if(xSpeed > MOVING_RIGHT)
        {
            //get tile x position
            tileXPos = currentTile * panel.TILE_SIZE;

            //get offset to hitbox
            xOffset= panel.TILE_SIZE - hitBox.width;

            //calculate x position for entity
            xPos = tileXPos + xOffset - PIXEL;
        
        }//ELSE moving left
        else
        {
            //calculate needed x position
            xPos = currentTile * panel.TILE_SIZE;

        }//END IF

        //return x position
        return xPos;

    }// END 

    /**********************************************************
     * Method Name   : getYPosUnderRoofOrAboveFloor
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method gets the closest y position
     *  possible without interacting with a solid tile
     *
     * BEGIN getYPosUnderRoofOrAboveFloor
     *  get current tile
     *  IF(entity is falling)
     *      get tile y position
     *      get offset to hitbox
     *      calculate needed y position for entity
     *  ELSE jumping
     *      calculate needed y position
     *  END IF
     *  return new position
     * END getYPosUnderRoofOrAboveFloor
     **********************************************************/
    public int getYPosUnderRoofOrAboveFloor(Rectangle hitBox, double airSpeed)
    {
        //local constants
        final int FALLING = 0;
        final int PIXEL = 1;

        //local variables
        int currentTile;
        int tileYPos;
        int yOffset;
        int yPos;

        /**********start**********/

        //get current tile
        currentTile = hitBox.y / panel.TILE_SIZE;

        //IF(entity is falling)
        if(airSpeed > FALLING)
        {
            //get tile y position
            tileYPos = currentTile * panel.TILE_SIZE;

            //get offset to hitbox
            yOffset= panel.TILE_SIZE - hitBox.height;

            //calculate needed y position
            yPos = tileYPos + yOffset - PIXEL;
        }
        //ELSE jumping
        else
        {
            //calculate needed y position
            yPos = currentTile * panel.TILE_SIZE;

        }//END IF

        return yPos;

    }//END

    /**********************************************************
     * Method Name   : onFloor
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method determines if the player
     *  is on the floor
     *
     * BEGIN onFloor
     *  IF(bottom left tile is not solid)
     *      IF(bottom right tile is not solid)
     *          not on floor
     *      END IF
     *  END IF
     *  return result
     * END onFloor
     **********************************************************/
    public boolean onFloor(Rectangle hitBox)
    {
        //local constants

        //local varibales
        boolean onFloor = true;

        /*****start******/

        //IF(bottom left tile is not solid)
        if(!isSolid(hitBox.x,hitBox.height + hitBox.y + 1))
        {
            //IF(bottom right tile is not solid)
            if(!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1))
            {
                //not on floor
                onFloor = false;

            }//END IF

        }//END IF

        //return result
        return onFloor;
    }

    /**********************************************************
     * Method Name   : enemyOnFloor
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Program Description: This method determines if an enemy is on 
     *  the floor
     *
     * BEGIN enemyOnFloor
     *  IF(bottom right tile is not solid)
     *       not on floor
     *  END IF
     *  return result
     * END enemyOnFloor
     **********************************************************/
    public boolean enemyOnFloor(Rectangle hitBox)
    {
        //local constants

        //local variables
        boolean onFloor = true;

        /****Start*****/

        //IF(bottom right tile is not solid)
        if(!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1))
        {
            //enemy not on floor
            onFloor = false;

        }//END IF

        //return result
        return onFloor;

    }
 
}
 