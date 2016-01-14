package com.example.esamesoftwareengeneering.board.cells;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.drawable;
import com.example.esamesoftwareengeneering.R.id;
import com.example.esamesoftwareengeneering.board.position.Position;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Square extends Cell/*extends FrameLayout *//*implements OnClickListener*/ {
	//public class Square /*extends FrameLayout*/ {
	
	public enum Color {
		LIGHT(0),
		DARK(1);
		private int color;
		
		private Color(int color) {
			this.color = color;
		}
		
		@Override
		public String toString() {
			return "Color: " + (color == 0 ? "Light" : "Dark");
		}
	};
	public enum Selection {
		NONE,
		PIECE,
		DESTINATION
	}
	private Color color;
	private Selection selection;
	private ImageView selectedPieceImageView;
	private ImageView selectedDestinationImageView;
	//private boolean selected = false;
	//private boolean initialized = false;
	/*private int row = -1;
	private int column = -1;*/
	
	
	/*public Square(Context context) {
		super(context);
		this.context = context;
		//this.setOnClickListener(this);
	}*/
	
	public Square(Context context, AttributeSet attrs) {
		super(context, attrs);
		//this.setOnClickListener(this);
	}
	
	/*public Square(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
		this.context = context;
		//this.setOnClickListener(this);
    }*/
	
	/*public Square(Context context, int row, int column) {
		
		super(context);
		this.row = row;
		this.column = column;
		//this.color = color;
		
	}*/
	
	/*public void Init(int row, int column) {
		
		Toast.makeText(context, row + ", " + column + ", INIT", Toast.LENGTH_SHORT);
		
		this.initialized = true;
		this.row = row;
		this.column = column;
		this.color = (row + column) % 2 == 0 ? Color.WHITE : Color.BLACK;
		
		// Background
		final ImageView backgroundImageView = (ImageView) findViewById(R.id.square_background);
		if (color == Color.BLACK) {
			Toast.makeText(context, row + ", " + column + ", BLACK", Toast.LENGTH_SHORT);
			backgroundImageView.setImageResource(R.drawable.square_black);
		} else {
			Toast.makeText(context, row + ", " + column + ", WHITE", Toast.LENGTH_SHORT);
			backgroundImageView.setImageResource(R.drawable.square_white);
		}
		
		// Selected
		this.selectedImageView = (ImageView) findViewById(R.id.square_selected);
		
	}*/
	public boolean onInit(Position position) {
		
		//this.initialized = true;
		//this.position = position;
		color = (position.getRank().getRow() + position.getFile().getColumn()) % 2 == 0 ? Color.LIGHT : Color.DARK;
		selection = Selection.NONE;
		
		// Background
		final ImageView backgroundImageView = (ImageView) findViewById(R.id.square_background);
		if (color == Color.DARK) {
			Toast.makeText(context, position.getRank().getRow() + ", " + position.getFile().getColumn() + ", BLACK", Toast.LENGTH_SHORT);
			backgroundImageView.setImageResource(R.drawable.square_dark);
		} else {
			Toast.makeText(context, position.getRank().getRow() + ", " + position.getFile().getColumn() + ", WHITE", Toast.LENGTH_SHORT);
			backgroundImageView.setImageResource(R.drawable.square_light);
		}
		
		// Selected
		selectedPieceImageView = (ImageView) findViewById(R.id.square_selected_piece);
		selectedDestinationImageView = (ImageView) findViewById(R.id.square_selected_destination);
		
		return true;
		
	}
	
	/*public int GetRow() {
		
		return row;
		
	}
	
	public int GetColumn() {
		
		return column;
		
	}*/
	
	/*public Square(Context context, AttributeSet attrs, int row, int column, Color color) {
		super(context, attrs);
		this.row = row;
		this.column = column;
		this.color = color;
		
		//ImageView backgroundImageView = new ImageView(context, attrs);
		//backgroundImageView.setBackgroundColor(android.graphics.Color.BLACK);
		//backgroundImageView.setImageResource(R.drawable.square_black);
		//this.addView(backgroundImageView);
		
		//this.addView(new Piece(context, attrs, Players.WHITE, Pieces.KING));
	}*/
	

    /*@Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }*/

	/*@Override
	public void onClick(View v) {
		
		if (this.selectedImageView.getVisibility() != View.VISIBLE) {
			this.selectedImageView.setVisibility(View.VISIBLE);
		} else {
			this.selectedImageView.setVisibility(View.INVISIBLE);
		}
		
	}*/
    
    /*@Override
    public void onSelected(boolean selected) {
    	
    	if (selected) {
			this.selectedPieceImageView.setVisibility(View.VISIBLE);
		} else {
			this.selectedPieceImageView.setVisibility(View.INVISIBLE);
		}
    	
    }*/
	
	public Selection getSelection() {
		return selection;
	}
	
	public void setSelection(Selection selection) {
		this.selection = selection;
		switch (selection) {
		case NONE:
			selectedPieceImageView.setVisibility(INVISIBLE);
			selectedDestinationImageView.setVisibility(INVISIBLE);
			break;
		case PIECE:
			selectedPieceImageView.setVisibility(VISIBLE);
			selectedDestinationImageView.setVisibility(INVISIBLE);
			break;
		case DESTINATION:
			selectedPieceImageView.setVisibility(INVISIBLE);
			selectedDestinationImageView.setVisibility(VISIBLE);
			break;
		}
	}
	
}