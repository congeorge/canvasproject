package com.canvas.operations;


import com.canvas.exception.CanvasException;
import com.canvas.model.*;

public interface CanvasOperation<C> {

    boolean execute(Canvas canvas) throws CanvasException;

    void  undo(Canvas canvas);
}