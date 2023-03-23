package com.canvas.others;

import com.canvas.exception.CanvasException;
import com.canvas.exception.NoSuchOperationException;
import com.canvas.operations.*;

import java.util.Arrays;

public class CanvasOperationFactory  {

	public static CanvasOperation getOperation(String[] inputArgs) throws Exception {
		CanvasOperation operation = null;
		String[] arguments =Arrays.copyOfRange(inputArgs,1,inputArgs.length);
		switch (inputArgs[0]) {
			case OperationsConstants.CANVAS:
				operation = new DrawCanvasOperation(arguments);
				break;
			case OperationsConstants.LINE:
				operation = new LineOperation(arguments);
				break;
			case OperationsConstants.RECTANGLE:
				operation = new RectangleOperation(arguments);
				break;
			case OperationsConstants.FILL:
				operation = new FillOperation(arguments);
				break;
			case OperationsConstants.UNDO:
				operation = new UndoCanvasOperation(arguments);
				break;
			case OperationsConstants.QUIT:
				operation = new QuitOperation(arguments);
				break;
			case OperationsConstants.Help:
				operation = new HelpOperation(arguments);
				break;
			default:
				throw new NoSuchOperationException("Not a supported operation");
		}
		return operation;
	}

}
