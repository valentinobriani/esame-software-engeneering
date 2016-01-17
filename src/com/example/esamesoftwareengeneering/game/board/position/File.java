package com.example.esamesoftwareengeneering.game.board.position;

import java.util.Objects;

import com.example.esamesoftwareengeneering.game.board.Board;

import android.R.raw;


public class File {
	
	private int column;
	private char fileChar;
	
	
	public File(int column) {
		if (column < 0 || column >= Board.COLUMNS) {
			throw new IllegalArgumentException("File constructor parameter \'column\' must be in the range [0, " + Board.COLUMNS + ")!");
		}
		
		this.column = column;
		this.fileChar = (char)('a' + (column - Board.FIRST_FILE_COLUMN));
	}
	
	public File(char fileChar) {
		this.column = Board.FIRST_FILE_COLUMN + (int)(fileChar - 'a');
		this.fileChar = fileChar;
		
		if (column < 0 || column >= Board.COLUMNS) {
			throw new IllegalArgumentException("File parameter \'column\' must be in the range [0, " + Board.COLUMNS + ")!");
		}
	}
	
	public int getColumn() {
		return column;
	}
	
	public char getFileChar() {
		return fileChar;
	}
	
	public int distance(File otherColumn) {
		return Math.abs(column - otherColumn.column);
	}
	
	@Override
    public int hashCode(){
		int hash = 1;
	    hash = hash * 31 + column;
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
		
		return (column == ((File) otherObject).column);
	}
	
	@Override
	public String toString() {
		return "File " + fileChar;
	}

}
