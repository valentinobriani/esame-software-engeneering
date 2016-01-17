package com.example.esamesoftwareengeneering.game.board.position;

import java.util.Objects;

import com.example.esamesoftwareengeneering.game.board.Board;


public class Rank {
	
	private int row;
	private char rankChar;
	
	
	public Rank(int row) {
		if (row < 0 || row >= Board.ROWS) {
			throw new IllegalArgumentException("Rank constructor parameter \'row\' must be in the range [0, " + Board.ROWS + ")!");
		}
		
		this.row = row;
		this.rankChar = (char)('1' + Board.LAST_RANK_ROW - row);
	}
	
	public Rank(char rankChar) {
		this.row = Board.LAST_RANK_ROW - (int)(rankChar - '1');
		this.rankChar = rankChar;
		
		if (row < 0 || row >= Board.ROWS) {
			throw new IllegalArgumentException("Rank parameter \'row\' must be in the range [0, " + Board.ROWS + ")!");
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public char getRankChar() {
		return rankChar;
	}
	
	public int distance(Rank otherRow) {
		return Math.abs(row - otherRow.row);
	}
	
	@Override
    public int hashCode(){
		int hash = 1;
	    hash = hash * 31 + row;
	    return hash;
    }
	
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null) {
            return false;
		}
		if (getClass() != otherObject.getClass()) {
            return false;
		}
		
		return (row == ((Rank) otherObject).row);
	}
	
	@Override
	public String toString() {
		return "Rank " + rankChar;
	}
	
}
