package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectCoordinatesException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.exception.NoCanvasException;
import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCanvas;
import com.canvas.model.TwoDCoordinate;

public class RectangleOperation implements CanvasOperation {

    private Coordinate<Integer>[] coordinates;


    public RectangleOperation(String[] inputs) throws IncorrectParametersException {
        if (!validateNoOfArguments(inputs)) {
            throw new IncorrectParametersException("Rectangle needs 4 co-ordinates");
        }
        validateAndSetArgumentsValue(inputs);
    }

    @Override
    public boolean execute(TwoDCanvas canvas) throws CanvasException {

        if(canvas!=null) {
           if (canvas.isWithinCanvas(coordinates[0]) && canvas.isWithinCanvas(coordinates[1])) {
                canvas.drawRectangle(coordinates[0], coordinates[1]);
                return true;
            } else
                throw new IncorrectCoordinatesException("Execution Failed:Cordinates are not within the Canvas");
        }
        else {
           throw new NoCanvasException("Canvas is needed to draw rectangle: Please create canvas first");
          }

    }

    @Override
    public Coordinate[] getCoordinates() {

        return coordinates;
    }

    private boolean validateNoOfArguments(String[] inputs)
    {
        boolean status=true;
        if(inputs==null || inputs.length < 4)
            status= false;
        return status;

    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            int x1 = Math.min(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[2]));
            int y1 = Math.min(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            int x2 = Math.max(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[2]));
            int y2 = Math.max(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            if(x1<=0 || x2<=0 || y1<=0|| y2<=0)
                throw new IncorrectParametersException("Rectangle coordinates cannot be less than 0");
            coordinates=new Coordinate[2];
            coordinates[0]=new TwoDCoordinate<>(x1,y1);
            coordinates[1]=new TwoDCoordinate<>(x2,y2);
        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Rectangle co-ordinates should be valid numbers");
        }
    }
}
