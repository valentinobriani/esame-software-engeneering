package com.example.esamesoftwareengeneering.board.pieces.behaviours;

import android.util.Log;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.pieces.Piece;
import com.example.esamesoftwareengeneering.board.pieces.Pieces;
import com.example.esamesoftwareengeneering.board.position.File;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.board.position.Rank;

public class RookBehaviour extends PieceBehaviour {
	
	
	public RookBehaviour(Color color) {
		super(color, Type.ROOK);
	}
	
	@Override
	public int getResourceId() {
		if (getColor() == PieceBehaviour.Color.WHITE) {
			return R.drawable.rook_white;
		} else {
			return R.drawable.rook_black;
		}
	}
	
	@Override
	public boolean isMovementValid(Pieces pieces, Piece piece, Position destinationPosition) {
		// Get the piece position
		Position piecePosition = pieces.getPiecePosition(piece);
		
		// Get the destination position's piece
		Piece destinationPositionPiece = pieces.getPiece(destinationPosition);
		
		// Movement / Capture
		if ((destinationPositionPiece == null || (destinationPositionPiece.getColor() != this.color/* && destinationPositionPiece.getType() != Type.KING*/)) &&
				destinationPosition.isPerpendicular(piecePosition)) {
			int rankDistance = piecePosition.getRank().distance(destinationPosition.getRank());
			int fileDistance = piecePosition.getFile().distance(destinationPosition.getFile());
			
			if (rankDistance == 0) {
				int startColumn = piecePosition.getFile().getColumn();
				int endColumn = destinationPosition.getFile().getColumn();
				int columnOffset = (endColumn - startColumn) / Math.abs(endColumn - startColumn);
				int column = startColumn + columnOffset;
				
				// Check that there are no pieces between start and end position
				while (column != endColumn) {
					Position position = new Position(piecePosition.getRank(), new File(column));
					if (pieces.getPiece(position) != null) {
						return false;
					}
					column += columnOffset;
				}
				
				return true;
			} else if (fileDistance == 0) {
				int startRow = piecePosition.getRank().getRow();
				int endRow = destinationPosition.getRank().getRow();
				int rowOffset = (endRow - startRow) / Math.abs(endRow - startRow);
				int row = startRow + rowOffset;
				
				// Check that there are no pieces between start and end position
				while (row != endRow) {
					Position position = new Position(new Rank(row), piecePosition.getFile());
					if (pieces.getPiece(position) != null) {
						return false;
					}
					row += rowOffset;
				}
				
				return true;
			} else {
				Log.e("Rook", "rankDistance or fileDistance must be 0");
			}
		}
		
		return false;
	}
	
}