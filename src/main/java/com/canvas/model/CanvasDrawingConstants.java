package com.canvas.model;

public enum CanvasDrawingConstants {
    HorizontalBorder('-'),
    VerticalBorder('|'),
    LineChar('X');

    public char getValue() {
        return value;
    }

    private final char value;

    private CanvasDrawingConstants(char s) {
        value = s;
    }
}
