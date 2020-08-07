
import javax.swing.ImageIcon;


public class Tree extends Sprite {
	
	//default constructor instantiates absPos and relPos  and store the
	// to the image field
	public Tree() {
		super();
		image = new ImageIcon("tree.png");
	}
	//constructor copies the default constructor and then calls
	//the setLocation method to change the default values
	public Tree(int x, int y) {
		super();
		image = new ImageIcon("tree.png");
		setLocation(x, y);
	}
}
