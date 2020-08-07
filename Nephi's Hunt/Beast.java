import javax.swing.ImageIcon;


public class Beast extends Sprite {
	//default constructor instantiates absPos and relPos  and store the
	// to the image field
	public Beast() {
		super();
		image = new ImageIcon("beast.png");
	}
	//constructor copies the default constructor and then calls
	//the setLocation method to change the default values
	public Beast(int x, int y) {
		super();
		image = new ImageIcon("beast.png");
		setLocation(x, y);
	}
}
