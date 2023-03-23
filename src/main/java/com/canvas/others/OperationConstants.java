package com.canvas.others;
public final class OperationConstants {

    private OperationConstants() {
        }

    public static final String    CANVAS="C";
    public static final String LINE="L";
    public static final String RECTANGLE="R";
    public static final String FILL="B";
    public static final String QUIT="Q";
    public static final String UNDO="U";
    public static final String HELP="H";
    public static final String  HELPMESSAGE =
            """
                    Command \t\tDescription
                    C w h           Should create a new canvas of width w and height h.
                    L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only
                                    horizontal or vertical lines are supported. Horizontal and vertical lines
                                    will be drawn using the 'x' character.
                    R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and
                                    lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
                                    using the 'x' character.
                    B x y c         Should fill the entire area connected to (x,y) with "colour" c. The
                                    behavior of this is the same as that of the "bucket fill" tool in paint
                                    programs.
                    U               Undo last action
                    H               Help
                    Q               Should quit the program.""";

}