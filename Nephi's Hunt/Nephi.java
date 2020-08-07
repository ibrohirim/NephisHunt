import javax.swing.ImageIcon;


public class Nephi extends Sprite {
	
	private boolean hasBow;
	private boolean hasArrow;
	private ImageIcon hasBowIcon;
	private ImageIcon hasArrowIcon;
	private ImageIcon hasBowAndArrowIcon;
	private ImageIcon regularIcon;
	
	
	
	//default constructor instantiates absPos and relPos  and store the
	// to the image field
	public Nephi() {
		super();
		hasBow = false;
		hasArrow = false;
		hasBowIcon = new ImageIcon("nephi_bow.png");
		hasArrowIcon = new ImageIcon("nephi_arrow.png");
		hasBowAndArrowIcon = new ImageIcon("nephi_bow_arrow.png");
		regularIcon = new ImageIcon("nephi.png");
		image = regularIcon;
	}
	//constructor copies the default constructor and then calls
	//the setLocation method to change the default values
	public Nephi(int x, int y) {
		this();
		setLocation(x, y);
	}
	//return value of hasArrow
	public boolean hasArrow(){
		return hasArrow;
	}
	//returns value of hasBow
	public boolean hasBow() {
		return hasBow;
	}
	//method that sets hasArrow to true and compares whether nephi has the bow or not
	//sets the appropriate image
	public void pickUpArrow() {
		hasArrow = true;
		if ( hasBow == true ) {
			image = hasBowAndArrowIcon;
		} else {
			image = hasArrowIcon;
		}
	}
	//method that sets hasBow to true and compares whether nephi has the arrow or not
	//sets the appropriate image
	public void pickUpBow() {
		hasBow = true;
		if ( hasArrow == true) {
			image = hasBowAndArrowIcon;
		} else {
			image = hasBowIcon;
		}
	}
}
