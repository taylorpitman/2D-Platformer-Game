
/**********************************************************
 * Program Name   : GamePanel
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This is the panel for the overall program. 
 *  In which a player is in a 2d environment and can move
 *  left, right and jump. The player's goal is to reach the 
 *  door that indicates the end of each level. 
 *  To get to the door, they must jump over obstacles and 
 *  destroy enemies along the way. They also have the option of 
 *  getting all 5 coins each level
 * 
 * METHODS
 * --
 * GamePanel                - contructor method
 * playMusic                - play music
 * stopMusic                - stops music
 * playSoundEffect          - plays sound effect
 * removeUIButtons          - removes buttons from panel
 * setUp                    - sets up game
 * makeUI                   - makes labels/buttons for UI
 * drawUI                   - draws UI to panel
 * updateScores             - updates labels
 * addCoin                  - adds one to score count
 * makeButtons              - makes buttons for menues
 * startThread              - starts thread
 * run                      - game loop/ runs thread
 * reset                    - reset game components
 * resetCoins               - reset coin label
 * update                   - update components
 * paintComponent           - paints components to screen
 * updateTime               - update timer
 * getCollisionDetection    - returns collision detection object
 * getTileManager           - returns tile manager object
 **********************************************************/

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable 
{
    /******CLASS CONSTANTS*******/

    // screen and entity sizes
    public final int OG_TILE_SIZE = 16;                         // 16 pixels
    public final int SCALE = 3;                                 // scaling to make everything bigger
    public final int TILE_SIZE = OG_TILE_SIZE * SCALE;          // 16 * 3 = 48 pixels x 48 pixels
    public final int MAX_COLUMNS = 16;
    public final int MAX_ROWS = 12;
    public final int SCREEN_WIDTH = MAX_COLUMNS * TILE_SIZE;    // max columns * tile size (768 pixels)
    public final int SCREEN_HEIGHT = MAX_ROWS * TILE_SIZE;      // max rows * tile size (576 pixels)

    //fps
    final int FPS = 60; // wanted fps for game

    //gamestates
    public final int MAIN_MENU      = 0;
    public final int LEVEL_SELECT   = 1;
    public final int PLAY           = 2;  
    public final int PAUSE          = 3;
    public final int GAME_OVER      = 4;
    public final int LEVEL_COMPLETE = 5;
    
    //levels
    public final int TUTORIAL = 0;
    public final int LEVEL_ONE = 1;
    public final int LEVEL_TWO = 2;
    public final int LEVEL_THREE = 3;

    /*********class variables************/

    private int curGameState;                                        // current game state
    private int curLevel;                                            // current level
    private int coins;                                              //current number of coins
    private int completeCoins;                                      //coins at end of level

    /********class objects********/

    TileManager tiles = new TileManager(this);                      // send GamePanel to instantiate tilemanager class   
    LevelManager levelManager;                                      // instantiate level handler                                   
    KeysHandler keyHandler = new KeysHandler(this);                 // instantiate  keys handler
    ClickHandler clickHandler = new ClickHandler(this);             // instantiates click handler
    ObjectManager objectManager;
    EnemyManager enemyManager;      
    CollisionDetection detectCollision = new CollisionDetection(this);  // send GamePanel to instantiate collision Detection class
    UserInterface UI = new UserInterface(this);
    Player player = new Player(this, keyHandler, clickHandler);         // instantiate player object

    //button objects
    ImageIcon mainStartGame, mainPlayTutorial, mainExitGame;
    ImageIcon pauseContinueGame, pauseExitGame;
    ImageIcon levelMenuOne, levelMenuTwo, levelMenuThree;
    ImageIcon overTryAgain;
    public JButton mainStart, mainTutorial, mainExit,
    pauseContinue, pauseExit, levelOne, levelTwo, levelThree,
    tryAgain, coinsCollected;
    ButtonListener buttonListener = new ButtonListener(this);

    //label objects
    ImageIcon coinImage;
    ImageIcon livesImage; 
    ImageIcon clockImage;
    JButton clockIcon;
    public JButton clockUI;
    JButton coinIcon;
    JButton livesIcon;
    public JButton coinUI;
    JButton livesUI;

    //Sound
    Sound audio = new Sound();

    Thread gameThread; // allows the program to continously run until stop is prompted

    /**********************************************************
     * Method Name   : GamePanel
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method acts as our constructor and 
     *  sets up the listeners, and initializes/ instantiates data
     *  we will need later on
     * 
     * BEGIN GamePanel
     *  add keyboard listener
     *  add mouse listener
     *  set screen size
     *  instantiate managers
     *  call set up game method
     * END GamePanel
     **********************************************************/

    public GamePanel() 
    {
        //local constants

        //local variables

        /*********start*********/

        // Keyboard listener
        addKeyListener(keyHandler);

        //mouse listener
        addMouseListener(clickHandler);

        // set screen size
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        setDoubleBuffered(true); // helps render preformance

        //instantiate managers
        levelManager = new LevelManager(this, tiles); 
        objectManager = new ObjectManager(this);
        enemyManager = new EnemyManager(this);

        //set up game
        setUp();

    }

   /**********************************************************
     * Method Name   : playMusic
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method gives the sound class what 
     * thet need to play the music
     * 
     * BEGIN playMusic
     *  set needed sound
     *  play music
     *  loop music
     * END playMusic
     **********************************************************/

    public void playMusic(int index)
    {
        //local constants

        //local variables

        /***Start***/

        //set needed sound 
        audio.setFile(index);

        //play the music
        audio.play();

        //loop music
        audio.loop();
    }

   /**********************************************************
     * Method Name   : stopMusic
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method gives the sound class what 
     * they need to stop the music
     * 
     * BEGIN stopMusic
     *  stop music
     * END stopMusic
     **********************************************************/
    public void stopMusic()
    {
        //local constants

        //local variables

        /**start***/

        //stop music
        audio.stop();
    }
   /**********************************************************
     * Method Name   : playSoundEffect
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method gives the sound class what 
     * thet need to play the sound effect
     * 
     * BEGIN playSoundEffect
     *  set correct file
     *  play sound effect
     * END playSoundEffect
     * END playSoundEffect
     **********************************************************/
    public void playSoundEffect(int index)
    {
        //local constants

        //local variables

        /***start***/

        //set file
        audio.setFile(index);

        //play sound effect
        audio.play();
    }

    /**********************************************************
     * Method Name   : removeUIButtons
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method removes the UI from view once
     *  the game state is over
     * 
     * BEGIN removeUIButtons
     *  remove UI from panel
     *  END removeUIButtons
     **********************************************************/
    public void removeUIButtons()
    {
        //local constants

        //local variables

        /*****start*****/

        //remove UI
        remove(coinUI);
        remove(coinIcon);
        remove(livesIcon);
        remove(livesUI);
        remove(clockIcon);
        remove(clockUI);
    }

    /**********************************************************
     * Method Name   : setUp
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method sets up and instantites
     *  certain objects and levels to get the game started
     * 
     * BEGIN setUp
     *  initialize coins to 0
     *  initialize game state
     *  call make UI method
     *  start music
     * END setUp
     **********************************************************/
    public void setUp()
    {
        //local constants

        //local variables

        /****start****/
        
        //initialize coins to 0
        coins = 0;

        //initialize game state
        curGameState = MAIN_MENU;
        
        //make UI
        makeUI();

        //start music
        playMusic(audio.BG_MUSIC);


    }//END setUp

    /**********************************************************
     * Method Name   : makeUI
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method instantiates all the needed buttons 
     * for the Use Interface
     * 
     * BEGIN makeUI
     *  call make buttons method
     *  instantiate main UI buttons
     * END makeUI
     **********************************************************/
    private void makeUI() 
    {
        //local constants

        //local variables


        /*****start*****/

        //call make buttons method
        makeButtons();

        //main UI buttons
        coinImage = new ImageIcon(this.getClass().getResource("/objectSprites/coinUI.gif"));
        livesImage = new ImageIcon(this.getClass().getResource("/objectSprites/livesUI.gif"));
        clockImage = new ImageIcon(this.getClass().getResource("/objectSprites/clock.png"));
    
        coinIcon = new JButton(coinImage);
        livesIcon = new JButton(livesImage);
        coinIcon.setBorderPainted(false);
        coinIcon.setContentAreaFilled(false);
        livesIcon.setBorderPainted(false);
        livesIcon.setContentAreaFilled(false);

        clockIcon = new JButton(clockImage);
        clockIcon.setBorderPainted(false);
        clockIcon.setContentAreaFilled(false);

        clockUI = new JButton("");
        clockUI.setBorderPainted(false);
        clockUI.setContentAreaFilled(false);
        clockUI.setFont(new Font("Arial", Font.BOLD, 25));

        coinUI = new JButton("" + coins);
        coinUI.setBorderPainted(false);
        coinUI.setContentAreaFilled(false);
        coinUI.setFont(new Font("Arial", Font.BOLD, 25));

        livesUI = new JButton("" + player.getLives());
        livesUI.setBorderPainted(false);
        livesUI.setContentAreaFilled(false);
        livesUI.setFont(new Font("Arial", Font.BOLD, 25));

        coinsCollected = new JButton(" " + completeCoins);
        coinsCollected.setBorderPainted(false);
        coinsCollected.setContentAreaFilled(false);
        coinsCollected.setFont(new Font("Arial", Font.BOLD, 30));

    }
    /**********************************************************
     * Method Name   : updateScores
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method updates the score UI
     * 
     * BEGIN updateScores
     *  update text
     * END updateScores
     **********************************************************/
    private void updateScores()
    {
        //local constants

        //local variables

        /****Start******/

        //update text
        coinUI.setText("" + coins);
        livesUI.setText("" + player.getLives());
    }

    /**********************************************************
     * Method Name   : addCoin
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method adds one coin to the player score
     * 
     * BEGIN addCoin
     *  play sound effect
     *  add one to coins
     * END addCoin
     **********************************************************/
    public void addCoin()
    {
        //local constants

        //local variables

        //play sound effect
        playSoundEffect(audio.COIN);
        
        //add one to coins
        coins++;

    }//END addCoin
    
    /**********************************************************
     * Method Name   : makeButtons
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method instantitates the buttons 
     *  needed for the menus
     * 
     * BEGIN makeButtons
     *  instantiate main menu image icons
     *  instantiate jbuttons
     *  instantiate pause image icons
     *  instantiate jbuttons
     *  instantitate level select menu
     *  instantiate jbuttons
     *  instnatitae game over menu
     *  instantiate jbutton
     * END makeButtons
     **********************************************************/
    public void makeButtons()
    {
        //local constants

        //local variables

        /******start******/

        //main menu
        mainStartGame = new ImageIcon(this.getClass().getResource("/menuImages/mainMenu/main_new_game.png"));
        mainPlayTutorial = new ImageIcon(this.getClass().getResource("/menuImages/mainMenu/main_play_tutorial.png"));
        mainExitGame = new ImageIcon(this.getClass().getResource("/menuImages/mainMenu/main_exit_game.png"));

        //Instantiate JButtons
        mainStart = new JButton(mainStartGame);
        mainStart.addActionListener(buttonListener);
        mainStart.setBorderPainted(false);
        mainStart.setContentAreaFilled(false);
    
        mainTutorial = new JButton(mainPlayTutorial);
        mainTutorial.addActionListener(buttonListener);
        mainTutorial.setBorderPainted(false);
        mainTutorial.setContentAreaFilled(false);
        
        mainExit = new JButton(mainExitGame);
        mainExit.addActionListener(buttonListener);
        mainExit.setBorderPainted(false);
        mainExit.setContentAreaFilled(false);
        mainExit.setBounds(100, 700, 100, 200);

        //pause menu
        pauseContinueGame = new ImageIcon(this.getClass().getResource("/menuImages/pauseMenu/pause_continue.png"));
        pauseExitGame = new ImageIcon(this.getClass().getResource("/menuImages/pauseMenu/pause_exit.png"));

        //Instantiate JButtons
        pauseContinue = new JButton(pauseContinueGame);
        pauseContinue.addActionListener(buttonListener);
        pauseContinue.setBorderPainted(false);
        pauseContinue.setContentAreaFilled(false);
    
        pauseExit = new JButton(pauseExitGame);
        pauseExit.addActionListener(buttonListener);
        pauseExit.setBorderPainted(false);
        pauseExit.setContentAreaFilled(false);

        //level select menu
        levelMenuOne = new ImageIcon(this.getClass().getResource("/menuImages/levelMenu/level_one.png"));
        levelMenuTwo = new ImageIcon(this.getClass().getResource("/menuImages/levelMenu/level_two.png"));
        levelMenuThree = new ImageIcon(this.getClass().getResource("/menuImages/levelMenu/level_three.png"));
        
        //Instantiate JButtons
        levelOne = new JButton(levelMenuOne);
        levelOne.addActionListener(buttonListener);
        levelOne.setBorderPainted(false);
        levelOne.setContentAreaFilled(false);
        
        levelTwo = new JButton(levelMenuTwo);
        levelTwo.addActionListener(buttonListener);
        levelTwo.setBorderPainted(false);
        levelTwo.setContentAreaFilled(false);
        
        levelThree = new JButton(levelMenuThree);
        levelThree.addActionListener(buttonListener);
        levelThree.setBorderPainted(false);
        levelThree.setContentAreaFilled(false);

        //game over menu
        overTryAgain = new ImageIcon(this.getClass().getResource("/menuImages/gameOverMenu/playAgain.png"));

        tryAgain = new JButton(overTryAgain);
        tryAgain.addActionListener(buttonListener);
        tryAgain.setBorderPainted(false);
        tryAgain.setContentAreaFilled(false);
        
    }

    /**********************************************************
     * Method Name   : startThread
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method starts the game thread to continuosly 
     *  update the game
     * 
     * BEGIN startThread
     *  instantiate gameThread
     *  startThread
     * END startThread
     **********************************************************/
    public void startThread() 
    {
        //local constants

        //local varibales

        /***start***/

        // instantiate game thread
        gameThread = new Thread(this); // pass this class through constructor

        // start thread
        gameThread.start(); // automatically calls run method

    }//END start thread

    /**********************************************************
     * Method Name   : run
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method holds our game loop
     *  in which the frames update 60 times per second
     * 
     * BEGIN run
     *  WHILE(game thread exisits)
     *      use delta method for FPS
     *      get current time
     *      calculate delta
     *      get last time
     *      IF(delta is over or equal to one second)
     *          update character action
     *          draw screen
     *          decrement delta
     *      END IF
     *  END WHILE
     * END run
     **********************************************************/
    @Override
    public void run() 
    {
        // local constants
        final int ONE_SEC = 1;

        // local variables
        double drawInterval = 1000000000 / FPS; // interval between updates
        double delta = 0;                       // counter
        long lastTime = System.nanoTime();      // time at the beginning of loop
        long currentTime;                       // new time

        /******* start *********/

        // WHILE(game thread exists)
        while (gameThread != null) 
        {
            // have the program draw the screen 60 times per second (the delta method)

            // check current time
            currentTime = System.nanoTime();

            // add (how much time has passed) / interval to delta
            delta += (currentTime - lastTime) / drawInterval;

            // reinitalize last time
            lastTime = currentTime;

            // IF(delta greater than or equal to the interval)
            if (delta >= ONE_SEC) 
            {
                // update character action
                update();

                // draw screen
                repaint();

                // decrement delta
                delta--;

            } // END IF

        } // END WHILE

    }// END run

    /**********************************************************
     * Method Name   : reset
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method resets all the attributes 
     * of a level
     * 
     * BEGIN reset
     *  reset objects 
     *  reset enemies
     *  reset player
     *  reset UI
     *  reset coin amount
     *  remove UI
     * END reset
     **********************************************************/
    public void reset()
    {
        //local constants

        //local variables

        /******start*****/

        //reset objects
        objectManager.reset();

        //reset enemies
        enemyManager.reset();

        //reset character
        player.reset();

        //reset UI
        clockUI.setText(" ");

        //reset coin amount
        resetCoins();

        //remove UI buttons
        removeUIButtons();

    }
    /**********************************************************
     * Method Name   : resetCoins
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method resets the coins
     * 
     * BEGIN resetCoins
     *  store completed coins
     *  reset coins
     * END resetCoins
     **********************************************************/
    public void resetCoins() 
    {
        //local constants
        final int MIN_COINS = 0;

        //local variables

        /*****start*****/

        //store completed coins
        completeCoins = coins;

        //reset coins
        coins = MIN_COINS;

    }

    /**********************************************************
     * Method Name   : update
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method determines
     *  what gets updated depending on the game state 
     * 
     * BEGIN update
     *  IF(game state is play)
     *      update player
     *      update objects
     *      update enemies
     *      update UI
     *  END IF
     * END update
     **********************************************************/    
    public void update() 
    {
        // local constants

        // local variables

        /****** start ********/

        //IFgame state is playing)
        if(curGameState == PLAY)
        {
            // update player
            player.update();
            
            //update objects
            objectManager.update();
        
            //update enemies
            enemyManager.update();
            
            //update game assets
            updateScores();

        }//END IF

    }// END update

    /**********************************************************
     * Method Name   : paintComponent
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method paints all components
     *  and attributes to the panel
     * 
     * BEGIN paintComponent
     *  call parent method
     *  change graphics to 2d
     *  IF(gamestate is play)
     *      request focus foe key listener
     *      draw level
     *      draw objects
     *      draw enemies
     *      draw player
     *      draw UI
     *  ELSE IF(game state is main menu)
     *      draw main menu
     *      set bounds of buttons
     *      add to panel
     *  ELSE IF(game state is pause)
     *      draw main menu
     *      set bounds of buttons
     *      add to panel
     *  ELSE IF(game state is level select)
     *      draw main menu
     *      set bounds of buttons
     *      add to panel
     *  ELSE IF(game state is gameover)
     *      draw main menu
     *      set bounds of buttons
     *      add to panel
     *  END IF
     * END paintComponent
     **********************************************************/
    public void paintComponent(Graphics page) 
    {
        // local constants
        final int OFFSET_Y = 70;
        final int BUTTON_ONE_Y = 240;
        final int BUTTON_X = 120;
        final int BUTTON_TWO_Y = BUTTON_ONE_Y + OFFSET_Y;
        final int BUTTON_THREE_Y = BUTTON_TWO_Y + OFFSET_Y;

        // local variables
        Graphics2D page2D;

        /***************** start ********************/

        // call parent method
        super.paintComponent(page);

        //change graphics to 2d graphics
        page2D = (Graphics2D) page;

        //IF(game state is play)
        if(curGameState == PLAY)
        {
            //have focus be on key listener
            requestFocus();

            //draw level
            levelManager.draw(page2D);

            //draw objects
            objectManager.draw(page2D);

            //draw enemies
            enemyManager.draw(page2D);

            //draw player
            player.draw(page2D); 

            //draw UI
            drawUI(page2D);

        }
        //ELSE IF(game state is main menu)
        else if (curGameState == MAIN_MENU)
        {
            //draw main menu
            UI.draw(page2D);

            //set bounds of buttons
            mainStart.setBounds(BUTTON_X, BUTTON_ONE_Y, mainStartGame.getIconWidth(), mainStartGame.getIconHeight());
            mainTutorial.setBounds(BUTTON_X, BUTTON_TWO_Y, mainPlayTutorial.getIconWidth(), mainPlayTutorial.getIconHeight());
            mainExit.setBounds(BUTTON_X,BUTTON_THREE_Y, mainExitGame.getIconWidth(), mainExitGame.getIconHeight());

            //add to panel
            add(mainStart);
            add(mainTutorial);
            add(mainExit);

        }
        //ELSE IF(game state is pause)
        else if(curGameState == PAUSE)
        {
            //draw pause menu
            UI.draw(page2D);

            //set bounds of buttons
            pauseContinue.setBounds(BUTTON_X, BUTTON_ONE_Y, pauseContinueGame.getIconWidth(), pauseContinueGame.getIconHeight());
            pauseExit.setBounds(BUTTON_X, BUTTON_TWO_Y, pauseExitGame.getIconWidth(), pauseExitGame.getIconHeight());
        
            //add to panel
            add(pauseContinue);
            add(pauseExit);

        }
        //ELSE IF(game state is level select)
        else if(curGameState == LEVEL_SELECT)
        {
            //draw level select menu
            UI.draw(page2D);

            //set bounds of buttons
            levelOne.setBounds(BUTTON_X, BUTTON_ONE_Y, levelMenuOne.getIconWidth(), levelMenuOne.getIconHeight());
            levelTwo.setBounds(BUTTON_X, BUTTON_TWO_Y, levelMenuTwo.getIconWidth(), levelMenuTwo.getIconHeight());
            levelThree.setBounds(BUTTON_X, BUTTON_THREE_Y, levelMenuThree.getIconWidth(), levelMenuThree.getIconHeight());

            //add buttons
            add(levelOne);
            add(levelTwo);
            add(levelThree);

        }//ELSE IF
        else if(curGameState == GAME_OVER)
        {
            //draw game over menu
            UI.draw(page2D);

            //set bounds of buttons
            pauseExit.setBounds(BUTTON_X, BUTTON_TWO_Y, pauseExitGame.getIconWidth(), pauseExitGame.getIconHeight());
            tryAgain.setBounds(BUTTON_X, BUTTON_ONE_Y, overTryAgain.getIconWidth(), overTryAgain.getIconHeight());

            //add buttons
            add(tryAgain);
            add(pauseExit);

        }//ELSE IF
        else if(curGameState == LEVEL_COMPLETE)
        {

            //draw level complete menu
            UI.drawLevelComplete(page2D);

            //set bounds of buttons
            pauseExit.setBounds(BUTTON_X, BUTTON_TWO_Y, pauseExitGame.getIconWidth(), pauseExitGame.getIconHeight());
            coinsCollected.setText(completeCoins + "/5");
            coinsCollected.setBounds(505, 183, 100, 100);

            //add buttons
            add(pauseExit);
            add(coinsCollected);
        }
        

    }// END paintComponent

    /**********************************************************
     * Method Name   : drawUI
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method draws the buttons and
     * UI to the screen
     * 
     * BEGIN drawUI
     *  fill rectangle
     *  make icons and buttons
     *  add to panel
     * END drawUI
     **********************************************************/
    private void drawUI(Graphics2D graphics) 
    {
        //local constants

        //local variables

        /****Start****/
        
        //fill rectangle
        graphics.setColor(new Color(89, 136, 56));
        graphics.fillRect(665, 5, 95, 110);

        //make icons and buttons
        coinIcon.setBounds(675, 40, coinImage.getIconWidth(), coinImage.getIconHeight());
        livesIcon.setBounds(675, 10, coinImage.getIconWidth(), coinImage.getIconHeight());   

        coinUI.setBounds(685, 20, 75,75);
        livesUI.setBounds(685, -10, 75,75);

        clockIcon.setBounds(669, 75, clockImage.getIconWidth(), clockImage.getIconHeight());
        clockUI.setBounds(685, 57, 75, 75);

        //add to panel
        add(coinIcon);
        add(livesIcon);
        add(coinUI);
        add(livesUI);
        add(clockIcon);
        add(clockUI);

    }

    /**********************************************************
     * Method Name   : updateTime
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method figures out how much
     *  time is left once the timer has started
     * 
     * BEGIN updateTime
     *  update clock
     * END updateTime
     **********************************************************/
    public void updateTime(int seconds)
    {
        //local constants
        final int MAX_SECS = 10;

        //local variables

        /****start****/

        //update clock
        clockUI.setText("" + (MAX_SECS - seconds/2));
    }

    /**********************************************************
     * Method Name   : getCollisionDetection
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns collision detection
     *  object
     * 
     * BEGIN getCollisionDetection
     *  return collision detection object
     * END getCollisionDetection
     **********************************************************/
    public CollisionDetection getCollisionDetection()
    {
        //local constants

        //local variables

        /*********start*********/
        
        //return detected collision
        return detectCollision;

    }//END get collision detection

   /**********************************************************
     * Method Name   : getTileManager
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the tile manager object
     * 
     * BEGIN getTileManager
     *  return tile manager object
     * END getTileManager
     **********************************************************/
    public TileManager getTileManager()
    {
        //local constants

        //local variables

        /*********start*********/

        //return tile manager
        return tiles;
        
    }//END get tile manager

    /**********************************************************
     * Method Name   : getCurGameState
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the
     *  current game state
     * 
     * BEGIN getCurGameState
     *  return  Current Game State
     * END getCurGameState
     **********************************************************/
    public int getCurGameState() 
    {
        //local constants

        //local variables

        /***start***/

        //return game state
        return curGameState;
    }

    /**********************************************************
     * Method Name   : setCurGameState
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the
     *  current game state
     * 
     * BEGIN setCurGameState
     *  set new gamestate
     * END setCurGameState
     **********************************************************/
    public void setCurGameState(int gameState)
    {
        //local constants

        //local variables

        /***start***/

        //set new gamestate
        curGameState = gameState;
    }

    /**********************************************************
     * Method Name   : getCurLevel
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method sets the
     *  new level
     * 
     * BEGIN getCurLevel
     *  return current level
     * END getCurLevel
     **********************************************************/
    public int getCurLevel() 
    {
        //local constants

        //local variables

        /***start***/

        //return current level
        return curLevel;
    }
    /**********************************************************
     * Method Name   : setCurLevel
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method sets the
     * new level
     * 
     * BEGIN setCurGameState
     *  set new level
     * END setCurLevel
     **********************************************************/
    public void setCurLevel(int level)
    {
        //local constants

        //local variables

        /***start***/

        //set new level
        curLevel = level;
    }

    /**********************************************************
     * Method Name   : getAudio
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the
     *  audio object
     * 
     * BEGIN getAudio
     *  return audio object
     * END getAudio
     **********************************************************/
    public Sound getAudio() 
    {
        //local constants

        //local variables

        /***start***/

        //return audio
        return audio;
    }

    /**********************************************************
     * Method Name   : getGameThread
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the
     *  game thread
     * 
     * BEGIN getGameThread
     *  return game thread object
     * END getGameThread
     **********************************************************/
    public Thread getGameThread()
    {
        //local constants

        //local variables

        /***start***/

        //returrn game thread
        return gameThread;
    }

    /**********************************************************
     * Method Name   : getLevelManager
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the
     *  level manager 
     * 
     * BEGIN getLevelManager
     *  return levle manager object
     * END getLevelManager
     **********************************************************/
    public LevelManager getLevelManager()
    {
        //local constants

        //local variables

        /***start***/

        //returrn level Manager
        return levelManager;
    }

    /**********************************************************
     * Method Name   : getEnemyManager
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the
     *  level manager 
     * 
     * BEGIN getEnemyManager
     *  return enemy manager object
     * END getEnemyManager
     **********************************************************/
    public EnemyManager getEnemyManager()
    {
        //local constants

        //local variables

        /***start***/

        //returrn enemy Manager
        return enemyManager;
    }

    /**********************************************************
     * Method Name   : getPlayer
     * Author         : Taylor Pitman
     * Date           : May 8th, 2023
     * Course/Section : CSC 264
     * Method Description: This method returns the
     *  level manager 
     * 
     * BEGIN getPlayer
     *  return player object
     * END getPlayer
     **********************************************************/
    public Player getPlayer()
    {
        //local constants

        //local variables

        /***start***/

        //returrn enemy Manager
        return player;
    }

}//END GamePanel

