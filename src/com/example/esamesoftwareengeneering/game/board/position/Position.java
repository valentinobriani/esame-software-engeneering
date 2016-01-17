package com.example.esamesoftwareengeneering.game.board.position;

import java.util.Objects;

import android.util.Log;

public class Position {
	
	private Rank rank;
	private File file;
	
	
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