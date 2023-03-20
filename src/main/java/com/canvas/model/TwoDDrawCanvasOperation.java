package com.canvas.model;

import com.canvas.operations.CanvasOperation;
import com.canvas.model.*;

public class TwoDDrawCanvasOperation<C> implements CanvasOperation<C> {

    public Cell<C>[] getPoints() {
        return points;
    }

    private Cell<C>[] points;


    public TwoDDrawCanvasOperation(C[] inputs) throws Exception {
        validateInput(inputs);
      //  points[0]=new TwoDCell<C>(inputs[1],inputs[2]);

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

    public boolean validateInput(C[] inputArgs) {
        if(inputArgs==null){
            return false; // Incorrect input received.
        }else if(inputArgs.length!=3){
            return false; // Incorrect inputs for canvas draw.
        }else if(inputArgs[1].equals("0") || inputArgs[2].equals("0")){
            return false;
        }
        return true;
    }
}
