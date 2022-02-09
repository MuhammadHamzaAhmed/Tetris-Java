package models;

import java.awt.*;

import views.TetrisBoard;

/**
 * StraightLine.java:
 * Creates a straight line tetronimo
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Point
 */
public class ReverseSkew extends Tetronimo
{
    /**
     * Creates the tetronimo and puts it in the vertical orientation
     */
    public ReverseSkew()
    {
        super.r1.setLocation( Tetronimo.SIZE, 0 );
        super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
        super.r3.setLocation( 0,  Tetronimo.SIZE);
        super.r4.setLocation( 0, Tetronimo.SIZE*2);

        super.r1.setColor(Color.MAGENTA);
        super.r2.setColor(Color.MAGENTA);
        super.r3.setColor(Color.MAGENTA);
        super.r4.setColor(Color.MAGENTA);

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
    	if(this.getXLocation()+2*SIZE>(TetrisBoard.WIDTH * Tetronimo.SIZE))
			return;
        super.curRotation++;
        Point curLoc = super.getLocation();
        super.setLocation( 0, 0 );

        if( super.curRotation % 2 == 0 )
        {
        	super.r1.setLocation( Tetronimo.SIZE, 0 );
            super.r2.setLocation( Tetronimo.SIZE, Tetronimo.SIZE );
            super.r3.setLocation( 0,  Tetronimo.SIZE);
            super.r4.setLocation( 0, Tetronimo.SIZE*2);
        }
        else
        {
        	super.r1.setLocation( 0, 0 );
            super.r2.setLocation( Tetronimo.SIZE, 0 );
            super.r3.setLocation( Tetronimo.SIZE,  Tetronimo.SIZE);
            super.r4.setLocation( Tetronimo.SIZE*2, Tetronimo.SIZE );
        }

        super.setLocation( curLoc );
    }

    /**
     * Gets the height of the tetronimo based on the orientation
     *
     * @return The height of the tetronimo
     */
    @Override
    public int getHeight()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetronimo.SIZE * 4;
        }
        else
        {
            return Tetronimo.SIZE * 2;
        }
    }

    /**
     * Gets the width of the tetronimo based on the orientation
     *
     * @return The width of the tetronimo
     */
    @Override
    public int getWidth()
    {
        if( this.curRotation % 2 == 0 )
        {
            return Tetronimo.SIZE *2;
        }
        else
        {
            return Tetronimo.SIZE * 3;
        }
    }

	@Override
	public void shiftLeft() {
    		super.setLocation( super.getXLocation() - Tetronimo.SIZE, super.getYLocation() );
		
	}

	@Override
	public void shiftRight() {
		if(super.getXLocation()+getWidth()<TetrisBoard.WIDTH*SIZE+40)
			super.setLocation( super.getXLocation() + Tetronimo.SIZE, super.getYLocation() );
		
	}
}
