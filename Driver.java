
import javax.swing.*;

public class Driver
{
//-----------------------------------------------------------------
// Creates and displays the application frame.
//-----------------------------------------------------------------

	public static void main(String[] args)
	{
		//local constants

		//local variables

		/***start***/

		//create jframe
		GamePanel game = new GamePanel();

		JFrame frame = new JFrame("Mario Game");
		frame.getContentPane().add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);

        //start thread
        game.startThread();
	}
}
