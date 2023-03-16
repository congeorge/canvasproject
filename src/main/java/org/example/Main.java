package org.example;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    private static String help="" +
            "Command \t\tDescription\n" +
            "C w h           Should create a new canvas of width w and height h.\n" +
            "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only\n" +
            "                horizontal or vertical lines are supported. Horizontal and vertical lines\n" +
            "                will be drawn using the 'x' character.\n" +
            "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and\n" +
            "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n" +
            "                using the 'x' character.\n" +
            "B x y c         Should fill the entire area connected to (x,y) with \"colour\" c. The\n" +
            "                behavior of this is the same as that of the \"bucket fill\" tool in paint\n" +
            "                programs.\n" +
            "Q               Should quit the program.";

    private static Canvas canvas=null;
    private static CanvasOperationExecutor executor = new CanvasOperationExecutor(canvas);

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CanvasOperation operation = null;

        while (true) {
            String input = bufferedReader.readLine().trim();
            if (input != null && !input.isEmpty()) {

                String[] inputs = input.split(" ");
                String command = inputs[0];
                System.out.println("command is  : " + command);
                try {
                    operation = OperationFactory.getCommand(inputs);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    printhelp();
                    continue;

                }
                if (operation instanceof DrawCanvasOperation) {
                    canvas = new Canvas(inputs);
                    canvas.getCanvas();
                    executor.setCanvas(canvas);

                } else {
                    executor.executeOperation(operation);

                }
                System.out.println(canvas.getShapeAsString());
        } else {
                printhelp();
            }


        }
    }
    public static void printhelp()
    {
        System.out.println("Sorry Invalid Command : Please use any of the below valid commands:");
        System.out.println(help);
        
    }
}