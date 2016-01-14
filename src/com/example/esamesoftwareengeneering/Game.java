package com.example.esamesoftwareengeneering;

public class Game {
	
	public enum State {
		START,
		PLAYING,
		END
	}
	private State state;
	
	
	public Game() {
		state = State.START;
	}
	
	public State getState() {
		return state;
	}

}
