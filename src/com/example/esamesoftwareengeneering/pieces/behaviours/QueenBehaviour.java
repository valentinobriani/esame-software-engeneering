package com.example.esamesoftwareengeneering.pieces.behaviours;

import java.util.Map;

import android.util.Log;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.position.File;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.board.position.Rank;
import com.example.esamesoftwareengeneering.pieces.Piece;

public class QueenBehaviour extends PieceBehaviour {
	
	
	public QueenBehaviour(Color color) {
		super(color, Type.QUEEN);
	}
	
	@Override
	public int getResourceId() {
		if (getColor() == PieceBehaviour.Color.WHITE) {
			return R.drawable.queen_white;
		} else {
			return R.drawable.queen_black;
		}
	}
	
	@Override
	public boolean isMoveValid(Map<Position, Piece> pieces, Position startPosition, Position endPosition) {
		Piece endPositionPiece = pieces.get(endPosition);
		
		// Movement / Capture
		if (endPositionPiece == null ||
				(endPositionPiece.getColor() != this.color && endPositionPiece.getType() != Type.KING)) {
			
			// Perpendicular movement
			if (endPosition.isPerpendicular(startPosition)) {
				int rankDistance = startPosition.getRank().distance(endPosition.getRank());
				int fileDistance = startPosition.getFile().distance(endPosition.getFile());
				
				if (rankDistance == 0) {
					int startColumn = startPosition.getFile().getColumn();
					int endColumn = endPosition.getFile().getColumn();
					int columnOffset = (endColumn - startColumn) / Math.abs(endColumn - startColumn);
					int column = startColumn + columnOffset;
					
					// Check that there are no pieces between start and end position
					while (column != endColumn) {
						Position position = new Position(startPosition.getRank(), new File(column));
						Piece piece = pieces.get(position);
						if (piece != null) {
							return false;
						}
						column += columnOffset;
					}
					
					return true;
				} else if (fileDistance == 0) {
					int startRow = startPosition.getRank().getRow();
					int endRow = endPosition.getRank().getRow();
					int rowOffset = (endRow - startRow) / Math.abs(endRow - startRow);
					int row = startRow + rowOffset;
					
					// Check that there are no pieces between start and end position
					while (row != endRow) {
						Position position = new Position(new Rank(row), startPosition.getFile());
						Piece piece = pieces.get(position);
						if (piece != null) {
							return false;
						}
						row += rowOffset;
					}
					
					return true;
				} else {
					Log.e("Rook", "rankDistance or fileDistance must be 0");
				}
			}
			
			// Diagonal movement
			else if (endPosition.isDiagonally(startPosition)) {
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
		}
		
		return false;
	}
	
}