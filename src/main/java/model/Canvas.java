package model;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Canvas{
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

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
         canvasArray = new char[height + 2][width+2];
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
            Arrays.fill(canvasArray[y1], x1, x2+1 , drawChar);


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

    public boolean isWithinCanvas(int x , int y)
    {
        boolean status=true;
        if(x<0 || x> (width) || y<0 || y> (height)){
            System.out.println("Co-ordinates are outside of the Canvas");
            status=false;
        }
        return status;
    }

    public void updateCanvasArray(int x, int y , char value) throws IncorrectParametersException {
        if(isWithinCanvas(x,y))
            canvasArray[x][y]=value;
        else
            //TODO : Add new exception
            throw new IncorrectParametersException("Cordinates are not within the Canvas");


    }


    public void addLine (int x1, int y1, int x2, int y2) {

        draw(x1, y1, x2, y2, LINE_CHAR);

    }



    public void addRectangle(int x1, int y1, int x2, int y2)
    {
        try{
            draw(x1, y1, x2, y1, LINE_CHAR);
            draw(x1, y1, x1, y2, LINE_CHAR);
            draw(x2, y1, x2, y2, LINE_CHAR);
            draw(x1, y2, x2, y2, LINE_CHAR);
                // add transforms if the order of co-oridnates is not appropriate
            }
            catch(Exception e){
                // log exception somewhere
                System.out.println("Not possible to draw for a given input.");

            }

        }
    }


