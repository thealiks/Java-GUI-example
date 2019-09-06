////*	NAME: ALEX SINGH	*////
////*	ID: 687195036		*////
////*	UPI: asin374		*////
////*	DATE: 28/04/16		*////

/*
 *	======================================================================
 *	AnimationPanel.java : Moves shapes around on the screen according to different paths.
 *	It is the main drawing area where shapes are added and manipulated.
 *	It also contains a popup menu to clear all shapes.
 *	======================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class AnimationPanel extends JComponent implements Runnable {
	private Thread animationThread = null;	// the thread for animation
	ArrayList<MovingShape> objects = new ArrayList<MovingShape>(); //THIS IS AN ARRAYLIST FOR ALL THE SHAPES//
	private int currentShapeType, // the current shape type
		currentPath, 				// the current path type
		currentWidth = 50,			// the current width of a shape
		currentHeight = 20;			// the current height of a shape
	private float currentPenWidth = 1;	
	private Color currentBorderColor = Color.black;  // the current border colour of a shape
	private int delay = 30; 		// the current animation speed
	JPopupMenu popup;				// popup menu

	 /** Constructor of the AnimationPanel
		*/
	public AnimationPanel() {
		popup = new JPopupMenu(); //create the popup menu
		makePopupMenu();

		// add the mouse event to handle popup menu and create new shape
		addMouseListener( new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				maybeShowPopup(e);
			}

			public void mouseReleased(MouseEvent e) {
				maybeShowPopup(e);
			}

			private void maybeShowPopup(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			public void mouseClicked( MouseEvent e ) {
				if (animationThread != null) {	// if the animation has started, then
					boolean found = false;
					for(MovingShape s: objects){//ACCESS ALL SHAPES IN THE ARRAYLIST//
						if ( s.contains( e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
							found = true;
							s.setSelected( ! s.isSelected() );//SELECT ALL SHAPES WHICH ARE CLICKED WITHIN//
						}
					}
					if (! found) createNewShape(e.getX(), e.getY()); // if the mousepoint is not within a shape, then create a new one according to the mouse position
					
				}
			}
		});
	}

	/** create a new shape
	 * @param x 	the x-coordinate of the mouse position
	 * @param y	the y-coordinate of the mouse position
	 */
	protected void createNewShape(int x, int y) {
		// get the margin of the frame
		Insets insets = getInsets();
		int marginWidth = getWidth() - insets.left - insets.right;
		int marginHeight = getHeight() - insets.top - insets.bottom;
		// create a new shape dependent on all current properties and the mouse position
		switch (currentShapeType) {
			case 0: { //rectnage
				MovingShape s =  new MovingRectangle(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentPenWidth, currentPath);
				objects.add(s);
				break;
			}
			case 1: { //BEST square
				int w;
				if (currentWidth<currentHeight){w = currentWidth;}
				else{w = currentHeight;}
				MovingShape s =  new MovingSquare(x, y, w, w, marginWidth, marginHeight, currentBorderColor, currentPenWidth, currentPath);
				objects.add(s);
				break;
			}
			case 2: { //F letter
				MovingShape s =  new MovingPolygon(x, y, currentWidth, currentHeight, marginWidth, marginHeight, currentBorderColor, currentPenWidth, currentPath);
				objects.add(s);
				break;
			}
			case 3: { //Rotating Square
				int w;
				if (currentWidth<currentHeight){w = currentWidth;}
				else{w = currentHeight;}
				MovingShape s =  new MovingRotatingSquare(x, y, w, w, marginWidth, marginHeight, currentBorderColor, currentPenWidth, currentPath);
				objects.add(s);
				break;
			}
		}
	}

	/** set the current shape type
	 * @param s	the new shape type
	 */
	public void setCurrentShapeType(int s) {
		currentShapeType = s;
	}

	/** set the current path type and the path type for all currently selected shapes
	 * @param t	the new path type
	 */
	public void setCurrentPathType(int t) {
		currentPath = t;
		for(MovingShape s: objects){
			if ( s.isSelected()) {
				s.setPath(currentPath);
			}
		}
	}

	/** set the current width and the width for all currently selected shapes
	 * @param w	the new width value
	 */
	public void setCurrentWidth (int w) {
		currentWidth = w;
		for(MovingShape s: objects){ //every shape in arraylist
			if ( s.isSelected()) {
				s.setWidth(currentWidth);
			}
		}
	}

	/** set the current height and the height for all currently selected shapes
	 * @param h	the new height value
	 */
	public void setCurrentHeight(int h) {
		currentHeight = h;
		for(MovingShape s: objects){//all shapes
			if ( s.isSelected()) {
				s.setHeight(currentHeight);
			}
		}
	}

	/** set the current border colour and the border colour for all currently selected shapes
	 * @param bc	the new border colour value
	 */
	public void setCurrentBorderColor(Color bc) {
		currentBorderColor = bc;
		for(MovingShape s: objects){//arraylist
			if ( s.isSelected()) {
				s.setBorderColor(currentBorderColor);
			}
		}
	}
	
	public void setCurrentPenWidth(float w) {
		currentPenWidth = w;
		for(MovingShape s: objects){//shapes in arraylist
			if ( s.isSelected()) {
				s.setPenWidth(currentPenWidth);
			}
		}
	}

	public float getCurrentPenWidth() {
		return currentPenWidth;
	}
	
	/** get the current width
	 * @return currentWidth - the width value
	 */
	public int getCurrentWidth() {
		return currentWidth;
	}

	/** get the current height
	 * @return currentHeight - the height value
	 */
	public int getCurrentHeight() {
		return currentHeight;
	}

 	/** remove all shapes from our vector
	 */
	public void clearAllShapes() {
		objects.clear();  //remove shapes from arraylist
	}

	/**	update the painting area
	 * @param g	the graphics control
	 */
	public void update(Graphics g){
		paint(g);
	}

	/**	move and paint all shapes within the animation area
	 * @param g	the Graphics control
	 */
	public void paintComponent(Graphics g) {
		for(MovingShape s: objects){
			s.move();
			s.draw(g);
		}
	}

	/** reset the margin size of all shapes from our vector
	 */
	public void resetMarginSize() {
		Insets insets = getInsets();
		int marginWidth = getWidth() - insets.left - insets.right;
		int marginHeight = getHeight() - insets.top - insets.bottom ;
		for(MovingShape s: objects){
			s.setMarginSize(marginWidth, marginHeight);
		}
	}


	// you don't need to make any changes after this line ______________

	/** create the popup menu for our animation program
	 */
	protected void makePopupMenu() {
		JMenuItem menuItem;
	 // clear all
		menuItem = new JMenuItem("Clear All");
		menuItem.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllShapes();
			}
		});
		popup.add(menuItem);
	 }

	/** change the speed of the animation
	 * @param newValue 	the speed of the animation in ms
	 */
	public void adjustSpeed(int newValue) {
		if (animationThread != null) {
			stop();
			delay = newValue;
			start();
		}
	}

	/**	When the "start" button is pressed, start the thread
	 */
	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}

	/**	When the "stop" button is pressed, stop the thread
	 */
	public void stop() {
		if (animationThread != null) {
			animationThread = null;
		}
	}

	/** run the animation
	 */
	public void run() {
		Thread myThread = Thread.currentThread();
		while(animationThread==myThread) {
			repaint();
			pause(delay);
		}
	}

	/** Sleep for the specified amount of time
	 */
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long)milliseconds);
		} catch(InterruptedException ie) {}
	}
}