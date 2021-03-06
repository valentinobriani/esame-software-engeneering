package com.example.esamesoftwareengeneering.game.board.pieces.behaviours;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.game.board.pieces.Piece;
import com.example.esamesoftwareengeneering.game.board.pieces.Pieces;
import com.example.esamesoftwareengeneering.game.board.position.File;
import com.example.esamesoftwareengeneering.game.board.position.Position;
import com.example.esamesoftwareengeneering.game.board.position.Rank;

public class BishopBehaviour extends PieceBehaviour {
	
	
	public BishopBehaviour(Pieces pieces, Color color) {
		super(pieces, color, Type.BISHOP);
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
	public boolean isMovementValid(Piece piece, Position destinationPosition) {
		// Get the piece position
		Position piecePosition = pieces.getPiecePosition(piece);
		
		// Get the destination position's piece
		Piece destinationPositionPiece = pieces.getPiece(destinationPosition);
		
		// Movement / Capture
		if ((destinationPositionPiece == null || (destinationPositionPiece.getColor() != this.color/* && destinationPositionPiece.getType() != Type.KING*/)) &&
				destinationPosition.isDiagonally(piecePosition)) {
			int startRow = piecePosition.getRank().getRow();
			int startColumn = piecePosition.getFile().getColumn();
			int endRow = destinationPosition.getRank().getRow();
			int endColumn = destinationPosition.getFile().getColumn();
			
			int rowOffset = (endRow - startRow) / Math.abs(endRow - startRow);
			int columnOffset = (endColumn - startColumn) / Math.abs(endColumn - startColumn);
			
			int row = startRow + rowOffset;
			int column = startColumn + columnOffset;
			
			// Check that there are no pieces between start and end position
			while (row != endRow && column != endColumn) {
				Position position = new Position(new Rank(row), new File(column));
				if (pieces.getPiece(position) != null) {
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