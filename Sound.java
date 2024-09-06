
/**********************************************************
 * Program Name   : Sound
 * Author         : Taylor Pitman
 * Date           : May 8th, 2023
 * Course/Section : CSC 264
 * Program Description: This class plays, loops, and stops the
 *  music and sound effect for the game.
 **********************************************************/

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound 
{
    //class constants
    public final int BG_MUSIC       = 0;
    public final int JUMP_EFFECT    = 1;
    public final int LOSE_LIFE      = 2;
    public final int GAME_OVER      = 3;
    public final int FIRE_BALL      = 4;
    public final int COIN           = 5;
    public final int LEVEL_DONE     = 6;

    //class variables
    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound()
    {
        //local constants

        //local variables

        /***Start***/

        //instantiate array with files
        soundURL[0] = getClass().getResource("gameBackGround.wav");
        soundURL[1] = getClass().getResource("jump.wav");
        soundURL[2] = getClass().getResource("loseLife.wav");
        soundURL[3] = getClass().getResource("gameOver.wav");
        soundURL[4] = getClass().getResource("fireBall.wav");
        soundURL[5] = getClass().getResource("coin.wav");
        soundURL[6] = getClass().getResource("levelComplete.wav");
    }

    public void setFile(int index)
    {
        //local constants

        //local variables
        AudioInputStream audioStream;

        /***Start***/

        //TRY
        try
        {
            //instantiate audio stream with file
            audioStream = AudioSystem.getAudioInputStream(soundURL[index]);

            //instantiate clip
            clip = AudioSystem.getClip();

            //open clip with audo stream
            clip.open(audioStream);

            //IF(index is jump)
            if(index == JUMP_EFFECT)
            {
                //call adjust volume
                adjustJump();

            }//END IF
        }
        //CATCH(exception)
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void adjustJump()
    {
        //local constants

        //local variables
        FloatControl gainControl;

        /****start****/

        //decrease volume
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15.0f); 
    }

    public void play ()
    {
        //local constants

        //local variables

        /***Start***/

        //start clip
        clip.start();
    }

    public void loop()
    {
        //local constants

        //local variables

        /***Start***/

        //loop sound continuosly
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        //local constants

        //local variables

        /***Start***/

        //stop sound
        clip.stop();
    }
    
}
