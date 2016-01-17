package com.example.esamesoftwareengeneering.board.pieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.esamesoftwareengeneering.board.pieces.behaviours.BishopBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.KingBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.KnightBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PawnBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.QueenBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.RookBehaviour;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Color;
import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Type;
import com.example.esamesoftwareengeneering.board.position.Position;

public class Pieces {
	
	private Map<Position, Piece> pieces;
	
	
	private Pieces(Map<Position, Piece> pieces) {
		this.pieces = pieces;
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
	
	public Piece getKing(Color color) {
		return getPieces(color, Type.KING).get(0);
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
	
	public static Pieces getInitialPieces() {
		Map<Position, Piece> positionPiecesMap = new HashMap<Position, Piece>();
		Pieces pieces = new Pieces(positionPiecesMap);
		
		// White pieces
		positionPiecesMap.put(new Position('1', 'e'), new Piece(new KingBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('1', 'd'), new Piece(new QueenBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('1', 'c'), new Piece(new BishopBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('1', 'f'), new Piece(new BishopBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('1', 'b'), new Piece(new KnightBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('1', 'g'), new Piece(new KnightBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('1', 'a'), new Piece(new RookBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('1', 'h'), new Piece(new RookBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'a'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'b'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'c'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'd'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'e'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'f'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'g'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		positionPiecesMap.put(new Position('2', 'h'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.WHITE)));
		
		// Black pieces
		positionPiecesMap.put(new Position('8', 'e'), new Piece(new KingBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('8', 'd'), new Piece(new QueenBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('8', 'c'), new Piece(new BishopBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('8', 'f'), new Piece(new BishopBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('8', 'b'), new Piece(new KnightBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('8', 'g'), new Piece(new KnightBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('8', 'a'), new Piece(new RookBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('8', 'h'), new Piece(new RookBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'a'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'b'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'c'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'd'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'e'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'f'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'g'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		positionPiecesMap.put(new Position('7', 'h'), new Piece(new PawnBehaviour(pieces, PieceBehaviour.Color.BLACK)));
		
		return pieces;
	}
	
}
