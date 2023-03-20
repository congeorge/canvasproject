package com.canvas.operations;

import com.canvas.exception.IncorrectCordinatesException;
import com.canvas.model.Canvas;

public class FillOperation implements CanvasOperation{

    public FillOperation(String[] inputs) throws Exception {

    }
    @Override
    public boolean execute (Canvas canvas) throws Exception {
        if(canvas!=null) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
      /*      if (canvas.isWithinCanvas(x1, y1) && canvas.isWithinCanvas(x2, y2)) {
                canvas.addRectangle(x1, y1, x2, y2);
                return true;
            } else*/
            throw new IncorrectCordinatesException("Execution Failed:Cordinates are not within the Canvas");
        }
        else {
            System.out.println("Canvas is needed to draw rectangle: Please create canvas first");
        }
        return false;
    }

    @Override
    public void undo(Canvas canvas) {


    }
}
