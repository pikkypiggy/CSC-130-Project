package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import FileIO.EZFileRead;


import Data.Vector2D;
import Data.spriteInfo;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(255, 255, 255);
	public static boolean isImageDrawn = false;
	public static stopWatchX timer = new stopWatchX(50);
	
	public static Queue<Vector2D> vecs1 = new LinkedList<>();
	public static Queue<Vector2D> vecs2 = new LinkedList<>();
	
	public static Vector2D currentVec = new Vector2D(550, 333);
	
	public static ArrayList<collisionBox> collisionBoxes = new ArrayList<>();
	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static ArrayList<spriteInfo> Dsprites = new ArrayList<>();
	public static ArrayList<spriteInfo> Asprites = new ArrayList<>();
	public static ArrayList<spriteInfo> Ssprites = new ArrayList<>();
	public static ArrayList<spriteInfo> Wsprites = new ArrayList<>();
	public static int currentSpriteIndex = 0;
	
	public static collisionBox zynn ;
	public static collisionBox mattBox ;
	public static collisionBox chicken;
	public static collisionBox rightBorder;
	public static collisionBox leftBorder;
	public static collisionBox topBorder;
	public static collisionBox bottomBorder;
	
	public static HashMap<String, String> map = new HashMap<>();

	public static String trigger = "";
	public static boolean wKey = false;
    public static boolean aKey = false;
    public static boolean sKey = false;
    public static boolean dKey = false;
    public static boolean spaceBar = false;
	 	

	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	public static void loadLinesFromFile() {
	    EZFileRead ezr = new EZFileRead("DialogueLines.txt");
	    for (int i = 0; i < ezr.getNumLines(); i++) {
	        String line = ezr.getLine(i);
	        StringTokenizer st = new StringTokenizer(line, "*");
	     
	        if (st.countTokens() == 2) { // Ensure we have both a key and a value
	            String key = st.nextToken();
	            String value = st.nextToken();
	            map.put(key, value); // Add Key-Value pair to the HashMap
	        }
	    }
	}

	public static void start() {
		
	    loadLinesFromFile();

	    // Initialize sprites and their position  
	    for (int i = 0; i < 4; i++) { 
	        // Create a new position for each sprite
	        Vector2D dSpritePos = new Vector2D(currentVec.getX(), currentVec.getY());
	        Vector2D aSpritePos = new Vector2D(currentVec.getX(), currentVec.getY());
	        Vector2D sSpritePos = new Vector2D(currentVec.getX(), currentVec.getY());
	        Vector2D wSpritePos = new Vector2D(currentVec.getX(), currentVec.getY());
	        
	        // Add sprites to the vector as well as incrementing to get full animation
	        Dsprites.add(new spriteInfo(dSpritePos, "DmattWalk" + i));
	        Asprites.add(new spriteInfo(aSpritePos, "AmattWalk" + i));
	        Ssprites.add(new spriteInfo(sSpritePos, "SmattWalk" + i));
	        Wsprites.add(new spriteInfo(wSpritePos, "WmattWalk" + i));
	    }
	   
	    
	   //creates the borders for the sprites collision box
	    
	    
	   mattBox = new collisionBox(currentVec.getX(), currentVec.getY(), 550, 333);
	   zynn = new collisionBox(180, 130, 170, 90);
	   chicken = new collisionBox(1085,548,1045,460);
	   rightBorder = new collisionBox (1300, 0, 1500, 700);
	   leftBorder = new collisionBox (20,0,0, 700);
	   topBorder = new collisionBox (38,-80,1241,-90);
	   bottomBorder = new collisionBox (38, 720, 1241, 720);
	   
	   collisionBoxes.add(mattBox);
	   collisionBoxes.add(zynn);
	   collisionBoxes.add(chicken);
	   collisionBoxes.add(rightBorder);
	   collisionBoxes.add(leftBorder);
	   collisionBoxes.add(topBorder);
	   collisionBoxes.add(bottomBorder);
		
	   /*
	    // Debugging sprite initialization
	    System.out.println("Initialized Dsprites:");
	    for (spriteInfo sprite : Dsprites) {
	        System.out.println(sprite.getTag());
	    }

	    System.out.println("Initialized Asprites:");
	    for (spriteInfo sprite : Asprites) {
	        System.out.println(sprite.getTag());
	    }
	    
	    System.out.println("Initialized Ssprites:");
	    for (spriteInfo sprite : Ssprites) {
	        System.out.println(sprite.getTag());
	    }
	    System.out.println("Initialized Wsprites:");
	    for (spriteInfo sprite : Wsprites) {
	        System.out.println(sprite.getTag());
	    } */
	}
	
	
	public static void update(Control ctrl) {
		
		//adds the sprites/images to the screen 
		ctrl.addSpriteToFrontBuffer(0, 0, "Ground");
		ctrl.addSpriteToFrontBuffer(100, 100, "Zynn");
		ctrl.addSpriteToFrontBuffer(1000, 500, "Chicken");
		ctrl.addSpriteToFrontBuffer(0, 0, "Border");
		
		ctrl.drawString(250, 340, trigger, Color.WHITE);
		
	    // Draw sprite
	    if (dKey ) {
	        ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), Dsprites.get(currentSpriteIndex).getTag());
	    } else if (aKey) {
	        ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), Asprites.get(currentSpriteIndex).getTag());
	    } else if (sKey) {
		    ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), Ssprites.get(currentSpriteIndex).getTag());
	    } else if(wKey) {
			    ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), Wsprites.get(currentSpriteIndex).getTag());
	    } else {
	       //when sprite is Idle
	        ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), "matt"); 
	    } 
		
	   
		
	    if (timer.isTimeUp()) {
	        int newX = currentVec.getX();
	        int newY = currentVec.getY();

	        //cycles through the animation 
	        currentSpriteIndex = (currentSpriteIndex + 1) % 4;
	        
	        //moves the sprite depending on the key pressed
	        if (dKey) {
	        	trigger = "";
	            newX += 10;
	            Main.wKey = false;
		        Main.aKey = false;
		        Main.sKey = false;
		        spaceBar = false;
	        } else if (aKey) {
	        	trigger = "";
	            newX -= 10;
	            Main.wKey = false;
		        Main.sKey = false;
		        Main.dKey = false;
		        spaceBar = false;
	        } else if (sKey) {
	        	trigger = "";
	            newY += 10;
	            Main.wKey = false;
		        Main.aKey = false;
		        Main.dKey = false;
		        spaceBar = false;
	        } else if (wKey) {
	        	trigger = "";
	            newY -= 10;
		        Main.aKey = false;
		        Main.sKey = false;
		        Main.dKey = false;
		        spaceBar = false;
	        }

	       
	        
	        //Update mattBox to the new position
	        mattBox.update(newX, newY, newX + 128, newY + 128);

	        //Check for collision and only move if no collision occurs
	        boolean collisionDetected = false;

	        for (collisionBox box : collisionBoxes) {
	            if (mattBox.intersects(box) && box != mattBox) {
	                collisionDetected = true;
	                break;
	            }
	        }

	        if (!collisionDetected) {
	            currentVec.setX(newX);
	            currentVec.setY(newY);
	        } else {
	            mattBox.update(currentVec.getX(), currentVec.getY(), currentVec.getX() + 128, currentVec.getY() + 128);
	        }

	        
	        timer.resetWatch();
	    }
	    
	    if (spaceBar ) {
	        boolean isExamined = false;

	        // Check proximity to Zynn
	        if (mattBox.x2 > zynn.x1 - 50 && mattBox.x1 < zynn.x2 + 50 &&
	            mattBox.y2 > zynn.y1 - 50 && mattBox.y1 < zynn.y2 + 50)
	            if (spaceBar) {
	                trigger = "You examine Zynn: "
	                		+ "hmmm...'Contains Nicotine' and Coffee flavored"
	                		+ " Strange.";
	                isExamined = true;
	            } 
	    	    

	        // Check proximity to Chicken
	        if (mattBox.x2 > chicken.x1 - 30 && mattBox.x1 < chicken.x2 + 30 &&
	            mattBox.y2 > chicken.y1 - 30 && mattBox.y1 < chicken.y2 + 30) {
	            if (spaceBar) {
	                trigger = "You examine the Chicken: Looks Delicious. I'm Starving! I haven't eaten in days";
	                isExamined = true;
	            } else {
		            // Reset the trigger when the space bar is not pressed
		            trigger = "";
		            isExamined = false;
		        }
	        } 

	     
	    } 
	    
	}
}	

	
