package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectCoordinatesException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.Canvas;
import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCoordinate;

public class FillOperation implements CanvasOperation{


    int x;
    int y;

    private char color;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }



    public FillOperation(String[]  inputs) throws Exception {
        if (!validateNoOfArguments(inputs))
            throw new IncorrectParametersException("Fill operation needs 2 co-ordinates and fill color: B x y c ");
        validateAndSetArgumentsValue(inputs);
    }




    @Override
    public boolean execute (Canvas canvas) throws CanvasException {
        if (canvas != null) {
           if (canvas.isWithinCanvas(x, y)) {
                canvas.doFill(x,y,color);
                return true;
            } else
                throw new IncorrectCoordinatesException("Execution Failed:Cordinates are not within the Canvas");
        } else {
            System.out.println("Canvas is needed to perform Fill: Please create canvas first");
        }
        return false;
    }

    @Override
    public void undo(Canvas canvas) {

        canvas.undoFill(x,y,color);
    }
    private boolean validateNoOfArguments(String[] inputs)
    {
        if(inputs==null || inputs.length < 4)
            return false;
        return true;

    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            x = Integer.parseInt(inputs[1]);
            y = Integer.parseInt(inputs[2]);
            color=inputs[3].charAt(0);
        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Line co-ordinates should be valid numbers");
        }


    }
}
