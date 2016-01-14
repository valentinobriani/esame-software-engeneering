package com.example.esamesoftwareengeneering.board.pieces;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.drawable;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Type;
import com.example.esamesoftwareengeneering.board.position.Position;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class Piece {
	
	private PieceBehaviour pieceBehaviour;
	
	
	public Piece(PieceBehaviour pieceBehaviour) {
		this.pieceBehaviour = pieceBehaviour;
	}
	
	public void setPieceBehaviour(PieceBehaviour pieceBehaviour) {
		this.pieceBehaviour = pieceBehaviour;
	}
	
	public Color getColor() {
		return pieceBehaviour.getColor();
	}
	
	public Type getType() {
		return pieceBehaviour.getType();
	}
	
	public Type[] getPromotionOptions() {
		return pieceBehaviour.getPromotionOptions();
	}
	
	public int getResourceId() {
		return pieceBehaviour.getResourceId();
	}
	
	public boolean isMoveValid(Position destinationPosition) {
		return pieceBehaviour.isMoveValid(this, destinationPosition);
	}
	
	public boolean isInCheck() {
		return pieceBehaviour.isInCheck(this);
	}
	
	public void promote(Type newType) {
		pieceBehaviour.promote(this, newType);
	}
	
	@Override
	public String toString() {
		return pieceBehaviour.toString();
	}
	
}
