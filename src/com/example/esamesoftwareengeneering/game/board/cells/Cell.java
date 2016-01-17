package com.example.esamesoftwareengeneering.game.board.cells;

import com.example.esamesoftwareengeneering.game.board.position.Position;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

public abstract class Cell extends FrameLayout {
	
	protected Context context;
	protected boolean initialized = false;
	protected Position position = null;
	
	
	public Cell(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
	
	@Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
	
	public boolean isInitialized() {
		return initialized;
	}

	public Position getPosition() {
		if (!initialized || position == null) {
			throw new IllegalStateException("The cell is not initialized!");
		}
		
		return position;
	}

	public boolean init(Position position) {
		Log.i("Cell", "init cell at " + position.toString());
		
		if (initialized) {
			throw new IllegalStateException("The cell at " + position.toString() + " is already initialized!");
		}
		
		this.position = position;
				
		initialized = onInit(position);
		
		return initialized;
	}
    
	protected abstract boolean onInit(Position position);
	
}
