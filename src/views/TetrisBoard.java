package views;

import controllers.TetrisController;
import models.Tetronimo;
import wheelsunh.users.*;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.util.ArrayList;

/**
 * TetrisBoard.java: Class to model the tetris board
 *
 * @author Professor Rossi
 * @version 1.0 July 24, 2020
 *
 * @see java.awt.Color
 * @see java.awt.event.KeyListener
 * @see java.awt.event.KeyEvent
 */
public class TetrisBoard {
	/**
	 * Constant to represent the width of the board
	 */
	public static final int WIDTH = 10;

	/**
	 * Constant to represnet the height of the board
	 */
	public static final int HEIGHT = 24;

	private final TetrisController controller;
	private ArrayList<Tetronimo> list;
	private Rectangle[][] playingField;
	private TextBox statusText = new TextBox("Score:- 0");
	private TextBox previewText = new TextBox("Next Block");
	private Tetronimo nextShape;

	/**
	 * Constructor to initialize the board
	 *
	 * @param frame The wheelsunh frame (so we can add this class as a key listener
	 *              for the frame)
	 */
	public TetrisBoard(Frame frame) {
		this.controller = new TetrisController(this);
		list = new ArrayList<Tetronimo>();
		frame.addKeyListener(controller);
		this.buildBoard();
		nextShape = controller.getNextTetromino();
		this.controller.run();
	}

	/**
	 * Builds the playing field for tetris
	 */
	private void buildBoard() {
		this.playingField = new Rectangle[WIDTH][HEIGHT];
		for (int i = 0; i < TetrisBoard.WIDTH; i++) {
			for (int j = 0; j < TetrisBoard.HEIGHT; j++) {
				this.playingField[i][j] = new Rectangle();
				this.playingField[i][j].setLocation(i * 20 + 40, j * 20);
				this.playingField[i][j].setSize(Tetronimo.SIZE, Tetronimo.SIZE);
				this.playingField[i][j].setColor(Color.WHITE);
				this.playingField[i][j].setFrameColor(Color.BLACK);
			}
		}

		statusText.setFrameColor(Color.WHITE);
		statusText.setLocation(280, 10);

		previewText.setFrameColor(Color.WHITE);
		previewText.setLocation(280, 45);
		
	}

	/**
	 * Getter method for the array representing the playing field, not used yet but
	 * will be needed by the controller later
	 *
	 * @return The playing field
	 */
	public Rectangle[][] getPlayingField() {
		return playingField;
	}

	public ArrayList<Tetronimo> getList() {
		return list;
	}

	public void setPlayingField(Rectangle[][] playingField) {
		this.playingField = playingField;
	}

	public void setStatusText(String statusText) {
		this.statusText.setText(statusText);;
	}

	public Tetronimo getNextShape() {
		Tetronimo rec = nextShape;
		rec.setLocation(40 + (5 * Tetronimo.SIZE), 0);
		setNextShape();
		return rec;
	}

	public void setNextShape() {
		nextShape = controller.getNextTetromino();
		nextShape.setLocation(40+(12*Tetronimo.SIZE),80);
	}
	
	public void setNextShapeNull() {
		this.nextShape = null;
	}

	public void setGameOver() {
		this.previewText.setText("Game Over");
	}
	
	

	
}