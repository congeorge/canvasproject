package com.canvas.operations;


import com.canvas.exception.CanvasException;
import com.canvas.model.*;

public interface CanvasOperation<C> {


    boolean execute(TwoDCanvas canvas) throws CanvasException;

    Coordinate<C>[] getCoordinates();
}