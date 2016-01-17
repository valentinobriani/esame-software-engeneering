package com.example.esamesoftwareengeneering;

import com.example.esamesoftwareengeneering.board.pieces.behaviours.PieceBehaviour.Color;

public class Game {
	
	public enum State {
		START,
		PLAYING,
		END
	}
	private State state;
	private Color turn;
	
	
	public Game() {
		state = State.START;
		turn = Color.WHITE;
	}
	
	public State getState() {
		return state;
	}
	
	public void changeTurn() {
		turn = turn.other();
	}

}
