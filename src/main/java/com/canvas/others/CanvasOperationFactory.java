package com.canvas.others;

import com.canvas.operations.*;

public interface CanvasOperationFactory {
		String CANVAS="C";
		String LINE="L";
		String RECTANGLE="R";
		String FILL="B";
		String QUIT="Q";
		String UNDO="U";

	public static CanvasOperation getOperation(String[] inputArgs) throws Exception {
		CanvasOperation operation = null;
		switch (inputArgs[0]) {
			case CANVAS:
				operation = new DrawCanvasOperation(inputArgs);
				break;
			case LINE:
				operation = new LineOperation(inputArgs);
				break;
			case RECTANGLE:
				operation = new RectangleOperation(inputArgs);
				break;
			case FILL:
				operation = new FillOperation(inputArgs);
				break;
			case UNDO:
				operation = new UndoCanvasOperation(inputArgs);
				break;
			case QUIT:
				operation = new QuitOperation(inputArgs);
				break;
			default:
				throw new Exception();
		}
		return operation;
	}

}
