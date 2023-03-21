package com.canvas.others;

import com.canvas.exception.CanvasException;
import com.canvas.operations.CanvasOperation;
import com.canvas.operations.DrawCanvasOperation;
import com.canvas.operations.UndoCanvasOperation;
import com.canvas.model.*;


import java.util.ArrayList;
import java.util.List;

public class CanvasOperationExecutor {

    public void setCanvas(com.canvas.model.Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas canvas;
    public Canvas getCanvas () {
        return canvas;
    }

    public CanvasOperationExecutor() {

    }

    public List<CanvasOperation> getCanvasOperationList() {
        return canvasOperationList;
    }

    private final List<CanvasOperation> canvasOperationList
            = new ArrayList<>();

    public void executeOperation(CanvasOperation canvasOperation) throws CanvasException {
        if (canvasOperation instanceof DrawCanvasOperation) {
            DrawCanvasOperation canvasOperation1 =(DrawCanvasOperation) canvasOperation;
            int width=canvasOperation1.getWidth();
            int height=canvasOperation1.getHeight();
            this.canvas = new Canvas(width,height);
            canvasOperationList.clear();
            setCanvas(canvas);
        }
        if (canvasOperation instanceof UndoCanvasOperation) {
            undoLastOperation();
            return;

        }

        if(canvasOperation.execute(canvas))
            canvasOperationList.add(canvasOperation);

        // for debug only
        canvasOperationList.forEach(System.out::println);
        if(canvas!=null)
            System.out.println(canvas.getShapeAsString());
    }

    public void undoLastOperation() {
        if(canvas!=null) {
            int length=getCanvasOperationList().size();
            if(length>1) {
                getCanvasOperationList().get(length - 1).undo(canvas);
                getCanvasOperationList().remove(length - 1);
            }
            else if(length==1) {
                getCanvasOperationList().clear();
                canvas = null;
                System.out.println("All Clear :Ready to Start again");
            }
        }
        else
            System.out.println("Start again: Nothing to undo");

    }
}
