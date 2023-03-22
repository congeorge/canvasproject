package com.canvas.operations;

import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCanvas;
import com.canvas.others.Operations;

public class HelpOperation implements CanvasOperation{
    public HelpOperation(String[] inputArgs) {

    }

    @Override
    public boolean execute (TwoDCanvas canvas) {
        printHelp();
        return false;
    }
/*    @Override
    public void undo(TwoDCanvas canvas) {
     // do nothing.

    }*/

    @Override
    public Coordinate[] getCoordinates() {
        return new Coordinate[0];
    }

    private void printHelp() {
        System.out.println(Operations.HelpMessage);
    }
}
