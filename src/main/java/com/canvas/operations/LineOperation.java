package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectCoordinatesException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.*;

public class LineOperation implements CanvasOperation{
    private Coordinate<Integer>[] coordinates;
    public LineOperation (String[] inputs) throws IncorrectParametersException {
        if (!validateNoOfArguments(inputs))
            throw new IncorrectParametersException("Line needs 4 co-ordinates : L x1 y1 x2 y2" );
        validateAndSetArgumentsValue(inputs);

    }

    @Override
    public boolean execute (TwoDCanvas canvas) throws CanvasException {
        if(canvas!=null)
        {
            if (canvas.isWithinCanvas(coordinates[0]) && canvas.isWithinCanvas(coordinates[1])) {
                canvas.addLine(coordinates[0], coordinates[1]);
                return true;
            } else
                throw new IncorrectCoordinatesException("Execution Failed:Cordinates are not within the Canvas");
        }
        else {
            System.out.println("Canvas is needed to draw lines: Please create canvas first");
        }
        return false;
    }

    @Override
    public Coordinate[] getCoordinates() {

        return coordinates;
    }

    private boolean validateNoOfArguments(String[] inputs)
    {
        if(inputs==null || inputs.length < 4)
            return false;
        return true;

   }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            int x1 = Math.min(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[2]));
            int y1 = Math.min(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            int x2 = Math.max(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[2]));
            int y2 = Math.max(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[3]));
            if (x1 != x2 && y1 != y2) {
                throw new IncorrectParametersException("Diagonal line are not supported");
            }
            coordinates=new Coordinate[2];
            coordinates[0]=new TwoDCoordinate<Integer>(x1,y1);
            coordinates[1]=new TwoDCoordinate<Integer>(x2,y2);
        } catch(NumberFormatException e)
        {
            throw new IncorrectParametersException("Line needs valid 4 co-ordinates : L x1 y1 x2 y2");
        }

    }

}
