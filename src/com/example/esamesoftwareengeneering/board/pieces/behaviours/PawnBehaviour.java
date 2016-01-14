package com.example.esamesoftwareengeneering.board.pieces.behaviours;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.widget.Toast;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.board.CellAdapter;
import com.example.esamesoftwareengeneering.board.pieces.Piece;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Type;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.exceptions.InvalidOperationException;

public class PawnBehaviour extends PieceBehaviour {
	
	
	public PawnBehaviour(CellAdapter cellAdapter, Color color) {
		super(cellAdapter, color, Type.PAWN);
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
	public boolean isMoveValid(Piece piece, Position destinationPosition) {
		// Get the piece position
		Position piecePosition = cellAdapter.getPiecePosition(piece);
		
		// Get the destination position's piece
		Piece destinationPositionPiece = cellAdapter.getPiece(destinationPosition);
		
		// Movement
		if (destinationPositionPiece == null &&
				piecePosition.getFile().equals(destinationPosition.getFile()) &&
				destinationPosition.getRank().getRow() == piecePosition.getRank().getRow() + (this.color == Color.WHITE ? -1 : 1)) {
			return true;
		}
		
		// Capture
		if (destinationPositionPiece != null && destinationPositionPiece.getColor() != this.color && destinationPositionPiece.getType() != Type.KING &&
				piecePosition.getFile().distance(destinationPosition.getFile()) == 1 &&
				destinationPosition.getRank().getRow() == piecePosition.getRank().getRow() + (this.color == Color.WHITE ? -1 : 1)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void promote(Piece piece, Type newType) {
		switch (newType) {
		case QUEEN:
			piece.setPieceBehaviour(new QueenBehaviour(this.cellAdapter, this.color));
			break;
		case BISHOP:
			piece.setPieceBehaviour(new BishopBehaviour(this.cellAdapter, this.color));
			break;
		case KNIGHT:
			piece.setPieceBehaviour(new KnightBehaviour(this.cellAdapter, this.color));
			break;
		case ROOK:
			piece.setPieceBehaviour(new RookBehaviour(this.cellAdapter, this.color));
			break;
		default:
			throw new InvalidOperationException("Pawn can't be promoted to " + newType.toString() + "!");
		}
	}
	
}
