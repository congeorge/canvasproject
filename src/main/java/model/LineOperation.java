package model;

import java.util.Arrays;

public class LineOperation implements CanvasOperation{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    public LineOperation (String[] inputs) throws IncorrectParametersException {
        if (inputs.length < 4)
            throw new IncorrectParametersException("Line needs 4 co-ordinates");
        try {
            x1 = Integer.parseInt(inputs[1]);
            y1 = Integer.parseInt(inputs[2]);
            x2 = Integer.parseInt(inputs[3]);
            y2 = Integer.parseInt(inputs[4]);
        } catch (NumberFormatException e) {
            throw new IncorrectParametersException("All Line co-ordinates should be valid numbers");
        }
        if (x1 != x2 && y1 != y2) {
            throw new IncorrectParametersException("Diagonal line are not supported");
        }
    }

    @Override
    public void execute (Canvas canvas)  {
        int width=canvas.getWidth();
        int height=canvas.getHeight();
        if(canvas.isWithinCanvas(x1,y1) && canvas.isWithinCanvas(x2,y2))
        {
            canvas.addLine(x1,y1,x2,y2);
        }
        else
            System.out.println("Execution Failed:Cordinates are not within the Canvas");
    }
}
