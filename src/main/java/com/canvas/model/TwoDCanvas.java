package com.canvas.model;


import com.canvas.operations.CanvasOperation;

import java.util.Arrays;
import java.util.Stack;
import static com.canvas.model.CanvasDrawingConstants.*;

public class TwoDCanvas implements CanvasInterface {

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


    public TwoDCanvas(Coordinate<Integer> size ) {
        this.width=size.getX();
        this.height=size.getY();
        canvasArray = new char[height + 2][width + 2];

    }


    public void createCanvas() {
            drawCanvas(HorizontalBorder.getValue(), VerticalBorder.getValue());
    }

    private void drawCanvas(char horizontalborder,char verticalborder) {
        drawLine(0, 0, width - 1, 0,horizontalborder );
        drawLine(0, 1, 0, height + 1, verticalborder);
        drawLine(width - 1, 1, width - 1, height + 1, verticalborder);
        drawLine(0, height + 1, width - 1, height + 1,horizontalborder );
    }


    private void drawLine(int x1, int y1, int x2, int y2, char drawChar) {
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

    public void drawRectangle(int x1, int y1, int x2, int y2)
    {
         addRectangle(x1, y1, x2, y2, RectangleChar.getValue());
    }
    public void removeRectangle(int x1, int y1, int x2, int y2)
    {
         addRectangle(x1, y1, x2, y2, defaultChar);
    }

    private void addRectangle(int x1, int y1, int x2, int y2, char character)
    {
        try{
            drawLine(x1, y1, x2, y1,character);
            drawLine(x1, y1, x1, y2,character);
            drawLine(x2, y1, x2, y2, character);
            drawLine(x1, y2, x2, y2,character);
            }
            catch(Exception e){
                // log exception somewhere
                System.out.println("Not possible to draw for a given input.");

            }

        }
    public void doFill(int x, int y,char color)
    {
        char origChar=defaultChar;
        if(canvasArray[y][x]== HorizontalBorder.getValue()|| canvasArray[y][x]== VerticalBorder.getValue())
        {
            return;
        }

        if(canvasArray[y][x]!= defaultChar)
        {
            origChar=canvasArray[y][x];
        }
        fill(x,y,origChar,color);
    }

    public void undoFill(int x, int y,char color) {
        char origChar = color;
        fill(x,y,origChar,defaultChar);

    }

    private void fill(int x,int y,char origChar, char color) {
        Stack<TwoDCoordinate> stack = new Stack<>();
        stack.add(new TwoDCoordinate<Integer>(y, x));
        while (!stack.isEmpty()) {
            TwoDCoordinate pop = stack.pop();
            int xc = (Integer)pop.getX();
            int yc = (Integer)pop.getY();
            if (canvasArray[xc][yc] != origChar)
                continue;
            else
                canvasArray[xc][yc] = color;

            if (xc - 1 >= 0 && canvasArray[xc- 1][yc] == origChar) {
                stack.add(new TwoDCoordinate(xc - 1, pop.getY()));
            }
            if (xc + 1 < height + 1 && canvasArray[xc + 1][yc] == origChar) {
                stack.add(new TwoDCoordinate(xc + 1, pop.getY()));

            }
            if (yc- 1 >= 0 && canvasArray[xc][yc - 1] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX(), yc - 1));
            }
            if (yc+ 1 < width + 1 && canvasArray[xc][yc + 1] == origChar) {
                stack.add(new TwoDCoordinate(pop.getX(), yc + 1));
            }


        }
    }
    }


