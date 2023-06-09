package com.canvas.others;

import com.canvas.exception.CanvasException;
import com.canvas.exception.NoSuchOperationException;
import com.canvas.operations.*;

import java.util.Arrays;

public interface CanvasOperationFactory  {


	static CanvasOperation getOperation(String[] inputArgs) throws CanvasException {
		CanvasOperation operation;
		String[] arguments =Arrays.copyOfRange(inputArgs,1,inputArgs.length);
		operation = switch (inputArgs[0]) {
			case OperationConstants.CANVAS -> new DrawCanvasOperation(arguments);
			case OperationConstants.LINE -> new LineOperation(arguments);
			case OperationConstants.RECTANGLE -> new RectangleOperation(arguments);
			case OperationConstants.FILL -> new FillOperation(arguments);
			default -> throw new NoSuchOperationException("Not a supported operation");
		};
		return operation;
	}

}
