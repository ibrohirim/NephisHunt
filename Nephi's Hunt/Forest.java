
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JApplet;
import javax.swing.JOptionPane;



public class Forest extends JApplet implements KeyListener {
	
	private List<Tree> trees;
	private List<Sprite> sprites;
	private int numRows;
	private int numCols;
	private Nephi nephi;
	private Bow bow;
	private Arrow arrow;
	private Beast beast;
	private Point nextPos;
	public static final int CELL_SIZE = 50;

	
	//method to instantiate the 10 tree objects
	private void createTrees() {
		trees.clear();
		for ( int i = 0; i < 10; i++) {
			Point p = findAvailableCell();
			Tree trop = new Tree(p.x, p.y);
			trees.add(trop);
			sprites.add(trop);
		}
	}
	//sets the number of rows and columns, instantiates the trees array and calls the reset method
	public void init() {
		numRows = 7;
		numCols = 7;
		trees = new ArrayList<Tree>();
		sprites = new ArrayList<Sprite>();
		reset();
		addKeyListener(this);
	}
	
	//creates the background and binding rectangle, loops to the size of the trees array
	//calling the draw method on each one
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, numRows*CELL_SIZE, numCols*CELL_SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, numRows*CELL_SIZE, numCols*CELL_SIZE);
		for (int i = 0; i < sprites.size(); i++) {
			sprites.get(i).draw(g);
		}
		requestFocusInWindow();
	}
	
	
	//chooses random numbers from 0 to number of rows, checks whether the specific point is available and returns it if it is
	private Point findAvailableCell() {
		while (true ) {
			int x = (int)(Math.random()*numCols);
			int y = (int)(Math.random()*numRows);
			Point temp = new Point(x,y);
			boolean inUse = false;
			for (Sprite s : sprites ) {
				Point spritePos = s.getLocation();
				if ( temp.equals(spritePos) ) {
					inUse = true;
				}
			}
			if (inUse == false) {
				return temp;
			}
		}
	}
	
	//method to clear all array lists and set new Positions to the objects
	public void reset() {
		trees.clear();
		sprites.clear();
		Point j = findAvailableCell();
		nephi = new Nephi(j.x, j.y);
		sprites.add(nephi);
		j = findAvailableCell();
		bow = new Bow(j.x, j.y);
		sprites.add(bow);
		j = findAvailableCell();
		arrow = new Arrow(j.x, j.y);
		sprites.add(arrow);
		j = findAvailableCell();
		beast = new Beast(j.x, j.y);
		sprites.add(beast);
		createTrees();
	}
	
	//compare values of keys pressed and the position of nephi relative to the beast
	//change value of hit and display appropriate messages for the situation
	public void shoot(int keycode) {
		if (nephi.hasBow() != true || nephi.hasArrow() != true ) {
			JOptionPane.showMessageDialog(null, "Nephi needs both the arrow and the bow to shoot!", "", JOptionPane.ERROR_MESSAGE);	
		} else if (nephi.hasBow() == true && nephi.hasArrow() == true ) {
			boolean hit = false;
			if ( keycode == KeyEvent.VK_N) {
				if (nephi.getX() == beast.getX() && beast.getY() < nephi.getY()) {
					hit = true;
				}
			}
			if ( keycode == KeyEvent.VK_S) {
				if (nephi.getX() == beast.getX() && beast.getY() > nephi.getY()) {
						hit = true;
				}
			}
			if ( keycode == KeyEvent.VK_E) {
				if (nephi.getX() < beast.getX() && beast.getY() == nephi.getY()) {
					hit = true;
				}
			}
			if ( keycode == KeyEvent.VK_W) {
				if (nephi.getX() > beast.getX() && beast.getY() == nephi.getY()) {
					hit = true;
				}
			}
			if ( hit == true ) {
				playSound("smb2_bonus_chance_start.wav");
				JOptionPane.showMessageDialog(null, "Congratulations you've slain the beast", "YAY!", JOptionPane.INFORMATION_MESSAGE);
				reset();
			}
			if ( hit != true ) {
				playSound("mb_die.wav");
				JOptionPane.showMessageDialog(null, "You missed :(", "Miss", JOptionPane.ERROR_MESSAGE);
				reset();
			}
		}	
	}
	
	// Method that get's a String variable name of a sound clip based on the action achieved
	public void playSound(String s) {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	//Method from KeyListener class, that gets the keycode value and changes the position of nephi
	//whether or not he has the arrow and the bow and calls they shoot method;
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if ( k == KeyEvent.VK_UP) {
			nextPos = nephi.getLocation();
			Point altPos = nephi.getLocation();
			nextPos.y -=1;
			if ( nextPos.y >= 0) {
				nephi.setLocation(nextPos);
			}
			for ( Tree t: trees) {
				if (nephi.overlaps(t)) {
					nephi.setLocation(altPos);
				}
			}
		}
		if ( k == KeyEvent.VK_DOWN) {
			nextPos = nephi.getLocation();
			Point altPos = nephi.getLocation();
			nextPos.y += 1;
			if (nextPos.y <= numRows - 1) {
				nephi.setLocation(nextPos);
			}
			for ( Tree t: trees) {
				if (nephi.overlaps(t)) {
					nephi.setLocation(altPos);
				}
			}
		}
		if ( k == KeyEvent.VK_RIGHT) {
			nextPos = nephi.getLocation();
			Point altPos = nephi.getLocation();
			nextPos.x += 1;
			if (nextPos.x <= numCols - 1) {
				nephi.setLocation(nextPos);
			}
			for ( Tree t: trees) {
				if (nephi.overlaps(t)) {
					nephi.setLocation(altPos);
				}
			}
		}
		if ( k == KeyEvent.VK_LEFT) {
			nextPos = nephi.getLocation();
			Point altPos = nephi.getLocation();
			nextPos.x -= 1;
			if (nextPos.x >= 0 ) {
				nephi.setLocation(nextPos);
			}
			for ( Tree t: trees) {
				if (nephi.overlaps(t)) {
					nephi.setLocation(altPos);
				}
			}
		}
		
		if (nephi.overlaps(bow)) {
			nephi.pickUpBow();
			playSound("mb_touch.wav");
			bow.setLocation(null);
		}
		
		if (nephi.overlaps(arrow)) {
			nephi.pickUpArrow();
			playSound("mb_touch.wav");
			arrow.setLocation(null);
		}
		if ( k == KeyEvent.VK_S) {
			if (nephi.hasBow() == true && nephi.hasArrow() == true){
				playSound("mb_sc.wav");
			}
			shoot(k);
		}
		if ( k == KeyEvent.VK_E) {
			if (nephi.hasBow() == true && nephi.hasArrow() == true){
				playSound("mb_sc.wav");
			}
			shoot(k);
		}
		if ( k == KeyEvent.VK_W) {
			if (nephi.hasBow() == true && nephi.hasArrow() == true){
				playSound("mb_sc.wav");
			}
			shoot(k);
		}
		if ( k == KeyEvent.VK_N) {
			if (nephi.hasBow() == true && nephi.hasArrow() == true){
				playSound("mb_sc.wav");
			}
			shoot(k);
		}
		if (k == KeyEvent.VK_R) {
			reset();
		}
		if (nephi.overlaps(beast)) {
			Point nB = findAvailableCell();
			playSound("mb_coin.wav");
			beast.setLocation(nB);
		}
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
