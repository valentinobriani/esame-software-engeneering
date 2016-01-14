package com.example.esamesoftwareengeneering.pieces.behaviours;

import java.util.Map;

import android.util.Log;

import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.exceptions.InvalidOperationException;
import com.example.esamesoftwareengeneering.pieces.Piece;

public abstract class PieceBehaviour {
	
	public enum Color {
		WHITE(0),
		BLACK(1);
		private int color;
		
		private Color(int color) {
			this.color = color;
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
	protected final Color color;
	protected final Type type;
	
	
	public PieceBehaviour(Color color, Type type) {
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
	
	public abstract boolean isMoveValid(Map<Position, Piece> pieces, Position startPosition, Position endPosition);
	
	public void promote(Piece piece, Type newType) {
		throw new InvalidOperationException("Default behaviour not permit type promotion. Implement promotion in subclass!");
	}
	
	@Override
	public String toString() {
		return color.toString() + " " + type.toString();
	}
	
}
