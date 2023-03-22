package com.canvas.operations;

import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.*;

public class DrawCanvasOperation implements CanvasOperation{

   private Coordinate[] coordinates;
    public int getWidth() {
        return width;
    }

    int width;

    public int getHeight() {
        return height;
    }

    int height;

    public DrawCanvasOperation (String[] inputs) throws IncorrectParametersException {
        if(!validateNoOfArguments(inputs))
            throw new IncorrectParametersException("Canvas needs height and width to be specified: C W H");
        validateAndSetArgumentsValue(inputs);
        if(width<=0 || height<=0)
            throw new IncorrectParametersException("Canvas needs height and width to be greater than 0");

    }
    @Override
    public boolean execute (TwoDCanvas canvas) {
        canvas.createCanvas();
        return true;
    }
    private boolean validateNoOfArguments(String[] inputs)
    {
        if(inputs==null || inputs.length < 2)
            return false;
         return true;



    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            width = Integer.parseInt((String) inputs[0]);
            height = Integer.parseInt((String) inputs[1]);
            coordinates = new Coordinate[1];
            coordinates[0]=new TwoDCoordinate<Integer>(Integer.valueOf(inputs[0]),Integer.valueOf(inputs[1]));
        } catch(NumberFormatException e)
        {
            throw new IncorrectParametersException("Canvas width and height should be valid numbers");

         }

    }


/*    @Override
    public void undo(TwoDCanvas canvas) {
        if(canvas!=null)
              canvas=null;

      }*/

    @Override
    public Coordinate[] getCoordinates() {
        return coordinates;
    }
}
