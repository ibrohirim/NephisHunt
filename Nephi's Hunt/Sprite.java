import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;


public abstract class Sprite {
	
	private Point absolutePosition;
	private Point relativePosition;
	protected ImageIcon image;
	
	//default constructor instantiates absPos and relPos  and store the
	// to the image field
	public Sprite() {
		absolutePosition = new Point();
		relativePosition = new Point();
	}
	//constructor copies the default constructor and then calls
	//the setLocation method to change the default values
	public Sprite(int x, int y) {
		this();
		setLocation(x, y);
	}
	//method to set the location of the trees to ones instantiated in the forest class
	public void setLocation(int x, int y) {
		relativePosition.x = x;
		relativePosition.y = y;
		absolutePosition.x = relativePosition.x*Forest.CELL_SIZE + (Forest.CELL_SIZE - image.getIconWidth())/2;
		absolutePosition.y = relativePosition.y*Forest.CELL_SIZE + (Forest.CELL_SIZE - image.getIconWidth())/2;
	}
	
	//compares the point position passed on if its null it sets the 
	//aPos and relPos to null as well, otherwise it sets to the values passed on
	public void setLocation(Point p) {
		if (p != null) {
			setLocation(p.x, p.y);
		} else {
			absolutePosition = null;
			relativePosition = null;
		}
	}
	
	// if the absolutePostion has a value it displays the tree image on the screen
	public void draw(Graphics g) {
		if (absolutePosition == null) {
			
		} else {
			image.paintIcon(null, g, absolutePosition.x, absolutePosition.y);
		}
	}
	
	//Returns a Point location of the object, if the location is null returns 10,10 instead to avoid null Pointers
	public Point getLocation() {
		if ( relativePosition == null ) {
			return new Point(10, 10);
		}
		return new Point(relativePosition);
	}
	
	// returns the relative x coordinate of the object
	public int getX() {
		return relativePosition.x;
	}
	
	//returns the relative y coordinate of the object 
	public int getY() {
		return relativePosition.y;
	}
	
	
	//checks whether the two objects are on the same Point position 
	public boolean overlaps(Sprite other) {
		if (this.relativePosition != null && other.relativePosition != null) {
			return ( this.relativePosition.equals(other.relativePosition));
		}
		return false;
	}
}

