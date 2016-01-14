package com.example.esamesoftwareengeneering;

import java.util.Map;

import com.example.esamesoftwareengeneering.board.Board;
import com.example.esamesoftwareengeneering.board.CellAdapter;
import com.example.esamesoftwareengeneering.board.cells.Square;
import com.example.esamesoftwareengeneering.board.cells.Square.Selection;
import com.example.esamesoftwareengeneering.board.pieces.Piece;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.BishopBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.KnightBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PawnBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.QueenBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.RookBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.board.position.Position;

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

public class GameActivity extends /*ActionBar*/Activity {
		
	private TextView turnTextView;
	private Board board;
	private Button confirmMoveButton;
	private CellAdapter cellAdapter;
	
	private PieceBehaviour.Color turn;
	private Position selectedPiecePosition;
	private Position selectedDestinationPosition;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		// Initialize variables
		turnTextView = (TextView) findViewById(R.id.textView_turn);
		board = (Board) findViewById(R.id.board);
		cellAdapter = new CellAdapter(this);
		confirmMoveButton = (Button) findViewById(R.id.button_confirm_move);
		
		turn = PieceBehaviour.Color.WHITE;
		selectedPiecePosition = null;
		selectedDestinationPosition = null;
		
		turnTextView.setText("Turn: " + turn.toString());
		
		board.setAdapter(cellAdapter);
		
		// Set the listener for the cells of the board
		board.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view, int viewPosition, long id) {
	            
	        	// If the clicked view is a square (it can also be a label)
	            if (view instanceof Square) {	            	
	            	Square clickedSquare = (Square) view;
	            	Position clickedPosition = clickedSquare.getPosition();
            		Piece clickedPiece = cellAdapter.getPiece(clickedPosition);
            		
            		// If there is no piece selected
	            	if (selectedPiecePosition == null) {
	            		
	            		// Select the clicked piece if it is of the player
	            		if (clickedPiece != null && clickedPiece.getColor() == turn) {
		            		selectedPiecePosition = clickedPosition;
		            		clickedSquare.setSelection(Selection.PIECE);
	            		}
	            	
	            	}
	            	
	            	// If there is already a piece selected
	            	else {
	            		
	            		// If in the clicked position there is a piece of the player
	            		if (clickedPiece != null && clickedPiece.getColor() == turn) {

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
	            			Piece selectedPiece = cellAdapter.getPiece(selectedPiecePosition);
	            			
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
	            		confirmMoveButton.setVisibility(View.INVISIBLE);
	            	}
	            }
	            
	        }
	    });
		
		// Set the listener for the confirm move button
		confirmMoveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				performMove();
			}
		});
		
	}
	
	private void performMove() {
		final Map<Position, Piece> pieces = cellAdapter.getPieces();
		Piece selectedPiece = pieces.get(selectedPiecePosition);
		
		// Check for pawn promotion
		if (selectedPiece.getType() == PieceBehaviour.Type.PAWN) {
			if ((selectedPiece.getColor() == PieceBehaviour.Color.WHITE &&
					selectedDestinationPosition.getRank().getRow() == Board.FIRST_RANK_ROW) ||
					(selectedPiece.getColor() == PieceBehaviour.Color.BLACK &&
					selectedDestinationPosition.getRank().getRow() == Board.LAST_RANK_ROW)) {
				showPromotionDialog(selectedDestinationPosition, selectedPiece);
			}
		}
		
		// Update chessboard
		pieces.remove(selectedPiecePosition);
		pieces.put(selectedDestinationPosition, selectedPiece);
		cellAdapter.notifyDataSetChanged();
		
		// Update turn
		turn = turn == PieceBehaviour.Color.WHITE ? PieceBehaviour.Color.BLACK : PieceBehaviour.Color.WHITE;
		
		turnTextView.setText("Turn: " + turn.toString());
		
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
		confirmMoveButton.setVisibility(View.INVISIBLE);
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
					
					// Update the views on the chessboard
					cellAdapter.notifyDataSetChanged();
					
					/*Toast.makeText(
							GameActivity.this,
							piece.toString() + " at "+ position.toString() + ": promoted",
							Toast.LENGTH_LONG).show();*/
					
					// Dismiss the dialog
					promotionAlertDialog.dismiss();
				}
			});
			
			// Show the dialog
			promotionAlertDialog.show();
		}		
	}
}
