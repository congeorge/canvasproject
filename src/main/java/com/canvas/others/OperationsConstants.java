package com.canvas.others;

public interface OperationsConstants {
    String CANVAS="C";
    String LINE="L";
    String RECTANGLE="R";
    String FILL="B";
    String QUIT="Q";
    String UNDO="U";
    String Help="H";

    String HelpMessage="" +
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
            "U               Undo last action\n"+
            "H               Help\n"+
            "Q               Should quit the program.";

}
