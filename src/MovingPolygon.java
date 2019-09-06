////*	NAME: ALEX SINGH	*////
////*	ID: 687195036		*////
////*	UPI: asin374		*////
////*	DATE: 28/04/16		*////
//RECCOMMEND HEIGHT/WIDTH RATIO, 5:3
	//example height: 150, width: 90
//height: 90, width: 54
	//but you can use any size, it just looks nicer with this ratio :)

import java.awt.*;
import java.awt.geom.GeneralPath;
public class MovingPolygon extends MovingShape {

	public MovingPolygon() {
		super();
	}
	
	//constructor
	public MovingPolygon(int x, int y, int w, int h,  int mw, int mh, Color bc, float pw, int pathType) {
		super(x ,y ,w, h ,mw ,mh ,bc, pw, pathType);
	}
	
	//draws an uppercase F
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(borderColor);
		g2d.setStroke(new BasicStroke(penWidth));
		int[] xx = {p.x, p.x, p.x + width/3, p.x + width/3, p.x + width/3*2, p.x + width/3*2, p.x + width/3, p.x + width/3, p.x + width, p.x + width,p.x,p.x}; //last few points are for closing the shape for when the penWidth is large.
		int[] yy = {p.y, p.y + height, p.y + height, p.y + height/5*3, p.y + height/5*3, p.y + height/5*2, p.y + height/5*2, p.y + height/5, p.y + height/5, p.y,p.y,p.y+height/100};
		
		GeneralPath q = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xx.length);
		q.moveTo(xx[0], yy[0]);
		for (int i=1;i<xx.length;i++){
			q.lineTo(xx[i],yy[i]);//for loop to get coordinates
		}
		q.closePath();
		g2d.draw(q);
		drawHandles(g);
	}

	//very important. only selects the shape if it is clicked within.
	public boolean contains(Point mousePt) {
		return ((p.x <= mousePt.x && mousePt.x <= (p.x + (width/3) + 1)
					&&	p.y <= mousePt.y && mousePt.y <= (p.y + height + 1))
				|| ((p.x + (width/3)) <= mousePt.x && mousePt.x <= (p.x + width + 1)
					&&	p.y <= mousePt.y && mousePt.y <= (p.y + (height/5) + 1))
				|| ((p.x + (width/3)) <= mousePt.x && mousePt.x <= (p.x + (width/3*2) + 1)
					&&	(p.y + (height/5*2)) <= mousePt.y && mousePt.y <= (p.y + (height/5*3) + 1))
				);
	}
	
	public void drawHandles(Graphics g) {
		if (isSelected()) {
			g.setColor(Color.black);
			g.fillRect(p.x -2, p.y-2, 4, 4);//tl
			g.fillRect(p.x -2, p.y + height -2, 4, 4);//bl
			g.fillRect(p.x + width -2, p.y-2, 4, 4);//tr
			g.fillRect(p.x + (width/3) -2, p.y + height -2, 4, 4);
			g.fillRect(p.x + (width/3) -2, p.y + (height/5*3) -2, 4, 4);
			g.fillRect(p.x + (width/3) -2, p.y + (height/5*2) -2, 4, 4);
			g.fillRect(p.x + (width/3) -2, p.y + (height/5) -2, 4, 4);
			g.fillRect(p.x + (width/3*2) -2, p.y + (height/5*3) -2, 4, 4);
			g.fillRect(p.x + (width/3*2) -2, p.y + (height/5*2) -2, 4, 4);
			g.fillRect(p.x + width -2, p.y + (height/5) -2, 4, 4);
		}
	}
}