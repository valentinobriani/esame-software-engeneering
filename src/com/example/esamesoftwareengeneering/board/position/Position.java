package com.example.esamesoftwareengeneering.board.position;

import java.util.Objects;

import android.util.Log;

public class Position {
	
	/*public enum Row {
		ROW_8(1),
		ROW_7(2),
		ROW_6(3),
		ROW_5(4),
		ROW_4(5),
		ROW_3(6),
		ROW_2(7),
		ROW_1(8);
		private int gridViewRow;
		
		private Row(int gridViewRow) {
			this.gridViewRow = gridViewRow;
		}
		
		@Override
		public String toString() {
			return "Row " + (9 - gridViewRow);
		}
	}
	
	public enum Column {
		COLUMN_A(1),
		COLUMN_B(2),
		COLUMN_C(3),
		COLUMN_D(4),
		COLUMN_E(5),
		COLUMN_F(6),
		COLUMN_G(7),
		COLUMN_H(8);
		private int gridViewColumn;
		
		private Column(int gridViewColumn) {
			this.gridViewColumn = gridViewColumn;
		}
		
		@Override
		public String toString() {
			return "Column " + ('a' + (gridViewColumn - 1));
		}
	}*/
	/*private int row;
	private int column;*/
	private Rank rank;
	private File file;
	
	
	/*public Position(int row, int column) {
		if (row < 1 || row > Board.ROWS || column < 1 || column > Board.COLUMNS) {
			Log.e("Position", "Invalid values for position (row = " + row + ", column = " + column +")");
		}
		this.row = row;
		this.column = column;
	}*/
	
	public Position(Rank rank, File file) {
		this.rank = rank;
		this.file = file;
	}
	
	public Position(char rankChar, char fileChar) {
		this.rank = new Rank(rankChar);
		this.file = new File(fileChar);
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public File getFile() {
		return file;
	}
	
	/*public int distance(Position otherPosition) {
		return (rank.distance(otherPosition.rank) + file.distance(otherPosition.file));
	}*/
	
	public boolean isPerpendicular(Position otherPosition) {
		return rank.equals(otherPosition.getRank()) || file.equals(otherPosition.getFile());
	}
	
	public boolean isDiagonally(Position otherPosition) {
		return rank.distance(otherPosition.getRank()) == file.distance(otherPosition.getFile());
	}
	
	@Override
    public int hashCode(){
		int hash = 1;
	    hash = hash * 31 + rank.hashCode();
	    hash = hash * 31 + file.hashCode();
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
		
		return (rank.equals(((Position) otherObject).rank) && file.equals(((Position) otherObject).file));
	}
	
	@Override
	public String toString() {
		return "Position: (" + rank.toString() + ", " + file.toString() + ")";
	}
	
}