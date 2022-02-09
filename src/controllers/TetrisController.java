package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import models.L;
import models.ReverseSkew;
import models.Skew;
import models.Square;
import models.StraightLine;
import models.T;
import models.Tetronimo;
import views.TetrisBoard;
import wheelsunh.users.Rectangle;
import wheelsunh.users.Utilities;

/**
 * TetrisController.java: Class to hold all of the game logic for tetris
 *
 * @author MS
 * @version 1.0 July 24, 2020
 */
public class TetrisController implements KeyListener {
	private final TetrisBoard TETRIS_BOARD;
	private Tetronimo tetra;
	private int score = 0;

	/**
	 * Constructor to take in a tetris board so the controller and the board can
	 * Communicate
	 *
	 * @param tetrisBoard A tetris board instance
	 */
	public TetrisController(TetrisBoard tetrisBoard) {
		this.TETRIS_BOARD = tetrisBoard;
	}

	/**
	 * Randomly chooses the next tetronimo and returns it (INCOMPLETE)
	 *
	 * @return The next tetronimo to be played
	 */
	public Tetronimo getNextTetromino() {
		Random rand = new Random();
		Tetronimo tetronimo = null;
		switch(rand.nextInt(6)) {
		case 0:
			tetronimo = new StraightLine();
			break;
		case 1:
			tetronimo = new Square();
			break;
		case 2:
			tetronimo = new L();
			break;
		case 3:
			tetronimo = new T();
			break;
		case 4:
			tetronimo = new Skew();
			break;
		case 5:
			tetronimo = new ReverseSkew();
		}
		return tetronimo;
	}

	public boolean gameOver(ArrayList<Tetronimo> list) {
		for (Tetronimo tet : list) {
			if (tet.getYLocation() == 0) {
				TETRIS_BOARD.setGameOver();
				return true;
			}
		}
		return false;

	}

	/**
	 * Starts gameplay and is responsible for keeping the game going (INCOMPLETE)
	 */
	public boolean notCollied() {
		for (Tetronimo tet : TETRIS_BOARD.getList()) {
			if (tetra.collisionTetra(tet)) {
				return false;
			}
		}
		return tetra.collisionWall();
	}

	public void run() {
		while (!gameOver(TETRIS_BOARD.getList())) {
			tetra = TETRIS_BOARD.getNextShape();
			tetra.rotate();
			while (notCollied()) {
				tetra.setLocation(tetra.getXLocation(), tetra.getYLocation() + Tetronimo.SIZE);
				Utilities.sleep(500);
			}
			TETRIS_BOARD.getList().add(tetra);
			int y=0;
			int count=0;
			for(int i=0;i<24;i++) {
				ArrayList<Rectangle> removeList = new ArrayList<Rectangle>();
				for(Tetronimo tet:TETRIS_BOARD.getList()) {
					tet.check(y,removeList);
				}
				if(removeList.size() >= TetrisBoard.WIDTH) {
					for(Rectangle rec:removeList) {
						rec.setLocation(1000,1000);
					}
					for(Tetronimo tet:TETRIS_BOARD.getList()) {
						tet.removeAndMove(y);
					}
					i--;
					count++;
				}
				else
					y += Tetronimo.SIZE;
			}
			if(count == 4) 
				score +=800;
			else 
				score +=100*count;
			TETRIS_BOARD.setStatusText("Score: "+score);
			tetra = null;
		}
	}

	public TetrisBoard getTETRIS_BOARD() {
		return TETRIS_BOARD;
	}

	/**
	 * This method is not used in this program
	 * 
	 * @param e The key event
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// not in use
	}

	/**
	 * Handles the key events by the user (INCOMPLETE)
	 * 
	 * @param e The key event
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (this.tetra == null) {
			return;
		}
		switch (key) {
		case 38:
			this.tetra.rotate();
			break;
		case 37:
			if (this.tetra.getXLocation() - Tetronimo.SIZE >= 40)
				this.tetra.shiftLeft();
			if(!notCollied())
				this.tetra.shiftRight();
			break;
		case 39:
			if(tetra instanceof Square) {
				if ((this.tetra.getXLocation() + this.tetra.getWidth()) < ((TetrisBoard.WIDTH * Tetronimo.SIZE) + 80))
					this.tetra.shiftRight();
			}
			else {
					this.tetra.shiftRight();
			}
			if(!notCollied())
				this.tetra.shiftLeft();
			break;
		case 40:
			if (notCollied() && tetra.getXLocation()+tetra.getHeight()+Tetronimo.SIZE <=480)
				tetra.setLocation(tetra.getXLocation(), tetra.getYLocation() + Tetronimo.SIZE);
			break;
		}
	}

	/**
	 * This method is not used in this program
	 * 
	 * @param e The key event
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// not in use
	}

}
