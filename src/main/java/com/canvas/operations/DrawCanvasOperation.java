package com.canvas.operations;

import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.*;

public class DrawCanvasOperation implements CanvasOperation{
    public int getWidth() {
        return width;
    }

    int width;

    public int getHeight() {
        return height;
    }

    int height;

    public DrawCanvasOperation (String[] inputs) throws Exception {
        if(inputs==null || inputs.length < 3)
            throw new IncorrectParametersException("Canvas needs height and width to be specified: C W H");
        width = Integer.parseInt((String)inputs[1]);
        height = Integer.parseInt((String)inputs[2]);
        if(width<0 || height<0)
            throw new IncorrectParametersException("Canvas needs height and width to be greater than 0");

    }
    @Override
    public boolean execute (Canvas canvas) {
        canvas.getCanvas();
        return true;
    }
    @Override
    public void undo(Canvas canvas) {
        if(canvas!=null)
        {
            canvas=null;
        }
      }
}
