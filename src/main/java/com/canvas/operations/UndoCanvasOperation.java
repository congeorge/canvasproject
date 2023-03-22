package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCanvas;

public class UndoCanvasOperation implements CanvasOperation{


    public UndoCanvasOperation(String[] inputs)  throws CanvasException {

    }
    @Override
    public boolean execute (TwoDCanvas canvas) {
        return true;
    }

  /*  @Override
    public void undo(TwoDCanvas canvas) {
        // do nothing.
    }
*/
    @Override
    public Coordinate[] getCoordinates() {
        return new Coordinate[0];
    }
}
