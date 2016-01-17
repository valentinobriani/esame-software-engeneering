package com.example.esamesoftwareengeneering.game.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.esamesoftwareengeneering.R;
import com.example.esamesoftwareengeneering.R.layout;
import com.example.esamesoftwareengeneering.game.GameActivity;
import com.example.esamesoftwareengeneering.game.board.cells.Cell;
import com.example.esamesoftwareengeneering.game.board.cells.Square;
import com.example.esamesoftwareengeneering.game.board.pieces.Piece;
import com.example.esamesoftwareengeneering.game.board.pieces.Pieces;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.BishopBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.KingBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.KnightBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PawnBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.QueenBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.RookBehaviour;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.game.board.pieces.behaviours.PieceBehaviour.Type;
import com.example.esamesoftwareengeneering.game.board.position.File;
import com.example.esamesoftwareengeneering.game.board.position.Position;
import com.example.esamesoftwareengeneering.game.board.position.Rank;

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
	private Pieces pieces;
	
	
	public CellAdapter(GameActivity gameActivity) {
		this.gameActivity = gameActivity;
		cells = new HashMap<Position, Cell>();
		pieces = new Pieces(new HashMap<Position, Piece>());
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
	
	public void setPieces(Pieces pieces) {
		this.pieces = pieces;
	}
	
	public Pieces getPieces() {
		return pieces;
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
		Log.i("CellAdapter", "Views updated");
		
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
			if (pieces != null && pieces.getPiece(position) != null) {
				Piece piece = pieces.getPiece(position);
				pieceImageView.setImageResource(piece.getResourceId());
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
	
}
