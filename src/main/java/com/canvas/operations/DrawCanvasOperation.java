package com.canvas.operations;

import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.*;

public class DrawCanvasOperation implements CanvasOperation{

   private Coordinate[] coordinates;


    public DrawCanvasOperation (String[] inputs) throws IncorrectParametersException {
        if(!validateNoOfArguments(inputs))
            throw new IncorrectParametersException("Canvas needs height and width to be specified: C W H");
        validateAndSetArgumentsValue(inputs);
    }
    @Override
    public boolean execute (TwoDCanvas canvas) {
        canvas.createCanvas();
        return true;
    }
    private boolean validateNoOfArguments(String[] inputs)
    {
        boolean status=true;
        if(inputs==null || inputs.length < 2)
            status=false;

         return status;
    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            int width= Integer.parseInt((inputs[0]));
            int height= Integer.parseInt((inputs[1]));
            if(width<=0 || height<=0)
                throw new IncorrectParametersException("Canvas needs height and width to be greater than 0");
            coordinates = new Coordinate[1];
            coordinates[0]=new TwoDCoordinate<>(width,height);
        } catch(NumberFormatException e)
        {
            throw new IncorrectParametersException("Canvas width and height should be valid numbers");

         }

    }

    @Override
    public Coordinate[] getCoordinates() {
        return coordinates;
    }
}
