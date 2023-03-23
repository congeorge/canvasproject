package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectCoordinatesException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.exception.NoCanvasException;
import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCanvas;
import com.canvas.model.TwoDCoordinate;

public class FillOperation implements CanvasOperation{

    private Coordinate[] coordinates;


    private char color;

    public char getColor() {
        return color;
    }
    @Override
    public Coordinate[] getCoordinates() {

        return coordinates;
    }






    public FillOperation(String[]  inputs) throws CanvasException {
        if (!validateNoOfArguments(inputs))
            throw new IncorrectParametersException("Fill operation needs 2 co-ordinates and fill color: B x y c ");
        validateAndSetArgumentsValue(inputs);
    }




    @Override
    public boolean execute (TwoDCanvas canvas) throws CanvasException {
        if (canvas != null) {
           if (canvas.isWithinCanvas(coordinates[0])) {
                canvas.doFill(coordinates[0],color);
                return true;
            } else
                throw new IncorrectCoordinatesException("Execution Failed:Cordinates are not within the Canvas");
        } else {
            throw new NoCanvasException("Canvas is needed to perform Fill: Please create canvas first");
        }

    }



    private boolean validateNoOfArguments(String[] inputs)
    {     boolean status=true;
        if(inputs==null || inputs.length < 3)
            status= false;
        return status;

    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            int x= Integer.parseInt(inputs[0]);
            int y =Integer.parseInt(inputs[1]);
            if(x<=0 || y<=0 )
                throw new IncorrectParametersException("Fill coordinates cannot be less than 0");

            coordinates = new Coordinate[1];
            coordinates[0]=new TwoDCoordinate<Integer>(x,y);
            color=inputs[2].charAt(0);

        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Fill co-ordinates should be valid numbers");
        }


    }
}
