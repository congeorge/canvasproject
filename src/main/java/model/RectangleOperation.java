package model;

public class RectangleOperation implements CanvasOperation{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public RectangleOperation (String[] inputs) throws IncorrectParametersException {
        if( inputs.length <4){
            throw new IncorrectParametersException("Rectangle needs 4 co-ordinates");
        }
        try {
            x1 = Integer.parseInt(inputs[1]);
            y1 = Integer.parseInt(inputs[2]);
            x2 = Integer.parseInt(inputs[3]);
            y2 = Integer.parseInt(inputs[4]);
        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Rectangle co-ordinates should be valid numbers");
        }

    }

    @Override
    public void execute (Canvas canvas) {
        //validate params are within the canvas
        // update the fields in the canvas
        canvas.addRectangle(x1,y1,x2,y2);
    }
}
