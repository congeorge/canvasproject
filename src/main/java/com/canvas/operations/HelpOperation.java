package com.canvas.operations;

import com.canvas.model.Coordinate;
import com.canvas.model.TwoDCanvas;
import com.canvas.others.OperationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpOperation implements CanvasOperation{
    private static final Logger logger = LogManager.getLogger(HelpOperation.class);
    public HelpOperation(String[] inputArgs) {

    }

    @Override
    public boolean execute (TwoDCanvas canvas) {
        printHelp();
        return false;
    }


    @Override
    public Coordinate[] getCoordinates() {
        return new Coordinate[0];
    }

    private void printHelp() {
      logger.info(OperationConstants.HELPMESSAGE);
    }
}
