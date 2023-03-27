package com.canvas.others;

import com.canvas.exception.CanvasException;
import com.canvas.exception.HelpCanvasException;
import com.canvas.exception.QuitCanvasException;
import com.canvas.operations.CanvasOperation;
import com.canvas.operations.DrawCanvasOperation;
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

    public void executeDrawOperation(CanvasOperation canvasOperation) throws CanvasException {
        if (canvasOperation instanceof DrawCanvasOperation) {
            setupNewCanvas(canvasOperation);

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

    public void undo() {
        int length = canvasOperationList.size();
        if (length > 1) {
            List<CanvasOperation> listops = new ArrayList<>();
            listops.addAll(canvasOperationList);
            canvasOperationList.clear();
            listops.remove(length - 1);
            listops.stream().forEach(x -> {
                try {
                    executeDrawOperation(x);
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

    public boolean isDrawOperation(String[] inputs) {
        boolean isDrawOperation = true;
        switch (inputs[0]) {
            case OperationConstants.HELP, OperationConstants.QUIT, OperationConstants.UNDO:
                isDrawOperation = false;
                break;
            default:
                isDrawOperation = true;
        }
        return isDrawOperation;
    }

    public void executeNonDrawOperation(String[] inputs) throws HelpCanvasException, QuitCanvasException {
        switch (inputs[0]) {
            case OperationConstants.HELP:
                throw new HelpCanvasException("Help Details");
            case OperationConstants.QUIT:
                throw new QuitCanvasException("Quitting the Application");
            case OperationConstants.UNDO:
                this.undo();
                this.displayCanvas();
                break;
            default:
                // do nothing
        }

    }
}
