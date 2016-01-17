package com.example.esamesoftwareengeneering.game.board.pieces.behaviours;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.game.board.pieces.Piece;
import com.example.esamesoftwareengeneering.game.board.pieces.Pieces;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.game.board.position.Position;

public class KnightBehaviour extends PieceBehaviour {
	
	
	public KnightBehaviour(Pieces pieces, Color color) {
		super(pieces, color, Type.KNIGHT);
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
	public boolean isMovementValid(Piece piece, Position destinationPosition) {
		// Get the piece position
		Position piecePosition = pieces.getPiecePosition(piece);
		
		// Get the destination position's piece
		Piece destinationPositionPiece = pieces.getPiece(destinationPosition);
		
		// Calculate distance to destination
		int rankDistance = piecePosition.getRank().distance(destinationPosition.getRank());
		int fileDistance = piecePosition.getFile().distance(destinationPosition.getFile());
		
		// Movement / Capture
		if ((destinationPositionPiece == null || (destinationPositionPiece.getColor() != this.color/* && destinationPositionPiece.getType() != Type.KING*/)) &&
				((rankDistance == 1 && fileDistance == 2) || (rankDistance == 2 && fileDistance == 1))) {
			return true;
		}
		
		return false;
	}
	
}