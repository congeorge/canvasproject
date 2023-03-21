package com.canvas.model;


import java.util.Arrays;
import java.util.Stack;
import static com.canvas.model.CanvasDrawingConstants.*;

public class Canvas{

    private int height;
    private int width;
    private char[][] canvasArray;

    private char defaultChar='\u0000';

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    public Canvas(int... args) {
        if (args.length >= 2) {
            this.width = args[0];
            this.height = args[1];
            canvasArray = new char[height + 2][width + 2];
        } else {
            // not enough arguments ?
        }
    }


    public void createCanvas() {
        drawLine(0, 0, width - 1, 0, HorizontalBorder.getValue() );
        drawLine(0, 1, 0, height + 1, VerticalBorder.getValue());
        drawLine(width - 1, 1, width - 1, height + 1, VerticalBorder.getValue());
        drawLine(0, height + 1, width - 1, height + 1,HorizontalBorder.getValue());
    }


    public void drawLine(int x1, int y1, int x2, int y2, char drawChar) {
        if (x1 == x2) {
             for (int i = y1; i <= y2; i++) {
                canvasArray[i][x1] = drawChar;
            }
        } else if (y1 == y2) {
            Arrays.fill(canvasArray[y1], x1, x2 + 1, drawChar);


        }

    }

    public String showCanvas() {
        StringBuilder results = new StringBuilder();

        for (int i = 0; i < canvasArray.length; ++i) {
            for (int j = 0; j < canvasArray[i].length; j++) {
                results.append((canvasArray[i][j]) == defaultChar ? " " : canvasArray[i][j]);
            }
            results.append("\n\r");
        }
        return results.toString();
    }

    public boolean isWithinCanvas(int x, int y) {
        boolean status = true;
        if (x < 0 || x > (width) || y < 0 || y > (height)) {
            System.out.println("Co-ordinates are outside of the Canvas");
            status = false;
        }
        return status;
    }


    public void addLine (int x1, int y1, int x2, int y2) {

        drawLine(x1, y1, x2, y2, LineChar.getValue());

    }

    public void removeLine (int x1, int y1, int x2, int y2) {

        drawLine(x1, y1, x2, y2, defaultChar);

    }


    public void addRectangle(int x1, int y1, int x2, int y2)
    {
        try{
            drawLine(x1, y1, x2, y1, LineChar.getValue());
            drawLine(x1, y1, x1, y2, LineChar.getValue());
            drawLine(x2, y1, x2, y2, LineChar.getValue());
            drawLine(x1, y2, x2, y2, LineChar.getValue());
            }
            catch(Exception e){
                // log exception somewhere
                System.out.println("Not possible to draw for a given input.");

            }

        }
    public void doFill(int x, int y,char color)
    {
        char origChar=defaultChar;
        System.out.println("Do fill operation");
        // if point is on canvas border do nothing
        if(canvasArray[y][x]!= defaultChar)
        {
            origChar=canvasArray[y][x];

        }
        Stack<TwoDCoordinate> stack= new Stack<>();
        stack.add(new TwoDCoordinate(y,x));
        while(!stack.isEmpty())
        {
            TwoDCoordinate pop = stack.pop();
            int xc = pop.getX();
            int yc = pop.getY();
            if (canvasArray[xc][yc] != origChar)
                continue;
            else
               canvasArray[xc][yc] = color;

            if (pop.getX() - 1 >= 0 && canvasArray[pop.getX() - 1][pop.getY()] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX() - 1, pop.getY()));
            }
            if (pop.getX() + 1 < height+1 && canvasArray[pop.getX() + 1][pop.getY()] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX() + 1, pop.getY()));

            }
            if (pop.getY() - 1 >= 0 && canvasArray[pop.getX()][pop.getY() - 1] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX(), pop.getY() - 1));
            }
            if (pop.getY() + 1 < width+1 && canvasArray[pop.getX()][pop.getY() + 1] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX(), pop.getY() + 1));
            }


        }


    }

    public void undoFill(int x, int y,char color) {
        char origChar = color;
        Stack<TwoDCoordinate> stack = new Stack<>();
        stack.add(new TwoDCoordinate(y, x));
        while (!stack.isEmpty()) {
            TwoDCoordinate pop = stack.pop();
            int xc = pop.getX();
            int yc = pop.getY();
            if (canvasArray[xc][yc] != origChar)
                continue;
            else
                canvasArray[xc][yc] = defaultChar;

            if (pop.getX() - 1 >= 0 && canvasArray[pop.getX() - 1][pop.getY()] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX() - 1, pop.getY()));
            }
            if (pop.getX() + 1 < height + 1 && canvasArray[pop.getX() + 1][pop.getY()] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX() + 1, pop.getY()));

            }
            if (pop.getY() - 1 >= 0 && canvasArray[pop.getX()][pop.getY() - 1] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX(), pop.getY() - 1));
            }
            if (pop.getY() + 1 < width + 1 && canvasArray[pop.getX()][pop.getY() + 1] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX(), pop.getY() + 1));
            }
        }
    }
    }


