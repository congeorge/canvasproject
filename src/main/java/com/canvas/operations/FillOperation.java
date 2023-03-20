package com.canvas.operations;

import com.canvas.exception.IncorrectCordinatesException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.Canvas;
import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCoordinate;

public class FillOperation implements CanvasOperation{

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    private Coordinate[] coordinates;


    private char color;

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }



    public FillOperation(String[]  inputs) throws Exception {
        if (inputs.length < 4)
            throw new IncorrectParametersException("Fill operation needs 2 co-ordinates and fill color: B x y c ");
        try {
            coordinates = new Coordinate[1];
            int x = Integer.parseInt(inputs[1]);
            int y = Integer.parseInt(inputs[2]);
          coordinates[0]=new TwoDCoordinate(x,y);
            color=inputs[3].charAt(0);
        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Line co-ordinates should be valid numbers");
        }

    }




    @Override
    public boolean execute (Canvas canvas) throws Exception {
        if (canvas != null) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            Coordinate startpoint =coordinates[0];
            int x = startpoint.getX();
            int y = startpoint.getY();
            if (canvas.isWithinCanvas(x, y)) {
                canvas.doFill(x,y,color);
                return true;
            } else
                throw new IncorrectCordinatesException("Execution Failed:Cordinates are not within the Canvas");
        } else {
            System.out.println("Canvas is needed to perform Fill: Please create canvas first");
        }
        return false;
    }

    @Override
    public void undo(Canvas canvas) {


    }
}
