package model;

public class RectangleOperation implements CanvasOperation{

    public RectangleOperation (String[] inputs) {
    }

    @Override
    public void execute (Canvas canvas) {
        //validate params are within the canvas
        // update the fields in the canvas
        canvas.addRectangle();
    }
}
