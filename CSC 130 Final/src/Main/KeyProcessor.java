/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' '){		
			Main.trigger = " ";
			Main.wKey = false;
			Main.aKey = false;
			Main.sKey = false;
			Main.dKey = false;
			return;
			
		}
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)	
				return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 'w':
			//move up 
			//Main.trigger = "w is triggered";
			Main.wKey = true;
			break;
			
		case 'a':
			//move left
			//Main.trigger = "a is triggered";
			Main.aKey = true;
			break;
			
		case 's':
			//move down
			//Main.trigger = "s is triggered";
			Main.sKey = true;
			break;
			
		case 'd': 
			//move right 
			//Main.trigger = "d is triggered";
			Main.dKey = true;
			break;
			
		case '$':
		//	Main.trigger = "space bar is triggered";
			Main.spaceBar = true;
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
			
		}
		
	}
	
	 public static void resetKey() {
	        Main.wKey = false;
	        Main.aKey = false;
	        Main.sKey = false;
	        Main.dKey = false;
	        Main.spaceBar = false;
	    } 
	}

