package com.canvas.others;

import com.canvas.exception.CanvasException;
import com.canvas.operations.CanvasOperation;
import com.canvas.operations.DrawCanvasOperation;
import com.canvas.operations.UndoCanvasOperation;
import com.canvas.model.*;


import java.util.ArrayList;
import java.util.List;

public class CanvasOperationExecutor {

    private void setCanvas(TwoDCanvas canvas) {
        this.canvas = canvas;
    }

    private TwoDCanvas canvas;
    public TwoDCanvas getCanvas () {
        return canvas;
    }

    public List<CanvasOperation> getCanvasOperationList() {
        return canvasOperationList;
    }

    private final List<CanvasOperation> canvasOperationList
            = new ArrayList<>();

    public void executeOperation(CanvasOperation canvasOperation) throws CanvasException {
        if (canvasOperation instanceof DrawCanvasOperation) {
            setupNewCanvas(canvasOperation);
        }
        if (canvasOperation instanceof UndoCanvasOperation) {
         //   undoLastOperation();
            undo();
            displayCanvas();
            return;
        }
        if (canvasOperation.execute(canvas))
            canvasOperationList.add(canvasOperation);
            displayCanvas();


    }

    private void setupNewCanvas(CanvasOperation canvasOperation) {
        DrawCanvasOperation canvasOperation1 = (DrawCanvasOperation) canvasOperation;
        Coordinate<Integer> coordinate = canvasOperation.getCoordinates()[0];
        this.canvas = new TwoDCanvas(coordinate);
        this.canvasOperationList.clear();
        this.setCanvas(canvas);

    }

    public void undo() {
           int length = canvasOperationList.size();
            if (length > 1) {
                List<CanvasOperation> listops=new ArrayList<>();
                listops.addAll(canvasOperationList);
                canvasOperationList.clear();
                listops.remove(length - 1);
                listops.stream().forEach((x)-> {
                    try {
                        executeOperation(x);
                    } catch (CanvasException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else if (length == 1) {
                canvasOperationList.clear();
                canvas = null;
                System.out.println("All Clear :Ready to Start again");
            }
         else
            System.out.println("Start again: Nothing to undo");


    }

/*    public void undoLastOperation() {
        if (canvas != null) {
            int length = canvasOperationList.size();
            if (length > 1) {
                canvasOperationList.get(length - 1).undo(canvas);
                canvasOperationList.remove(length - 1);
            } else if (length == 1) {
                canvasOperationList.clear();
                canvas = null;
                System.out.println("All Clear :Ready to Start again");
            }
        } else
            System.out.println("Start again: Nothing to undo");

    }*/
    public void displayCanvas() {
        canvasOperationList.forEach(System.out::println);
        if (canvas != null)
            System.out.println(canvas.showCanvas());
    }
}
