package com.example.esamesoftwareengeneering.game;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esamesoftwareengeneering.game.board.CellAdapter;
import com.example.esamesoftwareengeneering.game.board.pieces.Piece;
import com.example.esamesoftwareengeneering.game.board.pieces.Pieces;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PieceBehaviour.Color;

public class Game {
	
	public enum State {
		START,
		PLAYING,
		END
	}
	private Context context;
	private TextView labelTextView;
	private CellAdapter cellAdapter;
	private Button playAgainButton;
	private State state;
	private Color turn;
	
	
	public Game(Context context, TextView labelTextView, CellAdapter cellAdapter, Button playAgainButton) {
		this.context = context;
		this.labelTextView = labelTextView;
		this.cellAdapter = cellAdapter;
		this.playAgainButton = playAgainButton;
		start();
	}
	
	public State getState() {
		return state;
	}
	
	public Color getTurn() {
		return turn;
	}
	
	public void changeTurn() {
		setTurn(turn.other());
		cellAdapter.notifyDataSetChanged();
	}
	
	private void setTurn(Color turn) {
		Pieces pieces = cellAdapter.getPieces();
		
		// Set the turn
		this.turn = turn;
		labelTextView.setText("Turn: " + turn.toString());
		
		// Check if the player's king is in check
		boolean check = pieces.getKing(turn).isInCheck();
		if (check) {
			Toast.makeText(
					context,
					turn.toString() + " king is in check!",
					Toast.LENGTH_LONG
					).show();
		}
		
		// Check if the player can move
		List<Piece> playerPieces = pieces.getPieces(turn);
		Iterator<Piece> pieceIterator = playerPieces.iterator();
		boolean canMove = false;
		while (!canMove && pieceIterator.hasNext()) {
			canMove = pieceIterator.next().hasValidMoves();
		}
		
		// If the player has no valid moves
		if (!canMove) {
			String endgameMessage = "";
			
			// If the player's king is in check, the other player wins (checkmate)
			if (check) {
				endgameMessage = "Checkmate: " + turn.other().toString() + " wins";
			}
			
			// Else, the match ends in a draw (stalemate)
			else {
				endgameMessage = "Draw (stalemate)";
			}

			// End the game
			end(endgameMessage);
		}
	}
	
	public void restart() {
		start();
	}
	
	private void start() {
		state = State.START;
		cellAdapter.setPieces(Pieces.getInitialConfiguration());
		cellAdapter.notifyDataSetChanged();
		play();
	}
	
	private void play() {
		state = State.PLAYING;
		setTurn(Color.WHITE);
	}
	
	private void end(String endgameMessage) {
		state = State.END;
		labelTextView.setText(endgameMessage);
		playAgainButton.setVisibility(View.VISIBLE);
	}
	
}
