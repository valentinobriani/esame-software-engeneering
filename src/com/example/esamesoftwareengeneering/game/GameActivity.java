package com.example.esamesoftwareengeneering.game;

import java.util.Map;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.id;
import com.example.esamesoftwareengeneering.R.layout;
import com.example.esamesoftwareengeneering.game.Game.State;
import com.example.esamesoftwareengeneering.game.board.Board;
import com.example.esamesoftwareengeneering.game.board.CellAdapter;
import com.example.esamesoftwareengeneering.game.board.cells.Square;
import com.example.esamesoftwareengeneering.game.board.cells.Square.Selection;
import com.example.esamesoftwareengeneering.game.board.pieces.Piece;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.BishopBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.KnightBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PawnBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.QueenBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.RookBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.game.board.position.Position;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
		
	private TextView labelTextView;
	private Board board;
	private Button confirmMoveButton;
	private Button playAgainButton;
	private CellAdapter cellAdapter;
	
	private Game game;
	
	private Position selectedPiecePosition;
	private Position selectedDestinationPosition;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		// Initialize variables
		labelTextView = (TextView) findViewById(R.id.textView_label);
		board = (Board) findViewById(R.id.board);
		confirmMoveButton = (Button) findViewById(R.id.button_confirm_move);
		playAgainButton = (Button) findViewById(R.id.button_play_again);
		cellAdapter = new CellAdapter(this);
		board.setAdapter(cellAdapter);
		
		game = new Game(this, labelTextView, cellAdapter, playAgainButton);
		
		selectedPiecePosition = null;
		selectedDestinationPosition = null;
		
		// Set the listener for the cells of the board
		board.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view, int viewPosition, long id) {
	            // If the game state is playing and the clicked view is a square (it can also be a label)
	            if (game.getState() == State.PLAYING && view instanceof Square) {            	
	            	Square clickedSquare = (Square) view;
	            	Position clickedPosition = clickedSquare.getPosition();
            		Piece clickedPiece = cellAdapter.getPieces().getPiece(clickedPosition);
            		
            		// If there is no piece selected
	            	if (selectedPiecePosition == null) {
	            		// Select the clicked piece if it is of the player
	            		if (clickedPiece != null && clickedPiece.getColor() == game.getTurn()) {
		            		selectedPiecePosition = clickedPosition;
		            		clickedSquare.setSelection(Selection.PIECE);
	            		}
	            	}
	            	
	            	// If there is already a piece selected
	            	else {
	            		// If in the clicked position there is a piece of the player
	            		if (clickedPiece != null && clickedPiece.getColor() == game.getTurn()) {
	            			// Unselect previous selected piece
	            			if (selectedPiecePosition != null) {
	            				cellAdapter.getSquare(selectedPiecePosition).setSelection(Selection.NONE);
	            				selectedPiecePosition = null;
	            			}

            				// Unselect previous selected destination
	            			if (selectedDestinationPosition != null) {
	            				cellAdapter.getSquare(selectedDestinationPosition).setSelection(Selection.NONE);
	            				selectedDestinationPosition = null;	            				
	            			}
	            			
	            			// Select the clicked piece
		            		selectedPiecePosition = clickedPosition;
		            		clickedSquare.setSelection(Selection.PIECE);
	            		}
	            		
	            		// If in the clicked position there is no piece or there is a piece of the opposing player
	            		else {
            				// Unselect previous selected destination
	            			if (selectedDestinationPosition != null) {
	            				cellAdapter.getSquare(selectedDestinationPosition).setSelection(Selection.NONE);
	            				selectedDestinationPosition = null;
	            			}
	            			
	            			// Get the selected piece
	            			Piece selectedPiece = cellAdapter.getPieces().getPiece(selectedPiecePosition);
	            			
	            			// Select the clicked destination if the selected piece can move to the destination position
	            			if (selectedPiece != null &&
	            					selectedPiece.isMoveValid(clickedPosition)) {
		            			selectedDestinationPosition = clickedPosition;
			            		clickedSquare.setSelection(Selection.DESTINATION);	            				
	            			}
	            		}
	            	}
	            	
	            	// If a piece and a destination are selected, enable the confirm move button
	            	if (selectedPiecePosition != null && selectedDestinationPosition != null) {
	            		confirmMoveButton.setVisibility(View.VISIBLE);
	            	} else {
	            		confirmMoveButton.setVisibility(View.GONE);
	            	}
	            }
	            
	        }
	    });
		
		// Set the listener for the confirm move button
		confirmMoveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				moveSelectedPiece();
			}
		});
		
		// Set the listener for the play again button
		playAgainButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// Restart the game
				game.restart();
				
				// Hide the play again button
				view.setVisibility(View.GONE);
			}
		});
	}
	
	private void moveSelectedPiece() {
		final Map<Position, Piece> pieces = cellAdapter.getPieces().getPieces();
		Piece selectedPiece = pieces.get(selectedPiecePosition);
		
		if (selectedPiece != null) {
			// Move the piece
			selectedPiece.move(selectedDestinationPosition);
			
			// If a pawn reaches its eighth rank
			if (selectedPiece.getType() == PieceBehaviour.Type.PAWN &&
					((selectedPiece.getColor() == PieceBehaviour.Color.WHITE &&
					selectedDestinationPosition.getRank().getRow() == Board.FIRST_RANK_ROW) ||
					(selectedPiece.getColor() == PieceBehaviour.Color.BLACK &&
					selectedDestinationPosition.getRank().getRow() == Board.LAST_RANK_ROW))) {
				// Promote the piece
				showPromotionDialog(selectedDestinationPosition, selectedPiece);
			}
			
			// Else, change the turn
			else {
				game.changeTurn();
			}
			
			// Unselect previous selected piece
			if (selectedPiecePosition != null) {
				cellAdapter.getSquare(selectedPiecePosition).setSelection(Selection.NONE);
				selectedPiecePosition = null;
			}
			
			// Unselect previous selected destination
			if (selectedDestinationPosition != null) {
				cellAdapter.getSquare(selectedDestinationPosition).setSelection(Selection.NONE);
				selectedDestinationPosition = null;
			}
			
			// Hide the confirm move button
			confirmMoveButton.setVisibility(View.GONE);
		}
	}
	
	private void showPromotionDialog(final Position position, final Piece piece) {
		final PieceBehaviour.Type[] promotionOptions = piece.getPromotionOptions();
		
		// If the piece can be promoted, show the dialog with the promotion options
		if (promotionOptions != null && promotionOptions.length > 0) {
			// Create the options' strings
			final String[] promotionOptionStrings = new String[promotionOptions.length];
			for (int index = 0; index < promotionOptions.length; ++index) {
				promotionOptionStrings[index] = promotionOptions[index].toString();
			}
			
			// Initialize the listview with the options' strings
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
					GameActivity.this,
					android.R.layout.simple_list_item_1,
					android.R.id.text1,
					promotionOptionStrings);
			ListView listView = new ListView(GameActivity.this);
			listView.setAdapter(arrayAdapter);
			
			// Create the dialog
			final AlertDialog promotionAlertDialog = new AlertDialog.Builder(GameActivity.this)
				.setTitle("Promotion")
				.setView(listView)
				.setCancelable(false)
				.create();
			
			// Set the listener for the listview
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int viewPosition, long id) {
					// Promote the piece
					piece.promote(promotionOptions[viewPosition]);
					
					// Change the turn
					game.changeTurn();
					
					// Dismiss the dialog
					promotionAlertDialog.dismiss();
				}
			});
			
			// Show the dialog
			promotionAlertDialog.show();
		}
	}
	
	public Game getGame() {
		return game;
	}
	
}
