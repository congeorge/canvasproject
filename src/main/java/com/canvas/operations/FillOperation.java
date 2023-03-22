package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectCoordinatesException;
import com.canvas.exception.IncorrectParametersException;
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






    public FillOperation(String[]  inputs) throws Exception {
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
            System.out.println("Canvas is needed to perform Fill: Please create canvas first");
        }
        return false;
    }



    private boolean validateNoOfArguments(String[] inputs)
    {
        if(inputs==null || inputs.length < 3)
            return false;
        return true;

    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            coordinates = new Coordinate[1];
            coordinates[0]=new TwoDCoordinate<Integer>(Integer.valueOf(inputs[0]),Integer.valueOf(inputs[1]));
            color=inputs[2].charAt(0);
        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Line co-ordinates should be valid numbers");
        }


    }
}
