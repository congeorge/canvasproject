package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.QuitCanvasException;
import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCanvas;

public class QuitOperation implements CanvasOperation{
    public QuitOperation(String[] inputArgs) {

    }

    @Override
    public boolean execute (TwoDCanvas canvas) throws CanvasException {
        throw new QuitCanvasException("Quitting the Application");
    }
/*    @Override
    public void undo(TwoDCanvas canvas) {
     // do nothing.

    }*/

    @Override
    public Coordinate[] getCoordinates() {
        return new Coordinate[0];
    }
}
