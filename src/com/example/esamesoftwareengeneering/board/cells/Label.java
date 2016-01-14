package com.example.esamesoftwareengeneering.board.cells;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.Board;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.exceptions.InvalidOperationException;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class Label extends Cell/*extends FrameLayout */ {
	
	private char labelText;
	
	
	/*public Label(Context context) {
		super(context);
	}*/
	
	public Label(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/*public Label(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }*/
	
	/*public void init(char labelText) {
		this.labelText = labelText;
		
		// Text Label
		final ImageView textLabelImageView = (ImageView) findViewById(R.id.label_text);
		switch (labelText) {
		case ' ':
			textLabelImageView.setImageResource(R.drawable.label_blank);
			break;
		case 'a':
			textLabelImageView.setImageResource(R.drawable.label_a);
			break;
		case 'b':
			textLabelImageView.setImageResource(R.drawable.label_b);
			break;
		case 'c':
			textLabelImageView.setImageResource(R.drawable.label_c);
			break;
		case 'd':
			textLabelImageView.setImageResource(R.drawable.label_d);
			break;
		case 'e':
			textLabelImageView.setImageResource(R.drawable.label_e);
			break;
		case 'f':
			textLabelImageView.setImageResource(R.drawable.label_f);
			break;
		case 'g':
			textLabelImageView.setImageResource(R.drawable.label_g);
			break;
		case 'h':
			textLabelImageView.setImageResource(R.drawable.label_h);
			break;
		case '1':
			textLabelImageView.setImageResource(R.drawable.label_1);
			break;
		case '2':
			textLabelImageView.setImageResource(R.drawable.label_2);
			break;
		case '3':
			textLabelImageView.setImageResource(R.drawable.label_3);
			break;
		case '4':
			textLabelImageView.setImageResource(R.drawable.label_4);
			break;
		case '5':
			textLabelImageView.setImageResource(R.drawable.label_5);
			break;
		case '6':
			textLabelImageView.setImageResource(R.drawable.label_6);
			break;
		case '7':
			textLabelImageView.setImageResource(R.drawable.label_7);
			break;
		case '8':
			textLabelImageView.setImageResource(R.drawable.label_8);
			break;
		default:
			Log.e("Label", "Error: invalid label text");
			break;				
		}
	}*/
	public boolean onInit(Position position) {
		labelText = ' ';
		
		if ((position.getRank().getRow() < Board.FIRST_RANK_ROW ||
				position.getRank().getRow() > Board.LAST_RANK_ROW) &&
				!(position.getFile().getColumn() < Board.FIRST_FILE_COLUMN ||
						position.getFile().getColumn() > Board.LAST_FILE_COLUMN)){
			labelText = position.getFile().getFileChar();
		} else if ((position.getFile().getColumn() < Board.FIRST_FILE_COLUMN ||
				position.getFile().getColumn() > Board.LAST_FILE_COLUMN) &&
				!(position.getRank().getRow() < Board.FIRST_RANK_ROW ||
						position.getRank().getRow() > Board.LAST_RANK_ROW)){
			labelText = position.getRank().getRankChar();
		}/* else {
			Log.e("Label", "Error: position not valid for a label of the board");
		}*/
		
		// Text Label
		final ImageView textLabelImageView = (ImageView) findViewById(R.id.label_text);
		switch (labelText) {
		case ' ':
			textLabelImageView.setImageResource(R.drawable.label_blank);
			break;
		case 'a':
			textLabelImageView.setImageResource(R.drawable.label_a);
			break;
		case 'b':
			textLabelImageView.setImageResource(R.drawable.label_b);
			break;
		case 'c':
			textLabelImageView.setImageResource(R.drawable.label_c);
			break;
		case 'd':
			textLabelImageView.setImageResource(R.drawable.label_d);
			break;
		case 'e':
			textLabelImageView.setImageResource(R.drawable.label_e);
			break;
		case 'f':
			textLabelImageView.setImageResource(R.drawable.label_f);
			break;
		case 'g':
			textLabelImageView.setImageResource(R.drawable.label_g);
			break;
		case 'h':
			textLabelImageView.setImageResource(R.drawable.label_h);
			break;
		case '1':
			textLabelImageView.setImageResource(R.drawable.label_1);
			break;
		case '2':
			textLabelImageView.setImageResource(R.drawable.label_2);
			break;
		case '3':
			textLabelImageView.setImageResource(R.drawable.label_3);
			break;
		case '4':
			textLabelImageView.setImageResource(R.drawable.label_4);
			break;
		case '5':
			textLabelImageView.setImageResource(R.drawable.label_5);
			break;
		case '6':
			textLabelImageView.setImageResource(R.drawable.label_6);
			break;
		case '7':
			textLabelImageView.setImageResource(R.drawable.label_7);
			break;
		case '8':
			textLabelImageView.setImageResource(R.drawable.label_8);
			break;
		default:
			Log.e("Label", "Error: label text not supported for label at " + position.toString());
			textLabelImageView.setImageResource(R.drawable.label_blank);
			return false;				
		}
		
		return true;
	}

	/*@Override
	protected void onSelected(boolean selected) {
		throw new InvalidOperationException("a label should not be selected!");
	}*/
	
}
