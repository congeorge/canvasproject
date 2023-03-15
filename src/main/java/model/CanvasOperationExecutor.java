package model;

import java.util.ArrayList;
import java.util.List;

public class CanvasOperationExecutor {
    public Canvas getCanvas () {
        return canvas;
    }

    public void setCanvas (Canvas canvas) {
        this.canvas = canvas;
    }

    Canvas canvas;
    public CanvasOperationExecutor (Canvas canvas)  {
        this.canvas=canvas;
    }

    private final List<CanvasOperation> canvasOperationList
            = new ArrayList<>();

    public void executeOperation(CanvasOperation canvasOperation) {
        canvasOperationList.add(canvasOperation);
        canvasOperation.execute(canvas);
    }
}
