package com.example.esamesoftwareengeneering.board.pieces.behaviours;

import java.util.Iterator;
import java.util.List;

import android.util.Log;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.pieces.Piece;
import com.example.esamesoftwareengeneering.board.pieces.Pieces;
import com.example.esamesoftwareengeneering.board.position.Position;

public class KingBehaviour extends PieceBehaviour {
	
	
	public KingBehaviour(Pieces pieces, Color color) {
		super(pieces, color, Type.KING);
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
	public boolean isMovementValid(Piece piece, Position destinationPosition) {
		// Get the piece position
		Position piecePosition = pieces.getPiecePosition(piece);
		
		// Get the destination position's piece
		Piece destinationPositionPiece = pieces.getPiece(destinationPosition);
		
		// Calculate distance to destination
		int rankDistance = piecePosition.getRank().distance(destinationPosition.getRank());
		int fileDistance = piecePosition.getFile().distance(destinationPosition.getFile());
		
		// Movement / Capture
		if (destinationPositionPiece == null ||
				(destinationPositionPiece.getColor() != this.color/* && destinationPositionPiece.getType() != Type.KING*/)) {
			
			if (piecePosition.isPerpendicular(destinationPosition) &&
					((rankDistance == 1 && fileDistance == 0) || (rankDistance == 0 && fileDistance == 1))) {
				return true;
			} else if (piecePosition.isDiagonally(destinationPosition) && rankDistance == 1 && fileDistance == 1) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isInCheck(Piece piece) {
		Position piecePosition = pieces.getPiecePosition(piece);
		
		List<Piece> opposingPlayerPieces = pieces.getPieces(color.other());
		Iterator<Piece> opposingPlayerPieceIterator = opposingPlayerPieces.iterator();
		
		// For each opposing player's piece
		while (opposingPlayerPieceIterator.hasNext()) {
			Piece opposingPlayerPiece = opposingPlayerPieceIterator.next();
			
			// If the opposing player's piece can move to king's position, the king is in check
			if (opposingPlayerPiece.isMovementValid(piecePosition)) {
				Log.i("King", "King is in check");
				return true;
			}
		}
		
		// Else, the king is not in check
		return false;
	}
	
}