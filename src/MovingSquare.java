////*	NAME: ALEX SINGH	*////
////*	ID: 687195036		*////
////*	UPI: asin374		*////
////*	DATE: 28/04/16		*////

import java.awt.*;
public class MovingSquare extends MovingRectangle {

	public MovingSquare() {
		super();
	}

	//*it is a consTruckTor*//
	public MovingSquare(int x, int y, int w, int h, int mw, int mh, Color bc, float pw, int pathType) {
		super(x ,y, w, h ,mw ,mh ,bc, pw, pathType);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(borderColor);
		g2d.setStroke(new BasicStroke(penWidth));
		g2d.drawRect(p.x, p.y, width, height);	//set both height and width to be the min value
		drawHandles(g);
	}

	public void setWidth(int w){
		super.setHeight(w);	//changing the width changes both width and height for the movingRectangle
		super.setWidth(w);
	}
	
	public void setHeight(int h){
		super.setHeight(h);	//changing the height changes both width and height for the movingRectangle
		super.setWidth(h);
	}
}