package com.canvas.operations;

import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.*;

public class DrawCanvasOperation implements CanvasOperation{

  /*  private Coordinate[] points;*/
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
        if(width<0 || height<0)
            throw new IncorrectParametersException("Canvas needs height and width to be greater than 0");

    }
    @Override
    public boolean execute (Canvas canvas) {
        canvas.createCanvas();
        return true;
    }
    private boolean validateNoOfArguments(String[] inputs)
    {
        if(inputs==null || inputs.length < 3)
            return false;
         return true;



    }
    private void validateAndSetArgumentsValue(String[] inputs) throws IncorrectParametersException {
        try {
            width = Integer.parseInt((String) inputs[1]);
            height = Integer.parseInt((String) inputs[2]);
        } catch(NumberFormatException e)
        {
            throw new IncorrectParametersException("Canvas width and height should be valid numbers");

         }

    }


    @Override
    public void undo(Canvas canvas) {
        if(canvas!=null)
              canvas=null;

      }
}
