package com.example.esamesoftwareengeneering.board;

import java.util.HashMap;
import java.util.Map;

import com.example.esamesoftwareengeneering.GameActivity;
import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.layout;
import com.example.esamesoftwareengeneering.board.cells.Cell;
import com.example.esamesoftwareengeneering.board.cells.Square;
import com.example.esamesoftwareengeneering.board.position.File;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.board.position.Rank;
import com.example.esamesoftwareengeneering.pieces.Piece;
import com.example.esamesoftwareengeneering.pieces.behaviours.BishopBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.KingBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.KnightBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.PawnBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.QueenBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.RookBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour.Color;

import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class CellAdapter extends BaseAdapter {
	
	private GameActivity gameActivity;
	private Map<Position, Cell> cells;
	private Map<Position, Piece> pieces;
	
	
	public CellAdapter(GameActivity gameActivity) {		
		this.gameActivity = gameActivity;
		
		cells = new HashMap<Position, Cell>();
				
		initBoard();
	}
	
	@Override
	public int getCount() {		
		return (Board.ROWS * Board.COLUMNS);		
	}

	@Override
	public Object getItem(int viewPosition) {		
		int row = viewPosition / Board.COLUMNS;
		int column = viewPosition % Board.COLUMNS;
		
		Position position = new Position(new Rank(row), new File(column));
		return cells.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}
	
	@Override
	public View getView(int viewPosition, View convertView, ViewGroup parent) {
		//Toast.makeText(gameActivity, "Update views", Toast.LENGTH_SHORT).show();
		//Log.e("CellAdapter", "Update views");
		
		Cell cell;
		LayoutInflater layoutInflater = gameActivity.getLayoutInflater();
		
		// Calculate the position from the view position in the grid
		int row = viewPosition / Board.COLUMNS;
		int column = viewPosition % Board.COLUMNS;
		Position position = new Position(new Rank(row), new File(column));
		
		// Inflate a label
		if (row < Board.FIRST_RANK_ROW || row > Board.LAST_RANK_ROW ||
				column < Board.FIRST_FILE_COLUMN || column > Board.LAST_FILE_COLUMN) {
			cell = (Cell) layoutInflater.inflate(R.layout.label, null, false);
		}
		
		// Inflate a square
		else {
			cell = (Cell) layoutInflater.inflate(R.layout.square, null, false);
			
			final ImageView pieceImageView = (ImageView) cell.findViewById(R.id.piece);
			
			// If there is a piece in the square's position, show the piece imageview
			if (pieces.containsKey(position)) {
				pieceImageView.setImageResource(pieces.get(position).getResourceId());
				pieceImageView.setVisibility(View.VISIBLE);
			} else {
				pieceImageView.setVisibility(View.INVISIBLE);
			}
		}
		
		// Initialize the cell
		cell.init(position);
		cells.put(position, cell);
		
		return cell;		
	}
	
	public void initBoard() {		
		pieces = new HashMap<Position, Piece>();
		
		// White pieces
		pieces.put(new Position('1', 'e'), new Piece(new KingBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'd'), new Piece(new QueenBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'c'), new Piece(new BishopBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'f'), new Piece(new BishopBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'b'), new Piece(new KnightBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'g'), new Piece(new KnightBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'a'), new Piece(new RookBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'h'), new Piece(new RookBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'a'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'b'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'c'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'd'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'e'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'f'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'g'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'h'), new Piece(new PawnBehaviour(PieceBehaviour.Color.WHITE)));
		
		// Black pieces
		pieces.put(new Position('8', 'e'), new Piece(new KingBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'd'), new Piece(new QueenBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'c'), new Piece(new BishopBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'f'), new Piece(new BishopBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'b'), new Piece(new KnightBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'g'), new Piece(new KnightBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'a'), new Piece(new RookBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'h'), new Piece(new RookBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'a'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'b'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'c'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'd'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'e'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'f'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'g'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'h'), new Piece(new PawnBehaviour(PieceBehaviour.Color.BLACK)));
	}
	
	public Map<Position, Cell> getCells() {
		return cells;
	}
	
	public Cell getCell(Position position) {
		return cells.get(position);
	}
	
	public Square getSquare(Position position) {
		Cell cell = cells.get(position);
		return (cell instanceof Square ? (Square) cell : null);
	}
	
	public Map<Position, Piece> getPieces() {
		return pieces;
	}
	
	public Piece getPiece(Position position) {
		return pieces.get(position);
	}
	
}
