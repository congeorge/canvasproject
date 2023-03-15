package org.example;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    private static Canvas canvas=null;
    private static CanvasOperationExecutor executor = new CanvasOperationExecutor(canvas);

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CanvasOperation operation = null;

        while (true) {
            String input = bufferedReader.readLine().trim();
            if (input != null) {

                String[] inputs = input.split(" ");
                String command = inputs[0];
                System.out.println("command is  : " + command);
                try {
                    operation = OperationFactory.getCommand(inputs);
                } catch (Exception e) {
                    e.printStackTrace();
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
                // error reading message
                // display help page

            }


        }
    }
}