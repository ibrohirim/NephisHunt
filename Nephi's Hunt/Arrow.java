import javax.swing.ImageIcon;


public class Arrow extends Sprite {
	//default constructor instantiates absPos and relPos  and store the
	// to the image field
	public Arrow() {
		super();
		image = new ImageIcon("arrow.png");
	}
	//constructor copies the default constructor and then calls
	//the setLocation method to change the default values
	public Arrow(int x, int y) {
		super();
		image = new ImageIcon("arrow.png");
		setLocation(x, y);
	}
}
