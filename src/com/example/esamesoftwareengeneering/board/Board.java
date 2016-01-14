package com.example.esamesoftwareengeneering.board;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.GridView;

//@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//public class Board extends GridLayout {
public class Board extends GridView {
	
	public final static int RANKS = 8;
	public final static int FILES = 8;
	public final static int LABEL_ROWS = 2;
	public final static int LABEL_COLUMNS = 2;
	public final static int ROWS = RANKS + LABEL_ROWS;
	public final static int COLUMNS = FILES + LABEL_COLUMNS;
	public final static int FIRST_RANK_ROW = LABEL_ROWS / 2;
	public final static int LAST_RANK_ROW = ROWS - (LABEL_ROWS / 2) - 1;
	public final static int FIRST_FILE_COLUMN = LABEL_COLUMNS / 2;
	public final static int LAST_FILE_COLUMN = COLUMNS - (LABEL_COLUMNS / 2) - 1;
	//private Square[][] squares;
	
	
	public Board(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		/*squares = new Square[ROWS][COLUMNS];
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				Square.Color color = ((row * COLUMNS + column) % 2 == 1 ? Square.Color.BLACK : Square.Color.WHITE);
				squares[row][column] = new Square(context, attrs, row + 1, column + 1, color);
				//this.addView(squares[row][column]);
			}
		}*/
		
	}

}
