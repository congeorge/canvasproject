package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.model.Canvas;

public class UndoCanvasOperation implements CanvasOperation{


    public UndoCanvasOperation(String[] inputs)  throws CanvasException {

    }
    @Override
    public boolean execute (Canvas canvas) {
        return true;
    }

    @Override
    public void undo(Canvas canvas) {
        // do nothing.
    }
}
