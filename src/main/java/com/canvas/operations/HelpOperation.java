package com.canvas.operations;

import com.canvas.model.Canvas;
import com.canvas.others.Operations;

public class HelpOperation implements CanvasOperation{
    public HelpOperation(String[] inputArgs) {

    }

    @Override
    public boolean execute (Canvas canvas) {
        printHelp();
        return false;
    }
    @Override
    public void undo(Canvas canvas) {
     // do nothing.

    }
    private void printHelp() {
        System.out.println(Operations.HelpMessage);
    }
}
