package com.example.esamesoftwareengeneering.board.pieces.behaviours;

import android.util.Log;

import com.example.esamesoftwareengeneering.board.Board;
import com.example.esamesoftwareengeneering.board.CellAdapter;
import com.example.esamesoftwareengeneering.board.pieces.Piece;
import com.example.esamesoftwareengeneering.board.position.File;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.board.position.Rank;
import com.example.esamesoftwareengeneering.exceptions.InvalidOperationException;

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
	protected final CellAdapter cellAdapter;
	protected final Color color;
	protected final Type type;
	
	
	public PieceBehaviour(CellAdapter cellAdapter, Color color, Type type) {
		this.cellAdapter = cellAdapter;
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
		for (int row = Board.FIRST_RANK_ROW; !validMoves && row <= Board.LAST_RANK_ROW; ++row) {
			for (int column = Board.FIRST_FILE_COLUMN; !validMoves && column <= Board.LAST_FILE_COLUMN; ++column) {
				Position destinationPosition = new Position(
						new Rank(row),
						new File(column));
				validMoves = isMoveValid(piece, destinationPosition);
			}
		}
		return validMoves;
	}
	
	public abstract boolean isMoveValid(Piece piece, Position destinationPosition);
	
	public boolean isInCheck(Piece piece) {
		throw new InvalidOperationException("Default behaviour do not permit to be in check. Only the king can be in check!");
	}
	
	public void promote(Piece piece, Type newType) {
		throw new InvalidOperationException("Default behaviour do not permit promotion. Implement promotion in subclass!");
	}
	
	@Override
	public String toString() {
		return color.toString() + " " + type.toString();
	}
	
}
