package com.example.esamesoftwareengeneering.board;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

//@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//public class Board extends GridLayout {
public class Board extends GridView {
	
	public final static int RANKS = 8;
	public final static int FILES = 8;
	public final static int LABEL_ROWS = 2;
	public final static int LABEL_COLUMNS = 2;
	public final static int ROWS = RANKS + LABEL_ROWS;
	public final static int COLUMNS = FILES + LABEL_COLUMNS;
	public final static int FIRST_RANK_ROW = LABEL_ROWS / 2;
	public final static int LAST_RANK_ROW = ROWS - (LABEL_ROWS / 2) - 1;
	public final static int FIRST_FILE_COLUMN = LABEL_COLUMNS / 2;
	public final static int LAST_FILE_COLUMN = COLUMNS - (LABEL_COLUMNS / 2) - 1;
	
	
	public Board(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * Make the gridview always exanded, in order to show all the rows 
	 */
	@Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST));
        getLayoutParams().height = getMeasuredHeight();
    }

}
