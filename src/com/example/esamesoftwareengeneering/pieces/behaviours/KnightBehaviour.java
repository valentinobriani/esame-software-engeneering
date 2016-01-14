package com.example.esamesoftwareengeneering.pieces.behaviours;

import java.util.Map;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.pieces.Piece;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour.Color;

public class KnightBehaviour extends PieceBehaviour {
	
	
	public KnightBehaviour(Color color) {
		super(color, Type.KNIGHT);
	}
	
	@Override
	public int getResourceId() {
		if (getColor() == PieceBehaviour.Color.WHITE) {
			return R.drawable.knight_white;
		} else {
			return R.drawable.knight_black;
		}
	}
	
	@Override
	public boolean isMoveValid(Map<Position, Piece> pieces, Position startPosition, Position endPosition) {
		Piece endPositionPiece = pieces.get(endPosition);
		
		int rankDistance = startPosition.getRank().distance(endPosition.getRank());
		int fileDistance = startPosition.getFile().distance(endPosition.getFile());
		
		// Movement / Capture
		if ((endPositionPiece == null || (endPositionPiece.getColor() != this.color && endPositionPiece.getType() != Type.KING)) &&
				((rankDistance == 1 && fileDistance == 2) || (rankDistance == 2 && fileDistance == 1))) {
			return true;
		}
		
		return false;
	}
	
}