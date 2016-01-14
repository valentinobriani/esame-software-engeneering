package com.example.esamesoftwareengeneering;

import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour;
import com.example.esamesoftwareengeneering.pieces.behaviours.PieceBehaviour.Color;

public class Player {
	
	private PieceBehaviour.Color color;
	
	
	public Player(PieceBehaviour.Color color) {
		this.color = color;
	}
	
	public PieceBehaviour.Color getColor() {
		return color;
	}
	
	public String toString() {
		return "Player: (" + color.toString() + ")";
	}
	
}
