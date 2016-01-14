package com.example.esamesoftwareengeneering.pieces;

import java.util.Map;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.drawable;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour.Type;

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
	
	public boolean isMoveValid(Map<Position, Piece> pieces, Position startPosition, Position endPosition) {
		return pieceBehaviour.isMoveValid(pieces, startPosition, endPosition);
	}
	
	public void promote(Type newType) {
		pieceBehaviour.promote(this, newType);
	}
	
	@Override
	public String toString() {
		return pieceBehaviour.toString();
	}
	
}
