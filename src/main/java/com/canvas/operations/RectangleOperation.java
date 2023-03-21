package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectCoordinatesException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.Canvas;

public class RectangleOperation implements CanvasOperation {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public RectangleOperation(String[] inputs) throws IncorrectParametersException {
        if (!validateNoOfArguments(inputs)) {
            throw new IncorrectParametersException("Rectangle needs 4 co-ordinates");
        }
        validateAndSetArgumentsValue(inputs);
    }

    @Override
    public boolean execute(Canvas canvas) throws CanvasException {

        if(canvas!=null) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            if (canvas.isWithinCanvas(x1, y1) && canvas.isWithinCanvas(x2, y2)) {
                canvas.addRectangle(x1, y1, x2, y2);
                return true;
            } else
                throw new IncorrectCoordinatesException("Execution Failed:Cordinates are not within the Canvas");
        }
        else {
            System.out.println("Canvas is needed to draw rectangle: Please create canvas first");
        }
        return false;
    }

    @Override
    public void undo(Canvas canvas) {
        canvas.removeLine(x1, y1, x2, y2);

    }
    private boolean validateNoOfArguments(String[] inputs)
    {
        if(inputs==null || inputs.length < 4)
            return false;
        return true;

    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            x1 = Math.min(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            y1 = Math.min(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[4]));
            x2 = Math.max(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            y2 = Math.max(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[4]));
        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Rectangle co-ordinates should be valid numbers");
        }
    }
}
