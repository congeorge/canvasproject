package com.canvas.operations;


import com.canvas.model.*;

public interface CanvasOperation<C> {

    boolean execute(Canvas canvas) throws Exception;

    void  undo(Canvas canvas);
}