package com.canvas.others;

import com.canvas.exception.CanvasException;
import com.canvas.operations.*;

public class CanvasOperationFactory  {

	public static CanvasOperation getOperation(String[] inputArgs) throws Exception {
		CanvasOperation operation = null;
		switch (inputArgs[0]) {
			case Operations.CANVAS:
				operation = new DrawCanvasOperation(inputArgs);
				break;
			case Operations.LINE:
				operation = new LineOperation(inputArgs);
				break;
			case Operations.RECTANGLE:
				operation = new RectangleOperation(inputArgs);
				break;
			case Operations.FILL:
				operation = new FillOperation(inputArgs);
				break;
			case Operations.UNDO:
				operation = new UndoCanvasOperation(inputArgs);
				break;
			case Operations.QUIT:
				operation = new QuitOperation(inputArgs);
				break;
			default:
				throw new CanvasException("Not a supported command.");
		}
		return operation;
	}

}
