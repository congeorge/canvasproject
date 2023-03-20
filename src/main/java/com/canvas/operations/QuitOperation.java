package com.canvas.operations;

import com.canvas.model.Canvas;

public class QuitOperation implements CanvasOperation{
    public QuitOperation(String[] inputArgs) {

    }

    @Override
    public boolean execute (Canvas canvas) {
        System.exit(0);
        return false;
    }
    @Override
    public void undo(Canvas canvas) {
     // do nothing.

    }
}
