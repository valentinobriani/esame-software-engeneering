package com.example.esamesoftwareengeneering.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.esamesoftwareengeneering.GameActivity;
import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.layout;
import com.example.esamesoftwareengeneering.board.cells.Cell;
import com.example.esamesoftwareengeneering.board.cells.Square;
import com.example.esamesoftwareengeneering.board.pieces.Piece;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.BishopBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.KingBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.KnightBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PawnBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Type;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.QueenBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.RookBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.board.position.File;
import com.example.esamesoftwareengeneering.board.position.Position;
import com.example.esamesoftwareengeneering.board.position.Rank;

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
	public List<Piece> getPieces(Color color) {
		// Create the list of pieces
		List<Piece> pieceList = new ArrayList<Piece>();
		
		// Create a piece iterator
		Iterator<Piece> pieceIterator = pieces.values().iterator();
		
		// Iterate the pieces
		while (pieceIterator.hasNext()) {
			Piece piece = pieceIterator.next();
			
			// If the piece has the searched color, add it to the list
			if (piece.getColor() == color) {
				pieceList.add(piece);
			}
		}
		
		// Return the list
		return pieceList;
	}
	public List<Piece> getPieces(Color color, Type type) {
		// Create the list of pieces
		List<Piece> pieceList = new ArrayList<Piece>();
		
		// Create a piece iterator
		Iterator<Piece> pieceIterator = pieces.values().iterator();
		
		// Iterate the pieces
		while (pieceIterator.hasNext()) {
			Piece piece = pieceIterator.next();
			
			// If the piece has the searched color and type, add it to the list
			if (piece.getColor() == color && piece.getType() == type) {
				pieceList.add(piece);
			}
		}
		
		// Return the list
		return pieceList;
	}
	
	public Piece getPiece(Position position) {
		return pieces.get(position);
	}
	
	public Set<Position> getPiecePositions() {
		return pieces.keySet();
	}
	
	public Set<Position> getPiecePositions(Color color, Type type) {
		// Create the set of positions
		Set<Position> positionSet = new HashSet<Position>();
		
		// Create a position iterator
		Iterator<Position> positionIterator = pieces.keySet().iterator();
		
		// Iterate the positions
		while (positionIterator.hasNext()) {
			Position position = positionIterator.next();
			Piece positionPiece = pieces.get(position);
			
			// If the piece has the searched color and type, add the position to the set
			if (positionPiece.getColor() == color && positionPiece.getType() == type) {
				positionSet.add(position);
			}
		}
			
		// Return the list
		return positionSet;
	}
	
	public Position getPiecePosition(Piece piece) {		
		// Create a position iterator
		Iterator<Position> positionIterator = pieces.keySet().iterator();
		
		// Iterate the positions
		while (positionIterator.hasNext()) {
			Position position = positionIterator.next();
			Piece positionPiece = pieces.get(position);
			
			// If the piece is found, return the position
			if (positionPiece == piece) {
				return position;
			}
		}
		
		// Else, return null
		return null;
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
		//Log.i("CellAdapter", "Update views");
		
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
		pieces.put(new Position('1', 'e'), new Piece(new KingBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'd'), new Piece(new QueenBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'c'), new Piece(new BishopBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'f'), new Piece(new BishopBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'b'), new Piece(new KnightBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'g'), new Piece(new KnightBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'a'), new Piece(new RookBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('1', 'h'), new Piece(new RookBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'a'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'b'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'c'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'd'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'e'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'f'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'g'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		pieces.put(new Position('2', 'h'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.WHITE)));
		
		// Black pieces
		pieces.put(new Position('8', 'e'), new Piece(new KingBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'd'), new Piece(new QueenBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'c'), new Piece(new BishopBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'f'), new Piece(new BishopBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'b'), new Piece(new KnightBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'g'), new Piece(new KnightBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'a'), new Piece(new RookBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('8', 'h'), new Piece(new RookBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'a'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'b'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'c'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'd'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'e'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'f'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'g'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
		pieces.put(new Position('7', 'h'), new Piece(new PawnBehaviour(this, PieceBehaviour.Color.BLACK)));
	}
	
}
