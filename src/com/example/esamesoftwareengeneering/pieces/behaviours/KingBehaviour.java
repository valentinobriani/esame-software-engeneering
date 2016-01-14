package com.example.esamesoftwareengeneering.pieces.behaviours;

import java.util.Map;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.pieces.Piece;

public class KingBehaviour extends PieceBehaviour {
	
	
	public KingBehaviour(Color color) {
		super(color, Type.KING);
	}
	
	@Override
	public int getResourceId() {
		if (getColor() == PieceBehaviour.Color.WHITE) {
			return R.drawable.king_white;
		} else {
			return R.drawable.king_black;
		}
	}
	
	@Override
	public boolean isMoveValid(Map<Position, Piece> pieces, Position startPosition, Position endPosition) {
		Piece endPositionPiece = pieces.get(endPosition);
		
		int rankDistance = startPosition.getRank().distance(endPosition.getRank());
		int fileDistance = startPosition.getFile().distance(endPosition.getFile());
		
		// Movement / Capture
		if (endPositionPiece == null ||
				(endPositionPiece.getColor() != this.color && endPositionPiece.getType() != Type.KING)) {
			
			if (startPosition.isPerpendicular(endPosition) &&
					((rankDistance == 1 && fileDistance == 0) || (rankDistance == 0 && fileDistance == 1))) {
				return true;
			} else if (startPosition.isDiagonally(endPosition) && rankDistance == 1 && fileDistance == 1) {
				return true;
			}
		}
		
		return false;
	}
	
}