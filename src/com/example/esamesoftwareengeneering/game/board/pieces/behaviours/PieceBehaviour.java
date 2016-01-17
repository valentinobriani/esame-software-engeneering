package com.example.esamesoftwareengeneering.game.board.pieces.behaviours;

import android.util.Log;

import com.example.esamesoftwareengeneering.exceptions.InvalidOperationException;
import com.example.esamesoftwareengeneering.game.board.Board;
import com.example.esamesoftwareengeneering.game.board.pieces.Piece;
import com.example.esamesoftwareengeneering.game.board.pieces.Pieces;
import com.example.esamesoftwareengeneering.game.board.position.File;
import com.example.esamesoftwareengeneering.game.board.position.Position;
import com.example.esamesoftwareengeneering.game.board.position.Rank;

public abstract class PieceBehaviour {
	
	public enum Color {
		WHITE(0),
		BLACK(1);
		private int color;
		
		private Color(int color) {
			this.color = color;
		}
		
		public Color other() {
			return (color == 0 ? Color.BLACK : Color.WHITE);
		}
		
		@Override
		public String toString() {
			return (color == 0 ? "White" : "Black");
		}
	}
	
	public static enum Type {
		KING(0),
		QUEEN(1),
		BISHOP(2),
		KNIGHT(3),
		ROOK(4),
		PAWN(5);
		private int typeIndex;
		
		private Type(int typeIndex) {
			this.typeIndex = typeIndex;
		}
		
		@Override
		public String toString() {
			switch (typeIndex) {
			case 0: return "King";
			case 1: return "Queen";
			case 2: return "Bishop";
			case 3: return "Knight";
			case 4: return "Rook";
			case 5: return "Pawn";
			default:
				Log.e("Piece Type", "Piece type index not supported");
				return "Invalid piece type";
			}
		}
	};
	protected final Pieces pieces;
	protected final Color color;
	protected final Type type;
	
	
	public PieceBehaviour(Pieces pieces, Color color, Type type) {
		this.pieces = pieces;
		this.color = color;
		this.type = type;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Type getType() {
		return type;
	}
	
	public Type[] getPromotionOptions() {
		return null;
	}
	
	public abstract int getResourceId();
	
	public boolean hasValidMoves(Piece piece) {
		boolean validMoves = false;
		
		// For each position on the board
		for (int row = Board.FIRST_RANK_ROW; !validMoves && row <= Board.LAST_RANK_ROW; ++row) {
			for (int column = Board.FIRST_FILE_COLUMN; !validMoves && column <= Board.LAST_FILE_COLUMN; ++column) {
				Position destinationPosition = new Position(
						new Rank(row),
						new File(column));
				
				// If the piece can move to a position, it has valid moves
				validMoves = isMoveValid(piece, destinationPosition);
			}
		}
		return validMoves;
	}
	
	/**
	 * Check that the movement is valid for this piece
	 * @param piece
	 * @param destinationPosition
	 * @return
	 */
	public abstract boolean isMovementValid(Piece piece, Position destinationPosition);
	
	/**
	 * Check that the movement is valid for this piece and that the move don't leave the player's king in check
	 * @param piece
	 * @param destinationPosition
	 * @return
	 */
	public boolean isMoveValid(Piece piece, Position destinationPosition) {
		// If the movement is valid
		if (isMovementValid(piece, destinationPosition)) {
			Position initialPosition = pieces.getPiecePosition(piece);
			Piece destinationPositionPiece = pieces.getPiece(destinationPosition);
			
			// Simulate the move
			pieces.getPieces().put(destinationPosition, piece);
			pieces.getPieces().remove(initialPosition);
			
			// Check that the king will be in check
			Piece king = pieces.getKing(piece.getColor());
			boolean check = king.isInCheck();
			
			// Restore the previous state 
			pieces.getPieces().remove(destinationPosition);
			pieces.getPieces().put(initialPosition, piece);
			if (destinationPositionPiece != null) {
				pieces.getPieces().put(destinationPosition, destinationPositionPiece);
			}
			
			// If the king will be in check, the move is not valid
			return !check;
		}
		
		// The move is not valid
		return false;
	}
	
	public boolean isInCheck(Piece piece) {
		throw new InvalidOperationException("Default behaviour do not permit to be in check. Only the king can be in check!");
	}
	
	public void move(Piece piece, Position destinationPosition) {
		// If the move is valid
		if (isMoveValid(piece, destinationPosition)) {
			
			// Move the piece
			Position initialPosition = pieces.getPiecePosition(piece);
			pieces.getPieces().remove(initialPosition);
			pieces.getPieces().put(destinationPosition, piece);
		}
		
		// Else, throw an exception
		else {
			throw new InvalidOperationException("The move is not valid: check that the move is valid before performing it!");
		}
	}
	
	public void promote(Piece piece, Type newType) {
		throw new InvalidOperationException("Default behaviour do not permit promotion. Implement promotion in subclass!");
	}
	
	@Override
	public String toString() {
		return color.toString() + " " + type.toString();
	}
	
}
