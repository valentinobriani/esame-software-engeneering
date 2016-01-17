package com.example.esamesoftwareengeneering.game.board.cells;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.drawable;
import com.example.esamesoftwareengeneering.R.id;
import com.example.esamesoftwareengeneering.game.board.position.Position;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Square extends Cell {
	
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
	
	public Square(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public boolean onInit(Position position) {
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