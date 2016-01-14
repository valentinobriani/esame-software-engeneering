package com.example.esamesoftwareengeneering.pieces.behaviours;

import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.widget.Toast;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.exceptions.InvalidOperationException;
import com.example.esamesoftwareengeneering.pieces.Piece;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour.Type;

public class PawnBehaviour extends PieceBehaviour {
	
	
	public PawnBehaviour(Color color) {
		super(color, Type.PAWN);
	}
	
	@Override
	public Type[] getPromotionOptions() {
		return new Type[] {
				PieceBehaviour.Type.QUEEN,
				PieceBehaviour.Type.BISHOP,
				PieceBehaviour.Type.KNIGHT,
				PieceBehaviour.Type.ROOK
		};
	}
	
	@Override
	public int getResourceId() {
		if (getColor() == Color.WHITE) {
			return R.drawable.pawn_white;
		} else {
			return R.drawable.pawn_black;
		}
	}
	
	@Override
	public boolean isMoveValid(Map<Position, Piece> pieces, Position startPosition, Position endPosition) {
		Piece endPositionPiece = pieces.get(endPosition);
		
		// Movement
		if (endPositionPiece == null &&
				startPosition.getFile().equals(endPosition.getFile()) &&
				endPosition.getRank().getRow() == startPosition.getRank().getRow() + (this.color == Color.WHITE ? -1 : 1)) {
			return true;
		}
		
		// Capture
		if (endPositionPiece != null && endPositionPiece.getColor() != this.color && endPositionPiece.getType() != Type.KING &&
				startPosition.getFile().distance(endPosition.getFile()) == 1 &&
				endPosition.getRank().getRow() == startPosition.getRank().getRow() + (this.color == Color.WHITE ? -1 : 1)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void promote(Piece piece, Type newType) {
		switch (newType) {
		case QUEEN:
			piece.setPieceBehaviour(new QueenBehaviour(this.color));
			break;
		case BISHOP:
			piece.setPieceBehaviour(new BishopBehaviour(this.color));
			break;
		case KNIGHT:
			piece.setPieceBehaviour(new KnightBehaviour(this.color));
			break;
		case ROOK:
			piece.setPieceBehaviour(new RookBehaviour(this.color));
			break;
		default:
			throw new InvalidOperationException("Pawn can't be promoted to " + newType.toString() + "!");
		}
	}
	
}
