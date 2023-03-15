package model;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Canvas{
    private int height;
    private int width;
     char[][] canvasArray;
    private static final char HORIZONTAL_EDGE_CHAR = '-';
    private static final char VERTICAL_EDGE_CHAR   = '|';
    private static final char LINE_CHAR            = 'x';
    private  String horizontalEdge="";


      public Canvas (String... args) {
        if (args.length < 2)
        {

        }
        else {
            try {
                this.width = Integer.parseInt(args[1]);
                this.height = Integer.parseInt(args[2]);
                canvasArray = new char[height+2][width];
                Arrays.stream(canvasArray).forEach(chars -> Arrays.fill(chars, ' '));

                horizontalEdge = Stream.generate(() -> String.valueOf(HORIZONTAL_EDGE_CHAR)).limit(width + 2).collect(Collectors.joining());

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getCanvas() {
     //   canvasArray = new char[height + 2][width];
        // Draw upper border
        draw(0, 0, width - 1, 0, '-');
        // Draw left border
        draw(0, 1, 0, height + 1, '|');
        // Draw right border
        draw(width - 1, 1, width - 1, height + 1, '|');
        // Draw lower border
        draw(0, height + 1, width - 1, height + 1, '-');

        return getShapeAsString();
    }


    public void draw(int x1, int y1, int x2, int y2, char drawChar) {
        if (x1 == x2) {
            // vertical line
            for (int i = y1; i <= y2; i++) {
                canvasArray[i][x1] = drawChar;
            }
        } else if (y1 == y2) {
            // horizontal line
            Arrays.fill(canvasArray[y1], x1, x2 + 1, drawChar);
        } else {
            // we have a slope
            double slope = (double) (y2 - y1) / (double) (x2 - x1);
            for (int i = y1; i <= y2; i++) {
                canvasArray[i][(int) Math.ceil(x1 + (slope * i))] = drawChar;
            }
        }
    }
    public String getShapeAsString() {
        StringBuilder results = new StringBuilder();

        for (int i = 0; i < canvasArray.length; ++i) {
            for (int j = 0; j < canvasArray[i].length; j++) {
                results.append((canvasArray[i][j]) == 0 ? " " : canvasArray[i][j]);
            }
            results.append("\n\r");
        }
        return results.toString();
    }


    public void addRectangle () {
    }

    public void addLine (int x1, int y1, int x2, int y2) {
        //row by row
        for (int row = y1 - 1; row <= y2 - 1 && row < height; row++) {
            //col by col
            for (int col = x1 - 1; col <= x2 - 1 && col < width; col++) {
                canvasArray[row][col] = LINE_CHAR;
            }
        }
    }
}

