package com.canvas.operations;

import com.canvas.exception.IncorrectCordinatesException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.*;

public class LineOperation implements CanvasOperation{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    public LineOperation (String[] inputs) throws IncorrectParametersException {
        if (inputs.length < 4)
            throw new IncorrectParametersException("Line needs 4 co-ordinates");
        try {
            x1 = Math.min(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            y1 = Math.min(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[4]));
            x2 = Math.max(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            y2 = Math.max(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[4]));

        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Line co-ordinates should be valid numbers");
        }
        if (x1 != x2 && y1 != y2) {
            throw new IncorrectParametersException("Diagonal line are not supported");
        }
    }

    @Override
    public boolean execute (Canvas canvas) throws Exception {
        if(canvas!=null)
        {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            if (canvas.isWithinCanvas(x1, y1) && canvas.isWithinCanvas(x2, y2)) {
                canvas.addLine(x1, y1, x2, y2);
                return true;
            } else
                throw new IncorrectCordinatesException("Execution Failed:Cordinates are not within the Canvas");
        }
        else {
            System.out.println("Canvas is needed to draw lines: Please create canvas first");
        }
        return false;
    }

    @Override
    public void undo(Canvas canvas) {
        canvas.removeLine(x1,y1,x2,y2);

    }
}
