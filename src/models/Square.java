package models;

import java.awt.*;

import views.TetrisBoard;

/**
 * StraightLine.java:
 * Creates a straight line tetronimo
 *
 * @author MS
 * @version 1.0 July 24, 2020
 *
 * @see Point
 */
public class Square extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public Square()
    {
        super.r1.setLocation(0, 0);
        super.r2.setLocation(0, Tetronimo.SIZE);
        super.r3.setLocation(Tetronimo.SIZE, 0);
        super.r4.setLocation(Tetronimo.SIZE, Tetronimo.SIZE);
        
        super.r1.setColor(Color.CYAN);
        super.r2.setColor(Color.CYAN);
        super.r3.setColor(Color.CYAN);
        super.r4.setColor(Color.CYAN);

        super.r1.setFrameColor(Color.BLACK);
        super.r2.setFrameColor(Color.BLACK);
        super.r3.setFrameColor(Color.BLACK);
        super.r4.setFrameColor(Color.BLACK);

        super.add( r1 );
        super.add( r2 );
        super.add( r3 );
        super.add( r4 );
    }

    /**
     * Rotates the tetronimo
     */
    @Override
    public void rotate()
    {
        // square don't need rotation
    }

    /**
     * Gets the height of the tetronimo based on the orientation
     *
     * @return The height of the tetronimo
     */
    @Override
    public int getHeight()
    {
    	return Tetronimo.SIZE * 2;
    }

    /**
     * Gets the width of the tetronimo based on the orientation
     *
     * @return The width of the tetronimo
     */
    @Override
    public int getWidth()
    {
    	return Tetronimo.SIZE * 2;
    }

	@Override
	public void shiftLeft() {
		if(super.getXLocation()> 0)
    		super.setLocation( super.getXLocation() - Tetronimo.SIZE, super.getYLocation() );
		
	}

	@Override
	public void shiftRight() {
		if(super.getXLocation()<TetrisBoard.WIDTH*SIZE)
			super.setLocation( super.getXLocation() + Tetronimo.SIZE, super.getYLocation() );
	}
	
	
	
}
