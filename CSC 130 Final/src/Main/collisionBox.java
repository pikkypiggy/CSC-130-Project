package Main;

public class collisionBox {
	
	//these are the coordinates for each sprite
    int x1;
	int y1;
	int x2;
	int y2;

    public collisionBox(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void update(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    
  /* Encorporate From Module Notes
   *  There are four conditions where we know we did not collide (because it would be impossible):
    	box1.X1 > box2.X2
    	box1.X2 < box2.X1
    	box1.Y1 > box2.Y2
    	box1.Y2 < box2.Y1
  */
    
    public boolean intersects(collisionBox other) {
        // Check if the two boxes do not overlap
        if (this.x1 > other.x2 || this.x2 < other.x1 || this.y1 > other.y2 || this.y2 < other.y1) {
            return false; // No collision
        }
        return true; // Collision detected
    }
    
    
    public int getCenterX() {
        return (x1 + x2) / 2;
    }

    public int getCenterY() {
        return (y1 + y2) / 2;
    }
}
