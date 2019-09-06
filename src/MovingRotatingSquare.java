////*	NAME: ALEX SINGH	*////
////*	ID: 687195036		*////
////*	UPI: asin374		*////
////*	DATE: 28/04/16		*////

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
public class MovingRotatingSquare extends MovingSquare {

	public MovingRotatingSquare() {
		super();
	}
	
	/*constructor*/
	public MovingRotatingSquare(int x, int y, int w, int h, int mw, int mh, Color bc, float pw, int pathType) {
		super(x, y, w, h, mw, mh, bc, pw, pathType);
	}

	/*eh sorry, this is the best i can do.*/
	public void draw(Graphics g) {
		int n;	//n = new
		if (width<height){n = width;}	//check which value is smaller
		else{n = height;}	//set n to be the smaller one
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(borderColor);
		g2d.setStroke(new BasicStroke(penWidth));
		AffineTransform at = new AffineTransform(); //new instance of affine transform
		at.rotate(Math.toRadians(22.5),p.x+n/2, p.y+n/2);	//this rotates a shape
		Shape s = new Rectangle(p.x,p.y,n, n);		//create a square
		for (int i=0; i<4;i++){		//draw and rotate 4 times.
			g2d.draw(s);
			s = at.createTransformedShape(s);
		}
		drawHandles(g);
	}
}