package com.canvas.others;

import com.canvas.exception.CanvasException;
import com.canvas.operations.CanvasOperation;
import com.canvas.operations.DrawCanvasOperation;
import com.canvas.operations.UndoCanvasOperation;
import com.canvas.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;

public class CanvasOperationExecutor {

    private void setCanvas(TwoDCanvas canvas) {
        this.canvas = canvas;
    }
    private static final Logger logger = LogManager.getLogger(CanvasOperationExecutor.class);

    private TwoDCanvas canvas;

    public TwoDCanvas getCanvas() {
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
            undo();
            displayCanvas();
            return;
        }
        if (canvasOperation.execute(canvas))
            canvasOperationList.add(canvasOperation);
        displayCanvas();


    }

    private void setupNewCanvas(CanvasOperation canvasOperation) {
        Coordinate<Integer> coordinate = canvasOperation.getCoordinates()[0];
        this.canvas = new TwoDCanvas(coordinate);
        this.canvasOperationList.clear();
        this.setCanvas(canvas);

    }

    public void undo()  {
        int length = canvasOperationList.size();
        if (length > 1) {
            List<CanvasOperation> listops = new ArrayList<>();
            listops.addAll(canvasOperationList);
            canvasOperationList.clear();
            listops.remove(length - 1);
            listops.stream().forEach(x -> {
                try {
                    executeOperation(x);
                } catch (CanvasException e) {
                    logger.error(e.getMessage());
                }

            });
        } else if (length == 1) {
            canvasOperationList.clear();
            canvas = null;
            logger.info("All Clear :Ready to Start again");
        } else
            logger.info("Start again: Nothing to undo");


    }

    public void displayCanvas() {
        canvasOperationList.forEach(logger::debug);
        if (canvas != null)
            logger.info(canvas.showCanvas());
    }
}
