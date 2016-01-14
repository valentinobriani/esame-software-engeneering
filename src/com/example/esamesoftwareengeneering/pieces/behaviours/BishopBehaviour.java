package com.example.esamesoftwareengeneering.pieces.behaviours;

import java.util.Map;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.position.File;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.board.position.Rank;
import com.example.esamesoftwareengeneering.pieces.Piece;

public class BishopBehaviour extends PieceBehaviour {
	
	
	public BishopBehaviour(Color color) {
		super(color, Type.BISHOP);
	}

	@Override
	public int getResourceId() {
		if (getColor() == PieceBehaviour.Color.WHITE) {
			return R.drawable.bishop_white;
		} else {
			return R.drawable.bishop_black;
		}
	}

	@Override
	public boolean isMoveValid(Map<Position, Piece> pieces, Position startPosition, Position endPosition) {
		Piece endPositionPiece = pieces.get(endPosition);
		
		// Movement / Capture
		if ((endPositionPiece == null || (endPositionPiece.getColor() != this.color && endPositionPiece.getType() != Type.KING)) &&
				endPosition.isDiagonally(startPosition)) {
			int startRow = startPosition.getRank().getRow();
			int startColumn = startPosition.getFile().getColumn();
			int endRow = endPosition.getRank().getRow();
			int endColumn = endPosition.getFile().getColumn();
			
			int rowOffset = (endRow - startRow) / Math.abs(endRow - startRow);
			int columnOffset = (endColumn - startColumn) / Math.abs(endColumn - startColumn);
			
			int row = startRow + rowOffset;
			int column = startColumn + columnOffset;
			
			// Check that there are no pieces between start and end position
			while (row != endRow && column != endColumn) {
				Position position = new Position(new Rank(row), new File(column));
				Piece piece = pieces.get(position);
				if (piece != null) {
					return false;
				}
				row += rowOffset;
				column += columnOffset;
			}
			
			return true;
		}
		
		return false;
	}
	
}