package com.canvas;

import com.canvas.exception.CanvasException;
import com.canvas.exception.QuitCanvasException;
import com.canvas.operations.CanvasOperation;
import com.canvas.others.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static CanvasOperationExecutor executor = new CanvasOperationExecutor();

    public static void main(String[] args) throws Exception {
        printHelp();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CanvasOperation operation = null;
        boolean exit = false;
        do {
            System.out.print("Enter Command:");
            String input = bufferedReader.readLine().trim();
            if (input != null && !input.isEmpty()) {
                String[] inputs = input.split("\\s+");
                try {
                    operation = CanvasOperationFactory.getOperation(inputs);
                    executor.executeOperation(operation);
                } catch (QuitCanvasException e) {
                    System.out.println(e.getMessage());
                    exit = true;

                } catch (CanvasException e) {
                    System.out.println(e.getMessage());
                    printHelp();
                    continue;
                }
                catch (Exception e) {
                    System.out.println("Something's Wrong: Exiting Application");
                    e.printStackTrace();
                    exit=true;
                }
            } else {
                printHelp();
                continue;
            }
        } while (!exit);

        bufferedReader.close();

    }

    public static void printHelp() {
        System.out.println("Please use any of the below valid commands:");
        System.out.println(OperationConstants.HELPMESSAGE);

    }

}
