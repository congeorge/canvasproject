package com.canvas;

import com.canvas.exception.CanvasException;
import com.canvas.exception.QuitCanvasException;
import com.canvas.operations.*;
import com.canvas.others.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static CanvasOperationExecutor executor = new CanvasOperationExecutor();
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        printHelp();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CanvasOperation operation = null;
        boolean exit = false;
        do {
            logger.info("Enter Command:");
            String input = bufferedReader.readLine().trim();
            if (input != null && !input.isEmpty()) {
                String[] inputs = input.split("\\s+");
                try {
                    if (!executor.isDrawOperation(inputs))
                        executor.executeNonDrawOperation(inputs);
                    else {
                        operation = CanvasOperationFactory.getOperation(inputs);
                        executor.executeDrawOperation(operation);
                    }
                } catch (QuitCanvasException e) {
                    logger.error(e.getMessage());
                    exit = true;
                } catch (CanvasException e) {
                    logger.error(e.getMessage());
                    printHelp();
                } catch (Exception e) {
                    logger.error("Something's Wrong: Exiting Application");
                    e.printStackTrace();
                    exit = true;
                }
            } else {
                printHelp();

            }
        } while (!exit);

        bufferedReader.close();

    }


    public static void printHelp() {
        System.out.println(OperationConstants.HELPMESSAGE);
    }

}

