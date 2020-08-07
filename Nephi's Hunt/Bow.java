import javax.swing.ImageIcon;


public class Bow extends Sprite {
	//default constructor instantiates absPos and relPos  and store the
	// to the image field
	public Bow() {
		super();
		image = new ImageIcon("Bow.png");
	}
	//constructor copies the default constructor and then calls
	//the setLocation method to change the default values
	public Bow(int x, int y) {
		super();
		image = new ImageIcon("Bow.png");
		setLocation(x, y);
	}
}
