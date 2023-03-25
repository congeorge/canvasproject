package com.canvas.operations;


import com.canvas.exception.CanvasException;
import com.canvas.model.*;

public interface CanvasOperation {


    boolean execute(TwoDCanvas canvas) throws CanvasException;

    Coordinate[] getCoordinates();


}