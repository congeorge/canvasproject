package com.canvas.model;

public enum CanvasDrawingConstants {
    HORIZONTALBORDER('-'),
    VERTICALBORDER('|'),
    LINECHAR('X'),
    RECTANGLECHAR('X');



    public char getValue() {
        return value;
    }

    private final char value;

    private CanvasDrawingConstants(char s) {
        value = s;
    }
}
