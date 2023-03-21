package com.canvas;

import com.canvas.exception.CanvasException;
import com.canvas.operations.CanvasOperation;
import com.canvas.model.*;
import com.canvas.others.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static CanvasOperationExecutor executor = new CanvasOperationExecutor();

    public static void main(String[] args) throws Exception {
        printHelp();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CanvasOperation operation = null;
        while (true) {

            String input = bufferedReader.readLine().trim();
            if (input != null && !input.isEmpty()) {
                String[] inputs = input.split("\\s+");
                try {
                    operation = CanvasOperationFactory.getOperation(inputs);
                    executor.executeOperation(operation);
                } catch (CanvasException e) {
                    System.out.println(e.getMessage());
                    printHelp();
                    continue;
                }
            } else {
                printHelp();
                continue;
            }
        }
    }

    public static void printHelp() {
        System.out.println("Please use any of the below valid commands:");
        System.out.println(Operations.HelpMessage);

    }

}
