package com.canvas.operations;

import com.canvas.exception.CanvasException;
import com.canvas.exception.QuitCanvasException;
import com.canvas.model.Canvas;

public class QuitOperation implements CanvasOperation{
    public QuitOperation(String[] inputArgs) {

    }

    @Override
    public boolean execute (Canvas canvas) throws CanvasException {
        throw new QuitCanvasException("Quitting the Application");
    }
    @Override
    public void undo(Canvas canvas) {
     // do nothing.

    }
}
