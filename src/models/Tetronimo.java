package models;

import wheelsunh.users.Rectangle;
import wheelsunh.users.ShapeGroup;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Tetronimo.java: An abstract class to model the base capaabilities of a
 * tetronimo
 *
 * @author MS
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Color
 */
public abstract class Tetronimo extends ShapeGroup {
	/**
	 * Constant to represent the size of the tetronimo
	 */
	public static final int SIZE = 20;

	protected Rectangle r1;
	protected Rectangle r2;
	protected Rectangle r3;
	protected Rectangle r4;

	protected int curRotation = 1;
	
	/**
	 * Generates the four rectangles for the tetronino and puts them on the screen,
	 * they are at the default coordinates to start
	 */
	public Tetronimo() {
		super();
		this.r1 = new Rectangle();
		this.r1.setSize(Tetronimo.SIZE, Tetronimo.SIZE);
		this.r1.setFrameColor(Color.BLUE);

		this.r2 = new Rectangle();
		this.r2.setSize(Tetronimo.SIZE, Tetronimo.SIZE);
		this.r2.setFrameColor(Color.BLACK);

		this.r3 = new Rectangle();
		this.r3.setSize(Tetronimo.SIZE, Tetronimo.SIZE);
		this.r3.setFrameColor(Color.BLACK);

		this.r4 = new Rectangle();
		this.r4.setSize(Tetronimo.SIZE, Tetronimo.SIZE);
		this.r4.setFrameColor(Color.BLACK);
	}

	/**
	 * Increments the rotation of the tetronimo, other classes need to override this
	 * to provide the full functionality
	 */
	public abstract void rotate();

	/**
	 * Shifts the tetronimo left one row
	 */
	public abstract void shiftLeft();

	/**
	 * Shifts the tetronimo right one row
	 */
	public abstract void shiftRight();

	/**
	 * Method to determine if the tetronimo has landed (INCOMPLETE)
	 *
	 * @param tetronimo The tetronimo to evaluate
	 * @return True if the tetronimo has landed (on the bottom of the board or
	 *         another tetronimo), false if it has not
	 */
	public boolean collisionWall() {
		int nextY = this.getYLocation() + this.getHeight() + SIZE;
		return nextY <= 480;
	}

	public boolean collisionTetra(Tetronimo tet) {
		return (check(r1, tet.r1) || check(r1, tet.r2) || check(r1, tet.r3) || check(r1, tet.r4)
				|| check(r2, tet.r1) || check(r2, tet.r2) || check(r2, tet.r3) || check(r2, tet.r4)
				|| check(r3, tet.r1) || check(r3, tet.r2) || check(r3, tet.r3) || check(r3, tet.r4)
				|| check(r4, tet.r1) || check(r4, tet.r2) || check(r4, tet.r3) || check(r4, tet.r4));
	}

	private boolean check(Rectangle r1, Rectangle r2) {
		return (r1.getXLocation() == r2.getXLocation()) && (r1.getYLocation() == r2.getYLocation())
				|| (r1.getXLocation() == r2.getXLocation()) && (r1.getYLocation() + SIZE == r2.getYLocation());
	}

	public void check(int y,ArrayList<Rectangle> list) {
		if (r1.getLocation().getY() == y && !list.contains(r1))
			list.add(r1);
		if (r2.getLocation().getY() == y && !list.contains(r2))
			list.add(r2);
		if (r3.getLocation().getY() == y && !list.contains(r3))
			list.add(r3);
		if (r4.getLocation().getY() == y && !list.contains(r4))
			list.add(r4);
	}

	public void removeAndMove(int y) {
		removeAndMove(r1,y);
		removeAndMove(r2,y);
		removeAndMove(r3,y);
		removeAndMove(r4,y);
	}
	
	public void removeAndMove(Rectangle rec,int y) {
		if(rec == null || rec.getYLocation() > y)
			return;
		if (rec.getLocation().getY() == 1000)
			rec = null;
		else {
			rec.setLocation((int) rec.getLocation().getX(), (int) rec.getLocation().getY() + SIZE);
		}
	}
	

}